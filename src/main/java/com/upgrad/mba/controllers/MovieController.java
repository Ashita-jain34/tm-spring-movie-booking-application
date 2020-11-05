package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.MovieDTO;
import com.upgrad.mba.entities.Movie;
import com.upgrad.mba.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mba.services.MovieService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/movie_app/v1")
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    ModelMapper modelmapper;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping(value= {"/sayHelloMovie"})
    public String sayHello(){
        logger.info("Hello from the MovieController");
        return "Hello World To All From MovieController";
    }

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity getMovieDetails(@PathVariable(name = "id") int id) throws MovieDetailsNotFoundException {
        Movie responseMovie = movieService.getMovieDetails(id);
        MovieDTO responseMovieDTO = modelmapper.map(responseMovie,MovieDTO.class);
        logger.debug("Get movie details :" + responseMovieDTO);
        return new ResponseEntity<>(responseMovieDTO, HttpStatus.OK);
    }

    @GetMapping(value="/movies",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMovies() {
        List<Movie> movieList = movieService.getAllMoviesDetails();
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for(Movie movie : movieList){
            movieDTOList.add(modelmapper.map(movie,MovieDTO.class));
        }
        logger.debug("Returning all movies" , movieDTOList);
        return new ResponseEntity<>(movieDTOList, HttpStatus.OK);
    }

    @PostMapping(value="/movies", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newMovie(@RequestBody MovieDTO movieDTO) {
        Movie newMovie = modelmapper.map(movieDTO, Movie.class);
        Movie savedMovie = movieService.acceptMovieDetails(newMovie);
        MovieDTO savedMovieDTO = modelmapper.map(savedMovie,MovieDTO.class);
        logger.debug("Accepted new movie details",savedMovieDTO);
        return new ResponseEntity<>(savedMovieDTO,HttpStatus.CREATED);
    }

    @PutMapping(value="/movies/{id}",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateMovieDetails(@PathVariable(name = "id") int id, @RequestBody MovieDTO movieDTO) throws MovieDetailsNotFoundException {
        logger.debug("Update movie details : movie id :" + id, movieDTO);
        Movie newMovie = modelmapper.map(movieDTO, Movie.class);
        Movie updatedMovie = movieService.updateMovieDetails(id, newMovie);
        MovieDTO updatedMovieDTO = modelmapper.map(updatedMovie, MovieDTO.class);
        return new ResponseEntity<>(updatedMovieDTO,HttpStatus.OK);
    }
}
