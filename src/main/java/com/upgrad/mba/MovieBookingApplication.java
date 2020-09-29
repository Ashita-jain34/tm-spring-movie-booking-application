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
		MovieDao movieDao = context.getBean(MovieDao.class);

		Movie movie1 = new Movie();
		movie1.setMovieName("Avengers: Infinity War");
		movie1.setMovieDescription("The Avengers must stop Thanos, an intergalactic warlord, " +
				"from getting his hands on all the infinity stones.");
		movie1.setReleaseDate(LocalDateTime.of(2018, 4, 27, 5, 30));
		movie1.setDuration(150);
		movie1.setCoverPhotoUrl("cover-photo-url");
		movie1.setTrailerUrl("trailer-url");

		Movie movie2 = new Movie();
		movie2.setMovieName("Avengers: Endgame");
		movie2.setMovieDescription("After Thanos, an intergalactic warlord, disintegrates half of " +
				"the universe, the Avengers must reunite and assemble again to reinvigorate their " +
				"trounced allies and restore balance.");
		movie2.setReleaseDate(LocalDateTime.of(2019, 4, 26, 5, 30));
		movie2.setDuration(180);
		movie2.setCoverPhotoUrl("cover-photo-url");
		movie2.setTrailerUrl("trailer-url");

		Movie movie3 = new Movie();
		movie3.setMovieName("Black Panther");
		movie3.setMovieDescription("After his father's death, T'Challa returns home to Wakanda to " +
				"inherit his throne. However, a powerful enemy related to his family threatens to " +
				"attack his nation.");
		movie3.setReleaseDate(LocalDateTime.of(2018, 1, 29, 5, 30));
		movie3.setDuration(150);
		movie3.setCoverPhotoUrl("cover-photo-url");
		movie3.setTrailerUrl("trailer-url");

		Movie movie4 = new Movie();
		movie4.setMovieName("Spider-Man: Into the Spider-Verse");
		movie4.setMovieDescription("After gaining superpowers from a spider bite, Miles Morales protects" +
				" the city as Spider-Man. Soon, he meets alternate versions of himself and gets embroiled" +
				" in an epic battle to save the multiverse.");
		movie4.setReleaseDate(LocalDateTime.of(2018, 12, 14, 5, 30));
		movie4.setDuration(120);
		movie4.setCoverPhotoUrl("cover-photo-url");
		movie4.setTrailerUrl("trailer-url");

		Movie movie5 = new Movie();
		movie5.setMovieName("Deadpool 2");
		movie5.setMovieDescription("Deadpool protects a young mutant Russell from the authorities and gets" +
				" thrown in prison. However, he escapes and forms a team of mutants to prevent a time-travelling" +
				" mercenary from killing Russell.");
		movie5.setReleaseDate(LocalDateTime.of(2018, 5, 1, 5, 30));
		movie5.setDuration(150);
		movie5.setCoverPhotoUrl("cover-photo-url");
		movie5.setTrailerUrl("trailer-url");

		movieDao.saveAll(List.of(movie1, movie2, movie3, movie4, movie5));

		System.out.println("*************find by movie name*************");
		movieDao.findByMovieName("Black Panther")
				.forEach(movie -> System.out.println(movie.getMovieName()));

		System.out.println("*************find by movie name and duration*************");
		movieDao.findByMovieNameAndDuration("Deadpool 2", 150)
				.forEach(movie -> System.out.println(movie.getMovieName()));

		System.out.println("*************find by release date between*************");
		movieDao.findByReleaseDateBetween(
				LocalDateTime.of(2018, 3, 1, 0, 0),
				LocalDateTime.of(2019, 3, 1, 0, 0))
				.forEach(movie -> System.out.println(movie.getMovieName()));

		System.out.println("*************find by duration greater than or equal*************");
		movieDao.findByDurationGreaterThanEqual(150)
				.forEach(movie -> System.out.println(movie.getMovieName()));

		System.out.println("*************find by release date after*************");
		movieDao.findByReleaseDateAfter(LocalDateTime.of(2018, 3, 1, 0, 0))
				.forEach(movie -> System.out.println(movie.getMovieName()));

		System.out.println("*************find by movie name containing*************");
		movieDao.findByMovieNameContaining("Avengers")
				.forEach(movie -> System.out.println(movie.getMovieName()));

		System.out.println("*************find by movie name (incorrect case)*************");
		movieDao.findByMovieName("spider-man: into the spider-verse")
				.forEach(movie -> System.out.println(movie.getMovieName()));

		System.out.println("*************find by movie name ignore case (incorrect case)*************");
		movieDao.findByMovieNameIgnoreCase("spider-man: into the spider-verse")
				.forEach(movie -> System.out.println(movie.getMovieName()));
	}

}
