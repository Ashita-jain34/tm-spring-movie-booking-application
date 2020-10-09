package com.upgrad.mba.services;

import com.upgrad.mba.entities.Language;
import com.upgrad.mba.exceptions.LanguageDetailsNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LanguageServiceTest {

    @Autowired
    private LanguageService languageService;

    @Test
    public void testAcceptLanguageDetails() {
        Language language = new Language();
        language.setLanguageName("Language 1");
        Language savedLanguage = languageService.acceptLanguageDetails(language);

        Assertions.assertNotNull(savedLanguage);
        Assertions.assertTrue(savedLanguage.getLanguageId() != 0);
        Assertions.assertEquals("Language 1", savedLanguage.getLanguageName());
    }

    @Test
    public void testGetLanguageDetails() throws LanguageDetailsNotFoundException {
        Language language = new Language();
        language.setLanguageName("Language 2");
        language = languageService.acceptLanguageDetails(language);

        Language savedLanguage = languageService.getLanguageDetails(language.getLanguageId());
        Assertions.assertNotNull(savedLanguage);
        Assertions.assertTrue(savedLanguage.getLanguageId() != 0);
        Assertions.assertEquals("Language 2", savedLanguage.getLanguageName());
    }

    @Test
    public void testGetLanguageDetailsByLanguageName() throws LanguageDetailsNotFoundException {
        Language language = new Language();
        language.setLanguageName("Language 3");
        languageService.acceptLanguageDetails(language);

        Language savedLanguage = languageService.getLanguageDetailsByLanguageName("Language 3");
        Assertions.assertNotNull(savedLanguage);
        Assertions.assertTrue(savedLanguage.getLanguageId() != 0);
        Assertions.assertEquals("Language 3", savedLanguage.getLanguageName());
    }
}
