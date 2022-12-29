package com.panda.weatheralerts.weatherforecastreader;

import org.junit.jupiter.api.Test;
import com.panda.weatheralerts.weatherforecastreader.services.criteria.WeatherAlertCriteria;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherAlertGeneratorTest {

  @Test
  public void testWeatherAlertCriteria() {

    var weatherAlertCriteria = new WeatherAlertCriteria(35);
    assertTrue(weatherAlertCriteria.isGreaterThanOrEqualTo(30));
    assertFalse(weatherAlertCriteria.isLessThanOrEqualTo(30));

    weatherAlertCriteria = new WeatherAlertCriteria(30);
    assertTrue(weatherAlertCriteria.isLessThanOrEqualTo(35));
    assertFalse(weatherAlertCriteria.isGreaterThanOrEqualTo(35));

    weatherAlertCriteria = new WeatherAlertCriteria(30);
    assertTrue(weatherAlertCriteria.isWithin(25, 35));
    assertFalse(weatherAlertCriteria.isWithin(25, 29));
    assertFalse(weatherAlertCriteria.isWithin(31, 35));

  }

}
