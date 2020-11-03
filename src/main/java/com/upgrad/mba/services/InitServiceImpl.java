package com.upgrad.mba.services;

import com.upgrad.mba.entities.*;
import com.upgrad.mba.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("InitService")
public class InitServiceImpl<movie1, theatre1> implements InitService{
    @Autowired
    @Qualifier("cityDao")
    CityDao cityDao;

    @Autowired
    @Qualifier("userTypeDao")
    UserTypeDao userTypeDao;

    @Autowired
    @Qualifier("languageDao")
    LanguageDao languageDao;

    @Autowired
    @Qualifier("statusDao")
    StatusDao statusDao;

    List<City> cities = Arrays.asList(new City("Patna"), new City("Mumbai"), new City("Kolkata"), new City("Bangalore"));
    List<UserType> userTypes = Arrays.asList(new UserType("Customer"), new UserType("Admin"));
    List<Language> languages = Arrays.asList(new Language("English"), new Language("Hindi"), new Language("Bengali"));

    @Override
    public void init() {
        cities.forEach(city -> cityDao.save(city));
        userTypes.forEach(userType -> userTypeDao.save(userType));
        languages.forEach(language -> languageDao.save(language));
    }


}
