package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.CustomerDTO;
import com.upgrad.mba.entities.Customer;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.CustomException;
import com.upgrad.mba.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mba.exceptions.CustomerUserNameExistsException;
import com.upgrad.mba.services.CustomerServiceImpl;
import com.upgrad.mba.validator.CustomerValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/movie_app/v1")
public class AuthController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    CustomerValidator customerValidator;

    @PostMapping(value = "/signup",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signup(@RequestBody CustomerDTO customerDTO)
            throws APIException, CustomerUserNameExistsException, CustomException {

        customerValidator.validateCustomer(customerDTO);
        try {
            Customer customer = customerService.getCustomerDetailsByUsername(customerDTO.getUsername());
            if (customer != null) {
                throw new CustomerUserNameExistsException(
                        "Customer username already exists for : " + customerDTO.getUsername());
            }
        } catch (CustomerDetailsNotFoundException ex) {
            System.out.println("Customer does not exist for the given details");
        }
        try {
            Map<String, String> model = new HashMap<>();
            String username = customerDTO.getUsername();
            String password = customerDTO.getPassword();
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                model.put("Error", "Username is invalid/ Password is empty");
                return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
            }
            Customer newCustomer = modelMapper.map(customerDTO,Customer.class);
            Customer savedCustomer = customerService.acceptCustomerDetails(newCustomer);
            CustomerDTO savedCustomerDTO = modelMapper.map(savedCustomer,CustomerDTO.class);
            return new ResponseEntity<>(savedCustomerDTO,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new CustomException("Username " + customerDTO.getUsername() + " already registered",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
