package com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.forecastrequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Periods {
    private Integer number;
    private String name;
    private String startTime;
    private String endTime;
    private Boolean isDaytime;
    private Integer temperature;
    private String temperatureUnit;
    private Object temperatureTrend;
    private String windSpeed;
    private String windDirection;
    private String icon;
    private String shortForecast;
    private String detailedForecast;
}