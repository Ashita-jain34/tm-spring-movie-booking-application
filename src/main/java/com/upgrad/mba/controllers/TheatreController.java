package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.TheatreDTO;
import com.upgrad.mba.entities.Theatre;
import com.upgrad.mba.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mba.services.TheatreService;
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
public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @Autowired
    ModelMapper modelmapper;

    @GetMapping(value = "/theatres/{id}")
    public ResponseEntity getTheatreDetails(@PathVariable(name = "id") int id) throws TheatreDetailsNotFoundException {
        Theatre responseTheatre = theatreService.getTheatreDetails(id);
        TheatreDTO responseTheatreDTO = modelmapper.map(responseTheatre,TheatreDTO.class);
        return new ResponseEntity<>(responseTheatreDTO, HttpStatus.OK);
    }
}
