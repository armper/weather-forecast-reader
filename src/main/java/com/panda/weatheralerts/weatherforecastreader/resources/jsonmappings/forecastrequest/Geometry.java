package com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.forecastrequest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private String type;
    private List<List<List<Double>>> coordinates;
}