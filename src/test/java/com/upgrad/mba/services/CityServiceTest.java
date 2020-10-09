package com.upgrad.mba.services;

import com.upgrad.mba.entities.City;
import com.upgrad.mba.exceptions.CityDetailsNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CityServiceTest {
    @Autowired
    private CityService cityService;

    @Test
    public void testAcceptCityDetails() {
        City city = new City();
        city.setCityName("Test 1");
        City savedCity = cityService.acceptCityDetails(city);
        Assertions.assertNotNull(savedCity);
        Assertions.assertTrue(savedCity.getCityId() != 0);
        Assertions.assertEquals("Test 1", savedCity.getCityName());
    }

    @Test
    public void testUpdateCityDetails() throws CityDetailsNotFoundException {
        City city = new City();
        city.setCityName("Test 2");
        City savedCity = cityService.acceptCityDetails(city);

        City updateCity = new City();
        updateCity.setCityName("Test 3");
        City updatedCity = cityService.updateCityDetails(savedCity.getCityId(), updateCity);

        Assertions.assertNotNull(updatedCity);
        Assertions.assertTrue(updatedCity.getCityId() != 0);
        Assertions.assertEquals("Test 3", updatedCity.getCityName());
        Assertions.assertEquals(updatedCity.getCityId(), savedCity.getCityId());
        Assertions.assertNotEquals(updatedCity.getCityName(), savedCity.getCityName());
    }

    @Test
    public void testGetCityDetailsByCityName() throws CityDetailsNotFoundException {
        City city = new City();
        city.setCityName("Test 4");
        cityService.acceptCityDetails(city);

        City savedCity = cityService.getCityDetailsByCityName("Test 4");
        Assertions.assertNotNull(savedCity);
        Assertions.assertTrue(savedCity.getCityId() != 0);
        Assertions.assertEquals("Test 4", savedCity.getCityName());
    }
}
