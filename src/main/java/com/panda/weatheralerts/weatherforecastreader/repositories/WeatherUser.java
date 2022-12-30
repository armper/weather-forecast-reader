package com.panda.weatheralerts.weatherforecastreader.repositories;

import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Document
@Data
@ToString
@Builder
public class WeatherUser {

    @Id
    @NonNull
    private String email;

    private Double lat;

    private Double lon;

    private Integer windSpeedGreaterThan;

    private Integer windSpeedLessThan;

    private Integer temperatureGreaterThan;

    private Integer temperatureLessThan;

}
