package com.upgrad.mba.aspects;

import com.upgrad.mba.exceptions.BookingFailedException;
import com.upgrad.mba.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookingFailedExceptionAspect {
    @ExceptionHandler(BookingFailedException.class)
    public ResponseEntity<CustomResponse> handleBookingFailedException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }
}