package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        // Headed mode
        options.addArguments("--start-maximized");

        // Launch Chrome
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open Reddit
            driver.get("https://www.reddit.com/");

            // Wait until browser reports that the page is loaded
            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(20)
            );

            wait.until(webDriver ->
                    ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete")
            );

            System.out.println("Reddit opened successfully.");
            System.out.println("Title: " + driver.getTitle());
            System.out.println("URL: " + driver.getCurrentUrl());


        } finally {
            driver.quit();
        }
    }
}