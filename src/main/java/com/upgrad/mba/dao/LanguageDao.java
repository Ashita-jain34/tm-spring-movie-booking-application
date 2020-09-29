package com.upgrad.mba.dao;

import com.upgrad.mba.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDao extends JpaRepository<Language, Integer> {
}
