package com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.pointsrequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
    private String forecast;
}
