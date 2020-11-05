package com.upgrad.mba;

import com.upgrad.mba.entities.*;
import com.upgrad.mba.exceptions.*;
import com.upgrad.mba.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class MovieBookingApplication {


	public static void main(String[] args) throws CityDetailsNotFoundException, CustomerUserNameExistsException, UserTypeDetailsNotFoundException, CustomerDetailsNotFoundException, MovieTheatreDetailsNotFoundException, TheatreDetailsNotFoundException, MovieDetailsNotFoundException {

		ApplicationContext context = SpringApplication.run(MovieBookingApplication.class, args);
		MovieService movieService = context.getBean(MovieService.class);
		StatusService statusService = context.getBean(StatusService.class);
		CityService cityService = context.getBean(CityService.class);
		TheatreService theatreService = context.getBean(TheatreService.class);
		CustomerService customerService = context.getBean(CustomerService.class);
		BookingService bookingService = context.getBean(BookingService.class);
		UserTypeService userTypeService = context.getBean(UserTypeService.class);
		LanguageService languageService = context.getBean(LanguageService.class);
		MovieTheatreService movieTheatreService = context.getBean(MovieTheatreService.class);

		Status upcoming = new Status();
		upcoming.setStatusName("UPCOMING");
		upcoming = statusService.acceptStatusDetails(upcoming);

		Status ongoing = new Status();
		ongoing.setStatusName("ONGOING");
		ongoing = statusService.acceptStatusDetails(ongoing);

		Status deleted = new Status();
		deleted.setStatusName("DELETED");
		deleted = statusService.acceptStatusDetails(deleted);

		try {
			System.out.println("First Time");
			System.out.println(statusService.getStatusDetails(1));
			System.out.println("Second Time");
			System.out.println(statusService.getStatusDetails(1));
		} catch (Exception e) {
			System.out.println("Exception occurred.");
		}

		UserType userType1= new UserType("Customer");
		userTypeService.acceptUserTypeDetails(userType1);

		Language language1= new Language("English");
		languageService.acceptLanguageDetails(language1);

		Customer customer = new Customer();
		customer.setFirstName("Emma");
		customer.setLastName("Stone");
		customer.setUsername("emma123stone");
		customer.setPassword("emma@amme");
		customer.setDateOfBirth(LocalDateTime.of(1988, 11, 6, 0, 0));
		customer.setUserType(userType1);
		customer.setLanguage(language1);
		customerService.acceptCustomerDetails(customer);

		City city1= new City();
		city1.setCityName("Mumbai");
		cityService.acceptCityDetails(city1);

		City city2= new City();
		city2.setCityName("Delhi");
		cityService.acceptCityDetails(city2);

		Theatre theatre1 = new Theatre();
		theatre1.setTheatreName("Urvashi Cinema");
		theatre1.setTicketPrice(500);
		theatre1.setCity(cityService.getCityDetails(city1.getCityId()));
		theatre1 = theatreService.acceptTheatreDetails(theatre1);

		Theatre theatre2 = new Theatre();
		theatre2.setTheatreName("Cinepolis Multiplex");
		theatre2.setTicketPrice(600);
		theatre2.setCity(cityService.getCityDetails(city2.getCityId()));
		theatre2 = theatreService.acceptTheatreDetails(theatre2);

		Movie movie1 = new Movie();
		movie1.setMovieName("Avengers: Infinity War");
		movie1.setMovieDescription("The Avengers must stop Thanos, an intergalactic warlord, " +
				"from getting his hands on all the infinity stones.");
		movie1.setReleaseDate(LocalDateTime.of(2018, 4, 27, 5, 30));
		movie1.setDuration(150);
		movie1.setCoverPhotoUrl("cover-photo-url");
		movie1.setTrailerUrl("trailer-url");
		movie1.setStatus(upcoming);
		movie1 = movieService.acceptMovieDetails(movie1);

		Movie movie2 = new Movie();
		movie2.setMovieName("Avengers: Endgame");
		movie2.setMovieDescription("After Thanos, an intergalactic warlord, disintegrates half of " +
				"the universe, the Avengers must reunite and assemble again to reinvigorate their " +
				"trounced allies and restore balance.");
		movie2.setReleaseDate(LocalDateTime.of(2019, 4, 26, 5, 30));
		movie2.setDuration(180);
		movie2.setCoverPhotoUrl("cover-photo-url");
		movie2.setTrailerUrl("trailer-url");
		movie2.setStatus(ongoing);
		movie2 = movieService.acceptMovieDetails(movie2);

		MovieTheatre movieTheatre1 = new MovieTheatre();
		movieTheatre1.setMovie(movie1);
		movieTheatre1.setTheatre(theatre2);
		movieTheatreService.acceptMovieTheatreDetails(movieTheatre1);

		Booking booking = new Booking();
		booking.setBookingDate(LocalDateTime.of(2019, 1, 8, 0, 10));
		booking.setCustomer(customer);
		booking.setNoOfSeats(150);
		booking.setMovieTheatre(movieTheatre1);
		bookingService.acceptBookingDetails(booking);
		Set<Booking> bookingSet = new HashSet<Booking>();
		bookingSet.add(booking);
		customer.setBookings(bookingSet);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
