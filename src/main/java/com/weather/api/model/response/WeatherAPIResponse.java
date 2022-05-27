package com.weather.api.model.response;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherAPIResponse implements Serializable {

    private String cityName;
    private String countryCode;
    private String longitude;
    private String latitude;
    private String stateCode;
    private String timeZone;
    private String averageTemp;
    private String windSpeed;
}
