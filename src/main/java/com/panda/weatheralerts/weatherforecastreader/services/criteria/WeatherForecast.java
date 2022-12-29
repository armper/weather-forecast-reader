package com.panda.weatheralerts.weatherforecastreader.services.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherForecast {
    private Integer temperature;
    private Integer minWindSpeed;
    private Integer maxWindSpeed;

}