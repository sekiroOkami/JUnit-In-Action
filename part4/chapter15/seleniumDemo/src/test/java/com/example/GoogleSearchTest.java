package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {
    private WebDriver driver;

    public static Collection<RemoteWebDriver> getBrowserVersions() {
        return Arrays.asList(
                new RemoteWebDriver[] {
                        new FirefoxDriver(),
                        new ChromeDriver(),
                        new InternetExplorerDriver()
                }
        );
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testGoogleSearch(RemoteWebDriver driver) {
        driver.get("https://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("en.wikipedia.org");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        // wait until the Google page shows the result
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));

        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//a/h3"));

        findElements.get(0).click();

        assertEquals("https://en.wikipedia.org/wiki/Main_Page", driver.getCurrentUrl());
        assertThat(driver.getTitle(), is("Wikipedia, the free encyclopedia"));

        WebElement contents = driver.findElementByLinkText("Contents");
        assertTrue(contents.isDisplayed());
        contents.click();

        assertThat(driver.getTitle(), is("Wikipedia:Contents - Wikipedia"));
    }

    @AfterEach
    void tearDown() {
        driver.close();
    }
}
