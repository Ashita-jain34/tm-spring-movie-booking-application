package com.upgrad.mba;

import com.upgrad.mba.dao.*;
import com.upgrad.mba.entities.*;
import com.upgrad.mba.services.MovieService;
import com.upgrad.mba.services.StatusService;
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

		Status upcoming = new Status();
		upcoming.setStatusName("UPCOMING");
		upcoming = statusService.acceptStatusDetails(upcoming);

		Status ongoing = new Status();
		ongoing.setStatusName("ONGOING");
		ongoing = statusService.acceptStatusDetails(ongoing);

		Status deleted = new Status();
		deleted.setStatusName("DELETED");
		deleted = statusService.acceptStatusDetails(deleted);

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

		movieService.getAllMoviesDetails().forEach(System.out::println);
	}

}
