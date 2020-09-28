package com.upgrad.mba;

import com.upgrad.mba.dao.CityDao;
import com.upgrad.mba.dao.CustomerDao;
import com.upgrad.mba.dao.MovieDao;
import com.upgrad.mba.entities.City;
import com.upgrad.mba.entities.Customer;
import com.upgrad.mba.entities.Movie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class MovieBookingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MovieBookingApplication.class, args);
		MovieDao movieDao = context.getBean(MovieDao.class);
		Movie movie = new Movie();
		movie.setMovieName("Avengers: Infinity War");
		movie.setMovieDescription("The Avengers must stop Thanos, an intergalactic warlord, " +
				"from getting his hands on all the infinity stones.");
		movie.setReleaseDate(LocalDateTime.of(2018, 4, 27, 5, 30));
		movie.setDuration(150);
		movie.setCoverPhotoUrl("cover-photo-url");
		movie.setTrailerUrl("trailer-url");

		System.out.println("Before Saving: " + movie);

		Movie savedMovie = movieDao.save(movie);
		System.out.println("After saving: " + savedMovie);

		Movie retrievedMovie = movieDao.findById(savedMovie.getMovieId()).orElse(null);
		System.out.println("After retrieving: " + retrievedMovie);

		movie.setDuration(160);
		Movie updatedMovie = movieDao.save(movie);
		System.out.println("After updating: " + updatedMovie);

		movieDao.delete(updatedMovie);
		System.out.println("After deleting: " + movieDao.findById(updatedMovie.getMovieId()));
	}

}
