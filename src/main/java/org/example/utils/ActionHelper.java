package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class ActionHelper {

    private final WebDriver driver;

    private final WebDriverWait wait;

    private final Actions actions;

    public ActionHelper(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        this.actions =
                new Actions(driver);
    }

    // =========================================================
    // WAIT
    // =========================================================

    public void implicitWait(int seconds) {

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(seconds)
                );
    }

    public WebElement waitForVisible(
            By locator
    ) {

        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(locator)
        );
    }

    public WebElement waitForClickable(
            By locator
    ) {

        return wait.until(
                ExpectedConditions
                        .elementToBeClickable(locator)
        );
    }

    public boolean waitForInvisible(
            By locator
    ) {

        return wait.until(
                ExpectedConditions
                        .invisibilityOfElementLocated(locator)
        );
    }

    public boolean isVisible(
            By locator
    ) {

        try {

            return waitForVisible(locator)
                    .isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isPresent(
            By locator
    ) {

        try {

            return !driver
                    .findElements(locator)
                    .isEmpty();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isEnabled(
            By locator
    ) {

        try {

            return waitForVisible(locator)
                    .isEnabled();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isSelected(
            By locator
    ) {

        try {

            return waitForVisible(locator)
                    .isSelected();

        } catch (Exception e) {

            return false;
        }
    }

    // =========================================================
    // CLICK
    // =========================================================

    public void click(By locator) {

        waitForClickable(locator)
                .click();
    }

    public void clickIfVisible(
            By locator
    ) {

        if (isVisible(locator)) {

            driver.findElement(locator)
                    .click();
        }
    }

    // =========================================================
    // TEXT
    // =========================================================

    public void enterText(
            By locator,
            String text
    ) {

        WebElement element =
                waitForVisible(locator);

        element.clear();

        element.sendKeys(text);
    }

    public String getText(
            By locator
    ) {

        return waitForVisible(locator)
                .getText();
    }

    public void clear(By locator) {

        waitForVisible(locator)
                .clear();
    }

    // =========================================================
    // ATTRIBUTE
    // =========================================================

    public String getAttribute(
            By locator,
            String attribute
    ) {

        return waitForVisible(locator)
                .getAttribute(attribute);
    }

    // =========================================================
    // SCROLL
    // =========================================================

    public void scrollDown() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "window.scrollBy(0, 600);"
        );
    }

    public void scrollUp() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "window.scrollBy(0, -600);"
        );
    }

    public void scrollToElement(
            By locator
    ) {

        WebElement element =
                waitForVisible(locator);

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView" +
                        "({behavior:'smooth',block:'center'});",
                element
        );
    }

    // =========================================================
    // MOUSE
    // =========================================================

    public void hover(By locator) {

        WebElement element =
                waitForVisible(locator);

        actions.moveToElement(element)
                .perform();
    }

    public void doubleClick(By locator) {

        WebElement element =
                waitForClickable(locator);

        actions.doubleClick(element)
                .perform();
    }

    public void rightClick(By locator) {

        WebElement element =
                waitForClickable(locator);

        actions.contextClick(element)
                .perform();
    }

    // =========================================================
    // DRAG AND DROP
    // =========================================================

    public void dragAndDrop(
            By source,
            By target
    ) {

        WebElement sourceElement =
                waitForVisible(source);

        WebElement targetElement =
                waitForVisible(target);

        actions.dragAndDrop(
                sourceElement,
                targetElement
        ).perform();
    }

    // =========================================================
    // KEYBOARD
    // =========================================================

    public void pressEnter(By locator) {

        waitForVisible(locator)
                .sendKeys(Keys.ENTER);
    }

    public void pressEscape() {

        actions.sendKeys(Keys.ESCAPE)
                .perform();
    }

    public void pressTab(By locator) {

        waitForVisible(locator)
                .sendKeys(Keys.TAB);
    }

    // =========================================================
    // NAVIGATION
    // =========================================================

    public void openUrl(String url) {

        driver.get(url);
    }

    public void refresh() {

        driver.navigate()
                .refresh();
    }

    public void back() {

        driver.navigate()
                .back();
    }

    public void forward() {

        driver.navigate()
                .forward();
    }

    // =========================================================
    // WINDOW
    // =========================================================

    public void maximizeWindow() {

        driver.manage()
                .window()
                .maximize();
    }

    public void minimizeWindow() {

        driver.manage()
                .window()
                .minimize();
    }

    public Dimension getWindowSize() {

        return driver.manage()
                .window()
                .getSize();
    }

    // =========================================================
    // SCREENSHOT
    // =========================================================

    public File takeScreenshot(
            String fileName
    ) {

        TakesScreenshot screenshot =
                (TakesScreenshot) driver;

        File source =
                screenshot.getScreenshotAs(
                        OutputType.FILE
                );

        File destination =
                new File(
                        "screenshots/"
                                + fileName
                                + ".png"
                );

        destination
                .getParentFile()
                .mkdirs();

        try {

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to save screenshot",
                    e
            );
        }

        return destination;
    }

    // =========================================================
    // JAVASCRIPT
    // =========================================================

    public Object executeScript(
            String script,
            Object... args
    ) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        return js.executeScript(
                script,
                args
        );
    }

    // =========================================================
    // ALERT
    // =========================================================

    public void acceptAlert() {

        wait.until(
                ExpectedConditions.alertIsPresent()
        ).accept();
    }

    public void dismissAlert() {

        wait.until(
                ExpectedConditions.alertIsPresent()
        ).dismiss();
    }

    public String getAlertText() {

        return wait.until(
                ExpectedConditions.alertIsPresent()
        ).getText();
    }

    // =========================================================
    // FRAME
    // =========================================================

    public void switchToFrame(By locator) {

        WebElement frame =
                waitForVisible(locator);

        wait.until(
                ExpectedConditions
                        .frameToBeAvailableAndSwitchToIt(
                                frame
                        )
        );
    }

    public void switchToDefaultContent() {

        driver.switchTo()
                .defaultContent();
    }

    // =========================================================
    // WINDOW / TAB
    // =========================================================

    public void switchToNewWindow() {

        String currentWindow =
                driver.getWindowHandle();

        for (String window :
                driver.getWindowHandles()) {

            if (!window.equals(currentWindow)) {

                driver.switchTo()
                        .window(window);

                break;
            }
        }
    }

    public void closeCurrentWindow() {

        driver.close();
    }
}