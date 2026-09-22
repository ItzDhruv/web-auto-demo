package org.example.driver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {

            driver =
                    DriverManager.getDriver();
        }

        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {

            driver.quit();
            driver = null;
        }
    }
}