package com.weather.api.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForecastDataResponse {
	
	ArrayList<Object> data = new ArrayList<Object>();
	private String city_name;
	private String lon;
	private String timezone;
	private String lat;
	private String country_code;
	private String state_code;
	private float bestLocFactor;
}