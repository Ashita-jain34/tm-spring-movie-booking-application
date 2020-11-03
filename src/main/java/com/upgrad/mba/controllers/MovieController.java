package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.MovieDTO;
import com.upgrad.mba.entities.Movie;
import com.upgrad.mba.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mba.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value= {"/movie/9"})
    public MovieDTO getMovieDetailsofId() throws MovieDetailsNotFoundException {
        Movie responseMovie = movieService.getMovieDetails(9);
        MovieDTO responseMovieDTO = modelmapper.map(responseMovie,MovieDTO.class);
        return(responseMovieDTO);
    }

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity getMovieDetails(@PathVariable(name = "id") int id) throws MovieDetailsNotFoundException {
        Movie responseMovie = movieService.getMovieDetails(id);
        MovieDTO responseMovieDTO = modelmapper.map(responseMovie,MovieDTO.class);
        return new ResponseEntity<>(responseMovieDTO, HttpStatus.OK);
    }
}
