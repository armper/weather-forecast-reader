package com.panda.weatheralerts.weatherforecastreader.services.criteria;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class WeatherAlertCriteria {

    private Integer forecastValue;

    public Boolean isGreaterThanOrEqualTo(Integer threshold) {
        return forecastValue >= threshold;
    }

    public Boolean isLessThanOrEqualTo(Integer threshold) {
        return forecastValue <= threshold;
    }

    public Boolean isWithin(Integer thresholdMin, Integer thresholdMax) {
        return thresholdMin <= forecastValue && thresholdMax >= forecastValue;
    }
}
