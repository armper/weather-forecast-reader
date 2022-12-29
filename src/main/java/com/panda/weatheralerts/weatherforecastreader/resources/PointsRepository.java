package com.panda.weatheralerts.weatherforecastreader.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.pointsrequest.Points;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PointsRepository {
    private final RestTemplate restTemplate;

    public Points getForecastPoints(Double lat, Double lon) {
        String url = "https://api.weather.gov/points/" + lat + "," + lon;

        ResponseEntity<Points> response = restTemplate.getForEntity(url, Points.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error getting forecast points from API");
        }

        return response.getBody();
    }

}
