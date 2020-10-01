package com.upgrad.mba;

import com.upgrad.mba.dao.*;
import com.upgrad.mba.entities.*;
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
		BookingDao bookingDao = context.getBean(BookingDao.class);
		CustomerDao customerDao = context.getBean(CustomerDao.class);

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

		Customer customer1 = new Customer();
		customer1.setFirstName("Emma");
		customer1.setLastName("Stone");
		customer1.setUsername("emmastone123");
		customer1.setPassword("imemma");
		customer1.setDateOfBirth(LocalDateTime.of(1988, 11, 6, 0, 0));
		customer1 = customerDao.save(customer1);

		Customer customer2 = new Customer();
		customer2.setFirstName("Chris");
		customer2.setLastName("Hemsworth");
		customer2.setUsername("hammer_man");
		customer2.setPassword("thor");
		customer2.setDateOfBirth(LocalDateTime.of(1983, 8, 11, 0, 0));
		customer2 = customerDao.save(customer2);

		customerDao.findAll().forEach(System.out::println);

		Booking booking1 = new Booking();
		booking1.setBookingDate(LocalDateTime.now());
		booking1.setNoOfSeats(5);
		booking1.setCustomer(customer1);
		booking1 = bookingDao.save(booking1);

		Booking booking2 = new Booking();
		booking2.setBookingDate(LocalDateTime.now());
		booking2.setNoOfSeats(2);
		booking2.setCustomer(customer1);
		booking2 = bookingDao.save(booking2);

		Booking booking3 = new Booking();
		booking3.setBookingDate(LocalDateTime.now());
		booking3.setNoOfSeats(3);
		booking3.setCustomer(customer2);
		booking3 = bookingDao.save(booking3);

		System.out.println("**************Customers***********");
		customerDao.findAll().forEach(System.out::println);

		System.out.println("**************Bookings***********");
		bookingDao.findAll().forEach(System.out::println);

		System.out.println("**************Bookings by customer1***********");
		customerDao.findById(customer1.getCustomerId())
				.ifPresent(customer -> customer.getBookings().forEach(System.out::println));
	}

}
