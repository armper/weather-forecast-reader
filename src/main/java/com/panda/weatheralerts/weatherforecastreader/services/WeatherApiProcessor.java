package com.panda.weatheralerts.weatherforecastreader.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.panda.weatheralerts.weatherforecastreader.repositories.UserRepository;
import com.panda.weatheralerts.weatherforecastreader.resources.ForecastRepository;
import com.panda.weatheralerts.weatherforecastreader.resources.PointsRepository;
import com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.forecastrequest.Forecast;
import com.panda.weatheralerts.weatherforecastreader.resources.jsonmappings.pointsrequest.Points;
import com.panda.weatheralerts.weatherforecastreader.services.criteria.WeatherAlertCriteria;
import com.panda.weatheralerts.weatherforecastreader.services.criteria.WeatherForecast;

import lombok.AllArgsConstructor;

/*
 * This WeatherApiProcessor class is responsible for processing the weather API data according to the users' alert criteria. 
 * It utilizes the RestTemplate to make HTTP requests to get the forecast data from the API, and then compares the data against the users' 
 * criteria to determine if any alerts should be raised. It also contains utility methods to parse the data from the API in order to 
 * make the comparison.
 */
@Component
@AllArgsConstructor
public class WeatherApiProcessor {

    private final UserRepository userRepository;

    private final PointsRepository pointsRepository;

    private final ForecastRepository forecastRepository;

    @Scheduled(fixedRate = 3600000)
    public void processWeatherApi() {

        userRepository.findAll().stream().forEach(user -> {
            Points points = pointsRepository.getForecastPoints(user.getLat(), user.getLon());

            throttleOneSecond();

            Forecast forecast = forecastRepository.getForecast(points.getProperties().getForecast());

            Integer forecastTemperature = forecast.getProperties().getPeriods().stream().findFirst().orElseThrow()
                    .getTemperature();

            String windSpeedRangeString = forecast.getProperties().getPeriods().stream().findFirst().orElseThrow()
                    .getWindSpeed();

            WindSpeedRange forecastWindSpeedRange = parseWindSpeedRange(windSpeedRangeString);

            System.out.println("Forecast temperature: " + forecastTemperature);

            System.out.println("Forecast wind speed range: " + forecastWindSpeedRange);

            forecast.getProperties().getPeriods().stream().findFirst().ifPresent(period -> {

                if (user.getWindSpeedGreaterThan() != null) {
                    Integer userWindSpeedGreaterThan = user.getWindSpeedGreaterThan();
                    WeatherAlertCriteria weatherAlertCriteria = new WeatherAlertCriteria(
                            forecastWindSpeedRange.getMinWindSpeed());
                    if (Boolean.TRUE
                            .equals(weatherAlertCriteria.isGreaterThanOrEqualTo(userWindSpeedGreaterThan))) {
                        System.out.println("Alert, windspeed of " + forecastWindSpeedRange.getMinWindSpeed()
                                + "mph is greater than user's criteria of "
                                + userWindSpeedGreaterThan + "mph");
                    }
                }

                if (user.getWindSpeedLessThan() != null) {
                    Integer userWindSpeedLessThan = user.getWindSpeedLessThan();
                    WeatherAlertCriteria weatherAlertCriteria = new WeatherAlertCriteria(
                            forecastWindSpeedRange.getMaxWindSpeed());
                    if (Boolean.TRUE.equals(weatherAlertCriteria.isLessThanOrEqualTo(userWindSpeedLessThan))) {
                        System.out.println("Alert, windspeed of " + forecastWindSpeedRange.getMaxWindSpeed()
                                + "mph is less than user's criteria of "
                                + userWindSpeedLessThan + "mph");
                    }
                }

                if (user.getTemperatureGreaterThan() != null) {
                    Integer userTemperatureGreaterThan = user.getTemperatureGreaterThan();
                    WeatherAlertCriteria weatherAlertCriteria = new WeatherAlertCriteria(forecastTemperature);
                    if (Boolean.TRUE.equals(weatherAlertCriteria.isGreaterThanOrEqualTo(userTemperatureGreaterThan))) {
                        System.out.println(
                                "Alert, temperature of " + forecastTemperature + "F is greater than user's criteria of "
                                        + userTemperatureGreaterThan + "F");
                    }
                }

                if (user.getTemperatureLessThan() != null) {
                    Integer userTemperatureLessThan = user.getTemperatureLessThan();
                    WeatherAlertCriteria weatherAlertCriteria = new WeatherAlertCriteria(forecastTemperature);
                    if (Boolean.TRUE.equals(weatherAlertCriteria.isLessThanOrEqualTo(userTemperatureLessThan))) {
                        System.out.println(
                                "Alert, temperature of " + forecastTemperature + "F is less than user's criteria of "
                                        + userTemperatureLessThan + "F");
                    }
                }

            });

            throttleOneSecond();
        });

    }

    private WindSpeedRange parseWindSpeedRange(String windSpeedRange) {
        String[] parts = windSpeedRange.split(" to ");
        Integer minWindspeed = Integer.parseInt(parts[0].replaceAll("[^\\d.]", ""));
        Integer maxWindspeed = parts.length > 1 ? Integer.parseInt(parts[1].replaceAll("[^\\d.]", "")) : minWindspeed;
        return new WindSpeedRange(minWindspeed, maxWindspeed);
    }

    private void throttleOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void checkIfTemeratureGreaterThan(Integer userTemperatureCriteria, Integer temperature) {
        if (temperature > userTemperatureCriteria) {
            System.out.println("Alert, temperature of " + temperature + "F is greater than user's criteria of "
                    + userTemperatureCriteria + "F");
        }
    }

    private void checkIfTemperatureLessThan(Integer userTemperatureCriteria, Integer temperature) {
        if (temperature < userTemperatureCriteria) {
            System.out.println("Alert, temperature of " + temperature + "F is less than user's criteria of "
                    + userTemperatureCriteria + "F");
        }
    }

    private void checkWindSpeed(Integer userWindSpeedCriteria, List<Integer> windSpeedRange) {
        if (windSpeedRange.size() == 1) {
            if (windSpeedRange.get(0) > userWindSpeedCriteria) {
                System.out.println("alert, wind speed is " + windSpeedRange.get(0) + "  mph. User's criteria is "
                        + userWindSpeedCriteria + " mph");
            }
        } else if (windSpeedRange.size() == 2) {
            if (windSpeedRange.get(0) > userWindSpeedCriteria || windSpeedRange.get(1) > userWindSpeedCriteria) {
                System.out.println(
                        "alert, wind speed is " + windSpeedRange.get(0) + " to " + windSpeedRange.get(1)
                                + "  mph. User's criteria is " + userWindSpeedCriteria + " mph");
            }
        }
    }

    public List<Integer> getIntegersFromString(String input) {
        List<Integer> integers = new ArrayList<>();
        Matcher m = Pattern.compile("\\d+").matcher(input);
        while (m.find()) {
            integers.add(Integer.parseInt(m.group()));
        }
        return integers;
    }

}