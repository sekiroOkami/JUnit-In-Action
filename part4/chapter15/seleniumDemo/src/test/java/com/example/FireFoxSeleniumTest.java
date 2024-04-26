package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FireFoxSeleniumTest {
    private WebDriver driver;

    @BeforeEach
    void init() {
        driver = new FirefoxDriver();
    }

    @Test
    void testFireFoxManning() {
        String url = "https://www.maning.com/";
        driver.get(url);
        assertFalse(driver.getTitle().contains("Manning"));
    }

    @Test
    void testFireFoxGoogle() {
        String url = "https://www.google.com/";
        driver.get(url);
        assertThat(driver.getTitle(), is("Google"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


}
