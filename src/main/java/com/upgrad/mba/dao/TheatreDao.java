package com.upgrad.mba.dao;

import com.upgrad.mba.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreDao extends JpaRepository<Theatre, Integer> {
}
