package com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.forecastrequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Elevation {
    private String unitCode;
    private Double value;
}