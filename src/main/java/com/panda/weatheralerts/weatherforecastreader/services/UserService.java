package com.panda.weatheralerts.weatherforecastreader.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.panda.weatheralerts.weatherforecastreader.repositories.UserRepository;
import com.panda.weatheralerts.weatherforecastreader.repositories.WeatherUser;
import com.panda.weatheralerts.weatherforecastreader.webcontroller.WeatherUserRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void updateAlertCriteria(WeatherUserRequest weatherUserRequest) {
        WeatherUser weatherUser = mapToWeatherUser(weatherUserRequest);
        userRepository.save(weatherUser);
    }

    public void updateWeatherUser(String email, WeatherUserRequest weatherUserRequest) {
        var optionalUser = userRepository.findById(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        optionalUser.ifPresent(weatherUser -> {
            weatherUser.setTemperatureGreaterThan(weatherUserRequest.getTempMax());
            weatherUser.setTemperatureLessThan(weatherUserRequest.getTempMin());
            weatherUser.setWindSpeedGreaterThan(weatherUserRequest.getWindMax());
            weatherUser.setWindSpeedLessThan(weatherUserRequest.getWindMin());
            userRepository.save(weatherUser);
        });
    }

    private WeatherUser mapToWeatherUser(WeatherUserRequest weatherUserRequest) {
        return WeatherUser.builder()
                .email(weatherUserRequest.getEmail())
                .windSpeedLessThan(Optional.ofNullable(weatherUserRequest.getWindMin()).orElse(0))
                .windSpeedGreaterThan(Optional.ofNullable(weatherUserRequest.getWindMax()).orElse(3000))
                .temperatureLessThan(Optional.ofNullable(weatherUserRequest.getTempMin()).orElse(-3000))
                .temperatureGreaterThan(Optional.ofNullable(weatherUserRequest.getTempMax()).orElse(3000))
                .build();
    }

}
