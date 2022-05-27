package com.weather.api.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.weather.api.exception.handler.InvalidDateException;
import com.weather.api.model.response.WeatherAPIResponse;
import com.weather.api.service.WindSurferAPIService;

@RunWith(MockitoJUnitRunner.class)
public class WindSurferApiControllerTest {

    @InjectMocks
    private WindSurferApiController controller;

    @Mock
    private WindSurferAPIService service;

    @Test
    public void getWindSurferLocationsSuccessTest() {

        ResponseEntity<WeatherAPIResponse> responseEntity = controller.getWindSurfingLocationsByDate("2022-05-21");

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test(expected = InvalidDateException.class)
    public void getWindSurferLocationDateFailureTest() {
        
    	ResponseEntity<WeatherAPIResponse> responseEntity = controller.getWindSurfingLocationsByDate("20220521");
        
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
