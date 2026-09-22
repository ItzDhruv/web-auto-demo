package org.example.page;

import org.example.tm;
import org.openqa.selenium.By;

public class HomePage extends tm {

    public final By username =
            By.xpath("/html/body/div[1]/div/div/div/div/div/div/div[1]/div/div/div/div[1]/div/div/div[3]/div/div/div/div/div/div/div/div/div/div/div[2]/form/div/div[1]/div/div[1]/div/div/div[1]/input");

    public final By password =
            By.xpath("/html/body/div[1]/div/div/div/div/div/div/div[1]/div/div/div/div[1]/div/div/div[3]/div/div/div/div/div/div/div/div/div/div/div[2]/form/div/div[1]/div/div[2]/div/div/div[1]/input");

    public final By loginButton =
            By.xpath("/html/body/div[1]/div/div/div/div/div/div/div[1]/div/div/div/div[1]/div/div/div[3]/div/div/div/div/div/div/div/div/div/div/div[2]/form/div/div[1]/div/div[3]/div/div/div/div[1]/div/span/span");

    // =========================================================
    // USERNAME
    // =========================================================

    public void enterUsername(String value) {

        enterText(
                username,
                value
        );
    }

    // =========================================================
    // PASSWORD
    // =========================================================

    public void enterPassword(String value) {

        enterText(
                password,
                value
        );
    }

    // =========================================================
    // LOGIN BUTTON
    // =========================================================

    public void clickLogin() {

        click(loginButton);
    }

    // =========================================================
    // LOGIN
    // =========================================================

    public void login(
            String username,
            String password
    ) {

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }
}