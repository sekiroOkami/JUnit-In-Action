package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChromeSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void init() {
        driver = new ChromeDriver();
    }

    @Test
    void testChromeManning() {
        String url = "https://www.maning.com/";
        driver.get(url);
        assertFalse(driver.getTitle().contains("Manning"));
    }

    @Test
    void testChromeGoogle() {
        String url = "https://www.google.com/";
        driver.get(url);
        assertThat(driver.getTitle(), is("Google"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
