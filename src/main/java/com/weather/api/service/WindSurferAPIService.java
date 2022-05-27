package com.weather.api.service;

import com.weather.api.model.response.WeatherAPIResponse;

public interface WindSurferAPIService {

    WeatherAPIResponse getWindSurfingLocationsByDate(String date);
}
