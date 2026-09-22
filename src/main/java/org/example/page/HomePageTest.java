package org.example.tests;

import org.example.page.HomePage;
import org.example.tm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.configreader.ConfigReader.getValue;

public class HomePageTest extends tm {

    private HomePage homePage;

    @BeforeEach
    public void setup() {

        homePage = new HomePage();

        openUrl(getValue("tm.url"));
    }

    @Test
    public void loginTest() {

        homePage.login(
                "test@example.com",
                "password123"
        );
    }
}