package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.entities.Booking;
import com.upgrad.mba.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mba.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    ModelMapper modelmapper;

    @GetMapping(value = "/bookings/{id}")
    public ResponseEntity getBookingDetails(@PathVariable(name = "id") int id) throws BookingDetailsNotFoundException {
        Booking responseBooking = bookingService.getBookingDetails(id);
        BookingDTO responseBookingDTO = modelmapper.map(responseBooking,BookingDTO.class);
        return new ResponseEntity<>(responseBookingDTO, HttpStatus.OK);
    }
}
