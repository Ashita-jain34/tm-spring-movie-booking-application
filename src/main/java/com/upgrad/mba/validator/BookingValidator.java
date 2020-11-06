package com.upgrad.mba.validator;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.exceptions.APIException;

import java.text.ParseException;


public interface BookingValidator {
    public void validateBooking(BookingDTO bookingDTO) throws ParseException, APIException;
}
