package com.weather.api.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.weather.api.exception.handler.WindSurferLocationNotFoundException;
import com.weather.api.model.WeatherForecastDataResponse;
import com.weather.api.model.response.WeatherAPIResponse;
import com.weather.api.service.WindSurferAPIService;
import com.weather.api.util.Util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class WindSurferAPIServiceImpl implements WindSurferAPIService {

	private final RestTemplate restTemplate;
	private final Environment environment;

	@Value("#{'${list.of.cities}'.split(',')}")
	private List<String> citiesList;

	@Override
	public WeatherAPIResponse getWindSurfingLocationsByDate(String date) {

		log.info("*** Getting locations suitable for wind surfing for a particular date :: {}", date);

		String apiUrl = environment.getProperty("weather.api.url");
		String key = environment.getProperty("weather.api.key");
		List<WeatherForecastDataResponse> weatherForecastDataList = new ArrayList<>();
		List<WeatherForecastDataResponse> bestWeatherForecastDataList = new ArrayList<>();
		List<WeatherForecastDataResponse> bestWeatherForecastDataListFiltered = new ArrayList<>();
		String windSpeed = null;
		String validDate = null;
		String temp = null;
		String cityName = null;
		String latitude = null;
		String longitude = null;
		String stateCode = null;
		String timeZone = null;
		String countryCode = null;
		ArrayList<Object> dataList = null;
		WeatherAPIResponse weatherAPIResponse = null;
		for (String city : citiesList) {
			String api = String.format(apiUrl, city, key);
			WeatherForecastDataResponse response1 = restTemplate.getForObject(api, WeatherForecastDataResponse.class);
			weatherForecastDataList.add(response1);
		}
		for (WeatherForecastDataResponse weatherForecastData : weatherForecastDataList) {
			dataList = new ArrayList<>();
			for (Object data : weatherForecastData.getData()) {
				windSpeed = ((LinkedHashMap) data).get(Util.WIND_SPEED_KEY).toString();
				temp = ((LinkedHashMap) data).get(Util.TEMP_KEY).toString();
				validDate = ((LinkedHashMap) data).get(Util.VALID_DATE_KEY).toString();
				if (validDate.equalsIgnoreCase(date)) {
					dataList.add(data);
					weatherForecastData.setData(dataList);
					if (Float.parseFloat(windSpeed) > 5 && Float.parseFloat(windSpeed) < 18
							&& Float.parseFloat(temp) > 5 && Float.parseFloat(temp) < 35) {
						bestWeatherForecastDataList.add(weatherForecastData);
						weatherForecastData.setBestLocFactor(Float.parseFloat(windSpeed) * 3 + Float.parseFloat(temp));
					}
				}
			}
		}

		if (!CollectionUtils.isEmpty(bestWeatherForecastDataList)) {
			float max = bestWeatherForecastDataList.stream()
					.max(Comparator.comparing(WeatherForecastDataResponse::getBestLocFactor)).get().getBestLocFactor();

			bestWeatherForecastDataListFiltered = bestWeatherForecastDataList.stream()
					.filter(m -> m.getBestLocFactor() == max).collect(Collectors.toList());
		} else {
			throw new WindSurferLocationNotFoundException("No suitable locations found for provided date");
		}
		if (CollectionUtils.isEmpty(bestWeatherForecastDataListFiltered)) {
			throw new WindSurferLocationNotFoundException("No suitable locations found for provided date");
		}

		for (WeatherForecastDataResponse bestWeatherForecastData : bestWeatherForecastDataList) {
			for (Object data : bestWeatherForecastData.getData()) {
				windSpeed = ((LinkedHashMap) data).get(Util.WIND_SPEED_KEY).toString();
				temp = ((LinkedHashMap) data).get(Util.TEMP_KEY).toString();
				validDate = ((LinkedHashMap) data).get(Util.VALID_DATE_KEY).toString();
			}
			cityName = bestWeatherForecastData.getCity_name();
			latitude = bestWeatherForecastData.getLat();
			longitude = bestWeatherForecastData.getLon();
			stateCode = bestWeatherForecastData.getState_code();
			timeZone = bestWeatherForecastData.getTimezone();
			countryCode = bestWeatherForecastData.getCountry_code();
		}

		log.info("Locations suitable for wind surfing for a particular date :: {}", weatherAPIResponse);
		return weatherAPIResponse.builder().cityName(cityName).latitude(latitude).longitude(longitude).averageTemp(temp)
				.stateCode(stateCode).timeZone(timeZone).windSpeed(windSpeed).countryCode(countryCode).build();
	}
}
