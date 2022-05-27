package com.weather.api.service.impl;
/*package com.weather.api.service.impl;


import com.weather.api.exception.handler.WeatherForecastNotFoundException;
import com.weather.api.model.City;
import com.weather.api.model.ListData;
import com.weather.api.model.Main;
import com.weather.api.model.WeatherRoot;
import com.weather.api.model.response.WeatherAPIResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherAPIServiceImplTest {

    @InjectMocks
    private WeatherAPIServiceImpl service;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Environment environment;


    @Test(expected = WeatherForecastNotFoundException.class)
    public void getWeatherForecastFailureTest() {

        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?zip=%s&appid=%s";
        String key = "some-key";
        WeatherRoot weatherRoot = new WeatherRoot();

        when(environment.getProperty("weather.api.url")).thenReturn(apiUrl);
        when(environment.getProperty("weather.api.key")).thenReturn(key);
        //when(String.format(apiUrl, 12345, key)).thenReturn("api");
        when(restTemplate.getForObject("api", WeatherRoot.class)).thenReturn(weatherRoot);

        service.getWeatherForecast(12345L);
    }



    @Test
    public void getWeatherForecastSuccessTest() {

        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?zip=12345&appid=some-key";
        String key = "some-key";
        WeatherRoot weatherRoot = getWeatherRoot();

        when(environment.getProperty("weather.api.url")).thenReturn(apiUrl);
        when(environment.getProperty("weather.api.key")).thenReturn(key);
        when(restTemplate.getForObject(apiUrl, WeatherRoot.class)).thenReturn(weatherRoot);

        WeatherAPIResponse response = service.getWeatherForecast(12345L);

        Assert.assertEquals(12345L, response.getZipcode());
    }


    private WeatherRoot getWeatherRoot() {

        WeatherRoot weatherRoot = new WeatherRoot();

        List<ListData> dataList = new ArrayList<>();

        ListData data = new ListData();
        data.setDt(1605344400000L);
        data.setDt_txt("2020-11-14 09:00:00");

        Main main = new Main();
        main.setTemp_min(280.39);

        data.setMain(main);
        dataList.add(data);
        weatherRoot.setList(dataList);


        City city = new City();
        city.setCountry("USA");
        city.setName("New York");
        weatherRoot.setCity(city);

        return weatherRoot;
    }
}
*/