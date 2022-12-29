package com.panda.weatheralerts.weatherforecastreader.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.forecastrequest.Forecast;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ForecastRepository {
    private final RestTemplate restTemplate;

    public Forecast getForecast(String forecastUrl) {
        ResponseEntity<Forecast> forecastResponse = restTemplate.getForEntity(forecastUrl, Forecast.class);
        if (!forecastResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error getting forecast from API");
        }

        return forecastResponse.getBody();
    }
}
