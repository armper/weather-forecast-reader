package com.panda.weatheralerts.weatherforecastreader.webcontroller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.weatheralerts.weatherforecastreader.repositories.WeatherUser;
import com.panda.weatheralerts.weatherforecastreader.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api")
public class UpdateAlertCriteria {

    private final UserService userService;

    @PostMapping("/weatherusers")
    public ResponseEntity<Object> weatherUsers(@RequestBody WeatherUserRequest weatherUserRequest) {
        log.debug("Received request to update weather user: {}", weatherUserRequest);
        userService.updateAlertCriteria(weatherUserRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/weatherusers/{email}")
    public ResponseEntity<Object> updateWeatherUser(@RequestBody WeatherUserRequest weatherUserRequest,
            @PathVariable String email) {
        log.debug("Received request to update weather user: {}", weatherUserRequest);
        userService.updateWeatherUser(email, weatherUserRequest);
        return ResponseEntity.ok().build();
    }

}
