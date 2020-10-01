package com.upgrad.mba;

import com.upgrad.mba.dao.CityDao;
import com.upgrad.mba.dao.CustomerDao;
import com.upgrad.mba.dao.MovieDao;
import com.upgrad.mba.dao.TheatreDao;
import com.upgrad.mba.entities.City;
import com.upgrad.mba.entities.Customer;
import com.upgrad.mba.entities.Movie;
import com.upgrad.mba.entities.Theatre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MovieBookingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MovieBookingApplication.class, args);
		TheatreDao theatreDao = context.getBean(TheatreDao.class);
		CityDao cityDao = context.getBean(CityDao.class);

		City mumbai = new City();
		mumbai.setCityName("MUMBAI");
		mumbai = cityDao.save(mumbai);

		City delhi = new City();
		delhi.setCityName("DELHI");
		delhi = cityDao.save(delhi);

		Theatre theatre1 = new Theatre();
		theatre1.setTheatreName("Urvashi Cinema");
		theatre1.setTicketPrice(500);
		theatre1.setCity(mumbai);
		theatre1 = theatreDao.save(theatre1);

		Theatre theatre2 = new Theatre();
		theatre2.setTheatreName("Cinepolis Multiplex");
		theatre2.setTicketPrice(600);
		theatre2.setCity(mumbai);
		theatre2 = theatreDao.save(theatre2);

		Theatre theatre3 = new Theatre();
		theatre3.setTheatreName("PVR IMAX");
		theatre3.setTicketPrice(700);
		theatre3.setCity(delhi);
		theatre3 = theatreDao.save(theatre3);

		System.out.println("**************Cities***********");
		cityDao.findAll().forEach(System.out::println);

		System.out.println("**************Theatres***********");
		theatreDao.findAll().forEach(System.out::println);

		System.out.println("**************Theatres in Mumbai***********");
		cityDao.findById(mumbai.getCityId())
				.ifPresent(city -> city.getTheatres().forEach(System.out::println));
	}

}
