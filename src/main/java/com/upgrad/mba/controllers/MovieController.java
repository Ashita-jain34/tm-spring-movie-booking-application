package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.MovieDTO;
import com.upgrad.mba.entities.Movie;
import com.upgrad.mba.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mba.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/movie_app/v1")
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    ModelMapper modelmapper;

    @GetMapping(value= {"/sayHelloMovie"})
    public String sayHello(){
        return "Hello World To All From MovieController";
    }

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity getMovieDetails(@PathVariable(name = "id") int id) throws MovieDetailsNotFoundException {
        Movie responseMovie = movieService.getMovieDetails(id);
        MovieDTO responseMovieDTO = modelmapper.map(responseMovie,MovieDTO.class);
        return new ResponseEntity<>(responseMovieDTO, HttpStatus.OK);
    }
    
    @GetMapping(value="/movies",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMovies() {
        List<Movie> movieList = movieService.getAllMoviesDetails();
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for(Movie movie : movieList){
            movieDTOList.add(modelmapper.map(movie,MovieDTO.class));
        }
        return new ResponseEntity<>(movieDTOList, HttpStatus.OK);
    }

}
