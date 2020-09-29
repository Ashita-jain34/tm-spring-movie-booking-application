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

@SpringBootApplication
public class MovieBookingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MovieBookingApplication.class, args);
		TheatreDao theatreDao = context.getBean(TheatreDao.class);

		Theatre theatre1 = new Theatre();
		theatre1.setTheatreName("Urvashi Cinema");
		theatre1.setTicketPrice(500);

		Theatre theatre2 = new Theatre();
		theatre2.setTheatreName("Cinepolis Multiplex");
		theatre2.setTicketPrice(600);

		Theatre theatre3 = new Theatre();
		theatre3.setTheatreName("PVR Koramangla");
		theatre3.setTicketPrice(550);

		Theatre theatre4 = new Theatre();
		theatre4.setTheatreName("PVR IMAX");
		theatre4.setTicketPrice(700);

		Theatre theatre5 = new Theatre();
		theatre5.setTheatreName("INOX");
		theatre5.setTicketPrice(400);

		theatreDao.saveAll(List.of(theatre1, theatre2, theatre3, theatre4, theatre5));

		System.out.println("*************find by theatre name*************");
		theatreDao.findByTheatreName("Cinepolis Multiplex")
				.forEach(theatre -> System.out.println(theatre.getTheatreName()));

		System.out.println("*************find by ticket price less than*************");
		theatreDao.findByTicketPriceLessThan(600)
				.forEach(theatre -> System.out.println(theatre.getTheatreName()));

		System.out.println("*************find by theatre name containing*************");
		theatreDao.findByTheatreNameContaining("PVR")
				.forEach(theatre -> System.out.println(theatre.getTheatreName()));
	}

}
