package com.upgrad.mba.validator;

import com.upgrad.mba.dto.MovieDTO;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.StatusDetailsNotFoundException;

import java.text.ParseException;

public interface MovieValidator {
    public void validateMovie(MovieDTO movieDTO) throws APIException, ParseException, StatusDetailsNotFoundException;
}
