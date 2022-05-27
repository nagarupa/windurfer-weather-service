package com.weather.api.controller;

import java.net.URI;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.weather.api.exception.handler.InvalidDateException;
import com.weather.api.model.response.WeatherAPIResponse;
import com.weather.api.service.WindSurferAPIService;
import com.weather.api.util.Util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "WindSurfer API controller", description = "WindSurfer API ")
public class WindSurferApiController {

    private final WindSurferAPIService service;

    @Operation(summary = "Get a Weather forecast by Date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the data",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = WeatherAPIResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Date", content = @Content),
            @ApiResponse(responseCode = "404", description = "Weather forecast not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{date}")
    public ResponseEntity<WeatherAPIResponse> getWindSurfingLocationsByDate(@PathVariable String date) {

        log.info("*** Getting wind surffing locations for a particular date :: {}", date);

        if (Objects.isNull(date) || date.trim().length() < 10 || !Util.isValid(date))
            throw new InvalidDateException("Invalid Date or Format");

        WeatherAPIResponse weatherAPIResponse = service.getWindSurfingLocationsByDate(date);

        log.info("*** Weather forecast :: {}", weatherAPIResponse);
        return ResponseEntity.ok().body(weatherAPIResponse);
    }
}
