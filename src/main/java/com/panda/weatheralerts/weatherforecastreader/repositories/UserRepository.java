package com.panda.weatheralerts.weatherforecastreader.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<WeatherUser, String> {

}
