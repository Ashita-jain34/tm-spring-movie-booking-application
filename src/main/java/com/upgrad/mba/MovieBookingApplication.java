package com.upgrad.mba;

import com.upgrad.mba.dao.*;
import com.upgrad.mba.entities.*;
import com.upgrad.mba.services.CityService;
import com.upgrad.mba.services.MovieService;
import com.upgrad.mba.services.StatusService;
import com.upgrad.mba.services.TheatreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MovieBookingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MovieBookingApplication.class, args);
		MovieService movieService = context.getBean(MovieService.class);
		StatusService statusService = context.getBean(StatusService.class);
		CityService cityService = context.getBean(CityService.class);
		TheatreService theatreService = context.getBean(TheatreService.class);

//		Status upcoming = new Status();
//		upcoming.setStatusName("UPCOMING");
//		upcoming = statusService.acceptStatusDetails(upcoming);
//
//		Status ongoing = new Status();
//		ongoing.setStatusName("ONGOING");
//		ongoing = statusService.acceptStatusDetails(ongoing);
//
//		Status deleted = new Status();
//		deleted.setStatusName("DELETED");
//		deleted = statusService.acceptStatusDetails(deleted);

		try {
			System.out.println("First Time");
			System.out.println(statusService.getStatusDetails(1));
			System.out.println("Second Time");
			System.out.println(statusService.getStatusDetails(1));
		} catch (Exception e) {
			System.out.println("Exception occurred.");
		}

//		City mumbai = new City();
//		mumbai.setCityName("MUMBAI");
//		//mumbai = cityService.acceptCityDetails(mumbai);
//
//		City delhi = new City();
//		delhi.setCityName("DELHI");
//		//delhi = cityService.acceptCityDetails(delhi);
//		List<City> savedCities = cityService.acceptMultipleCityDetails(Arrays.asList(mumbai, delhi));
//
//		Theatre theatre1 = new Theatre();
//		theatre1.setTheatreName("Urvashi Cinema");
//		theatre1.setTicketPrice(500);
//		theatre1.setCity(savedCities.get(0));
//		theatre1 = theatreService.acceptTheatreDetails(theatre1);
//
//		Theatre theatre2 = new Theatre();
//		theatre2.setTheatreName("Cinepolis Multiplex");
//		theatre2.setTicketPrice(600);
//		theatre2.setCity(savedCities.get(0));
//		theatre2 = theatreService.acceptTheatreDetails(theatre2);
//
//		Theatre theatre3 = new Theatre();
//		theatre3.setTheatreName("PVR IMAX");
//		theatre3.setTicketPrice(700);
//		theatre3.setCity(savedCities.get(1));
//		theatre3 = theatreService.acceptTheatreDetails(theatre3);
//
//		Movie movie1 = new Movie();
//		movie1.setMovieName("Avengers: Infinity War");
//		movie1.setMovieDescription("The Avengers must stop Thanos, an intergalactic warlord, " +
//				"from getting his hands on all the infinity stones.");
//		movie1.setReleaseDate(LocalDateTime.of(2018, 4, 27, 5, 30));
//		movie1.setDuration(150);
//		movie1.setCoverPhotoUrl("cover-photo-url");
//		movie1.setTrailerUrl("trailer-url");
//		movie1.setStatus(upcoming);
//		movie1 = movieService.acceptMovieDetails(movie1);
//
//		Movie movie2 = new Movie();
//		movie2.setMovieName("Avengers: Endgame");
//		movie2.setMovieDescription("After Thanos, an intergalactic warlord, disintegrates half of " +
//				"the universe, the Avengers must reunite and assemble again to reinvigorate their " +
//				"trounced allies and restore balance.");
//		movie2.setReleaseDate(LocalDateTime.of(2019, 4, 26, 5, 30));
//		movie2.setDuration(180);
//		movie2.setCoverPhotoUrl("cover-photo-url");
//		movie2.setTrailerUrl("trailer-url");
//		movie2.setStatus(ongoing);
//		movie2 = movieService.acceptMovieDetails(movie2);
//
//		movieService.getAllMoviesDetails().forEach(System.out::println);
//
//		theatreService.getAllTheatreDetails().forEach(System.out::println);
	}

}
