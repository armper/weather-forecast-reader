package com.panda.weatheralerts.weatherforecastreader.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WindSpeedRange {
    Integer minWindSpeed;
    Integer maxWindSpeed;
}
