package com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.forecastrequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
    // @JsonProperty("@context")
    // private List<String> context;
    private String type;
    private Geometry geometry;
    private Properties properties;

}