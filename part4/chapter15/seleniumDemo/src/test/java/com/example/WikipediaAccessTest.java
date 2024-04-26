package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WikipediaAccessTest {
    private WebDriver driver;

    @BeforeEach
    void init() {
        driver = new FirefoxDriver();
    }
//    @Test
//    void testWikipediaAccess() {
//        driver.get("https://en.wikipedia.org/");
//        assertThat(driver.getTitle(),
//                is("Wikipedia, the free encyclopedia"));
//        WebElement contents = driver.findElementByLinkText("Contents");
//        assertTrue(contents.isDisplayed());
//        contents.click();
//        assertThat(driver.getTitle(),
//                is("Wikipedia:Contents - Wikipedia "));
//    }
    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
