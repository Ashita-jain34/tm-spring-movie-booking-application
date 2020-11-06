package com.upgrad.mba.validator;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.utils.DateDifference;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
@Service
public class BookingValidatorImpl implements BookingValidator {

    @Override
    public void validateBooking(BookingDTO bookingDTO) throws ParseException, APIException {
        if(bookingDTO.getCustomerId() <= 0)
            throw new APIException("Invalid userId");
        if(bookingDTO.getNoOfSeats() <= 0)
            throw new APIException("Invalid number of seats");
        if(bookingDTO.getMovieTheatreId() <= 0)
            throw new APIException("Invalid MovieTheatreID");
        int dateDifference = DateDifference.differenceBetweenDates(bookingDTO.getBookingDate(),LocalDateTime.now());
        if(dateDifference < 0 || dateDifference >= 3)
            throw new APIException("Invalid booking date");
    }
}
