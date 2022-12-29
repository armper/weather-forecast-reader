package com.panda.weatheralerts.weatherforecastreader.repositories;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document
@Data
@ToString
public class WeatherUser {

    @Id
    private String email;

    private Double lat;

    private Double lon;

    private Integer windSpeedGreaterThan;

    private Integer windSpeedLessThan;

    private Integer temperatureGreaterThan;

    private Integer temperatureLessThan;

}
