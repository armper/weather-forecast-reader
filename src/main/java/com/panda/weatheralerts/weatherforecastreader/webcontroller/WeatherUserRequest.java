package com.panda.weatheralerts.weatherforecastreader.webcontroller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WeatherUserRequest {
    private String email;
    private Integer windMin;
    private Integer windMax;
    private Integer tempMin;
    private Integer tempMax;

}
