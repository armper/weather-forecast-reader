package com.panda.weatheralerts.weatherforecastreader.services;

import org.springframework.stereotype.Service;

import com.panda.weatheralerts.weatherforecastreader.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void updateAlertCriteria(String email, int windMin, int windMax, int tempMin, int tempMax) {
        var user = userRepository.findById(email).orElseThrow();

        user.setWindSpeedGreaterThan(windMin);
        user.setWindSpeedLessThan(windMax);
        user.setTemperatureGreaterThan(tempMin);
        user.setTemperatureLessThan(tempMax);

        userRepository.save(user);

    }

}
