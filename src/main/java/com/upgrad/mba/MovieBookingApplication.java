package com.upgrad.mba;

import com.upgrad.mba.dao.CityDao;
import com.upgrad.mba.dao.CustomerDao;
import com.upgrad.mba.entities.City;
import com.upgrad.mba.entities.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class MovieBookingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MovieBookingApplication.class, args);
		CityDao cityDao = context.getBean(CityDao.class);
		City city = new City();
		city.setCityName("Delhi");

		System.out.println("Before Saving: " + city);

		City savedCity = cityDao.save(city);
		System.out.println("After saving: " + savedCity);

		City retrievedCity = cityDao.findById(savedCity.getCityId());
		System.out.println("After retrieving: " + retrievedCity);

		city.setCityName("Mumbai");
		City updatedCity = cityDao.update(city);
		System.out.println("After updating: " + updatedCity);

		cityDao.delete(updatedCity);
		System.out.println("After deleting: " + cityDao.findById(updatedCity.getCityId()));
	}

}
