package com.panda.weatheralerts.weatherforecastreader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.panda.weatheralerts.weatherforecastreader.repositories.UserRepository;
import com.panda.weatheralerts.weatherforecastreader.repositories.WeatherUser;
import com.panda.weatheralerts.weatherforecastreader.services.WeatherApiProcessor;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableScheduling
@EnableAdminServer
public class WeatherForecastReaderApplication {

	@Autowired
	WeatherApiProcessor weatherApiProcessor;

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecastReaderApplication.class, args);
	}

	// Populate User table with test data spring boot command line runner
	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return args -> {
			/* repository.deleteAll();

			List<WeatherUser> testUsers = new ArrayList<>();
			testUsers.add(generateRandomTestUser());

			repository.save(generateRandomTestUser());

			weatherApiProcessor.processWeatherApi(); */

		};

	}

	// Method to Generate random test user
	private WeatherUser generateRandomTestUser() {
		WeatherUser user = new WeatherUser();
		user.setEmail("aleoperea@yahoo.com");
		user.setLat(38.8894);
		user.setLon(-77.0352);

		return user;
	}

}
