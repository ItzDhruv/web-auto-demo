package org.example;

import org.openqa.selenium.WebDriver;

import static org.example.driver.DriverFactory.getDriver;

public class BaseClass {

    public static WebDriver driver;

    static {

        driver = getDriver();
    }
}