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

}
