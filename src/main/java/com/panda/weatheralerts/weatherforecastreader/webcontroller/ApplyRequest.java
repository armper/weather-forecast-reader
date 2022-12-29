package com.panda.weatheralerts.weatherforecastreader.webcontroller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplyRequest {
    private String email;
    private int windMin;
    private int windMax;
    private int tempMin;
    private int tempMax;

}
