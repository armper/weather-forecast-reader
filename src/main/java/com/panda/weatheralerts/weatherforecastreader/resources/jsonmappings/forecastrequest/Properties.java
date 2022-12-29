package com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.forecastrequest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
    private String updated;
    private String units;
    private String forecastGenerator;
    private String generatedAt;
    private String updateTime;
    private String validTimes;
    private Elevation elevation;
    private List<Periods> periods;
}