package org.example;

import org.example.onfailer.ScreenshotExtension;
import org.example.utils.ActionHelper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.io.File;

@ExtendWith(ScreenshotExtension.class)
public class tm extends BaseClass {

    protected ActionHelper action;

    public tm() {

        action =
                new ActionHelper(driver);
    }

    // =========================================================
    // WAIT
    // =========================================================

    public void waitTm(int seconds) {

        action.implicitWait(seconds);
    }

    public WebElement waitForVisible(
            By locator
    ) {

        return action.waitForVisible(locator);
    }

    public WebElement waitForClickable(
            By locator
    ) {

        return action.waitForClickable(locator);
    }

    public boolean waitForInvisible(
            By locator
    ) {

        return action.waitForInvisible(locator);
    }

    public boolean isVisible(By locator) {

        return action.isVisible(locator);
    }

    public boolean isPresent(By locator) {

        return action.isPresent(locator);
    }

    public boolean isEnabled(By locator) {

        return action.isEnabled(locator);
    }

    public boolean isSelected(By locator) {

        return action.isSelected(locator);
    }

    // =========================================================
    // CLICK
    // =========================================================

    public void click(By locator) {

        action.click(locator);
    }

    public void clickIfVisible(By locator) {

        action.clickIfVisible(locator);
    }

    public void doubleClick(By locator) {

        action.doubleClick(locator);
    }

    public void rightClick(By locator) {

        action.rightClick(locator);
    }

    // =========================================================
    // TEXT
    // =========================================================

    public void enterText(
            By locator,
            String text
    ) {

        action.enterText(
                locator,
                text
        );
    }

    public String getText(By locator) {

        return action.getText(locator);
    }

    public void clear(By locator) {

        action.clear(locator);
    }

    // =========================================================
    // SCROLL
    // =========================================================

    public void scrollDown() {

        action.scrollDown();
    }

    public void scrollUp() {

        action.scrollUp();
    }

    public void scrollToElement(
            By locator
    ) {

        action.scrollToElement(locator);
    }

    // =========================================================
    // MOUSE
    // =========================================================

    public void hover(By locator) {

        action.hover(locator);
    }

    public void dragAndDrop(
            By source,
            By target
    ) {

        action.dragAndDrop(
                source,
                target
        );
    }

    // =========================================================
    // KEYBOARD
    // =========================================================

    public void pressEnter(By locator) {

        action.pressEnter(locator);
    }

    public void pressEscape() {

        action.pressEscape();
    }

    public void pressTab(By locator) {

        action.pressTab(locator);
    }

    // =========================================================
    // NAVIGATION
    // =========================================================

    public void openUrl(String url) {

        action.openUrl(url);
    }

    public void refresh() {

        action.refresh();
    }

    public void back() {

        action.back();
    }

    public void forward() {

        action.forward();
    }

    // =========================================================
    // WINDOW
    // =========================================================

    public void maximizeWindow() {

        action.maximizeWindow();
    }

    public void minimizeWindow() {

        action.minimizeWindow();
    }

    public Dimension getWindowSize() {

        return action.getWindowSize();
    }

    // =========================================================
    // SCREENSHOT
    // =========================================================

    public File takeScreenshot(
            String fileName
    ) {

        return action.takeScreenshot(
                fileName
        );
    }

    // =========================================================
    // ATTRIBUTE
    // =========================================================

    public String getAttribute(
            By locator,
            String attribute
    ) {

        return action.getAttribute(
                locator,
                attribute
        );
    }

    // =========================================================
    // ALERT
    // =========================================================

    public void acceptAlert() {

        action.acceptAlert();
    }

    public void dismissAlert() {

        action.dismissAlert();
    }

    public String getAlertText() {

        return action.getAlertText();
    }

    // =========================================================
    // FRAME
    // =========================================================

    public void switchToFrame(By locator) {

        action.switchToFrame(locator);
    }

    public void switchToDefaultContent() {

        action.switchToDefaultContent();
    }

    // =========================================================
    // WINDOW / TAB
    // =========================================================

    public void switchToNewWindow() {

        action.switchToNewWindow();
    }

    public void closeCurrentWindow() {

        action.closeCurrentWindow();
    }
}