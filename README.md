
# Web Automation Framework

A Java-based web automation framework built using **Selenium WebDriver**, **JUnit 5**, and **Maven**.

The framework is designed to provide a reusable and maintainable structure for web application automation.

It provides reusable utilities for:

- Browser automation
- Chrome, Firefox and Edge support
- Configuration management
- Selenium WebDriver management
- Implicit and explicit waits
- Element visibility and presence checks
- Click and text operations
- Mouse actions
- Hover
- Double click
- Right click
- Drag and drop
- Keyboard actions
- Scrolling
- Browser navigation
- Window and tab handling
- iframe handling
- JavaScript execution
- Alert handling
- Screenshots
- Automatic screenshots on test failure
- Page Object Model
- JUnit 5 test execution

---

# Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Web automation |
| JUnit 5 | Test framework |
| Maven | Dependency management |
| Chrome | Browser |
| Firefox | Browser |
| Edge | Browser |
| IntelliJ IDEA | Recommended IDE |

---

# Project Structure

```text
src
├── main
│   ├── java
│   │   └── org.example
│   │       │
│   │       ├── configreader
│   │       │   └── ConfigReader.java
│   │       │
│   │       ├── driver
│   │       │   ├── DriverFactory.java
│   │       │   └── DriverManager.java
│   │       │
│   │       ├── onfailer
│   │       │   └── ScreenshotExtension.java
│   │       │
│   │       ├── page
│   │       │   └── HomePage.java
│   │       │
│   │       ├── utils
│   │       │   └── ActionHelper.java
│   │       │
│   │       ├── BaseClass.java
│   │       └── tm.java
│   │
│   └── resources
│       └── config.properties
│
├── test
│   └── java
│       └── org.example.tests
│           └── HomePageTest.java
│
├── screenshots
│   └── Failure screenshots
│
├── pom.xml
└── README.md
```

---

# Framework Architecture

```text
                         Test Class
                             │
                             ▼
                            tm
                             │
                             ▼
                        BaseClass
                             │
                             ▼
                       DriverFactory
                             │
                             ▼
                       DriverManager
                             │
                             ▼
                        WebDriver
                             │
             ┌───────────────┼───────────────┐
             ▼               ▼               ▼
          Chrome          Firefox           Edge
```

Utility flow:

```text
Test
 │
 ├── click()
 ├── enterText()
 ├── scrollDown()
 ├── hover()
 ├── doubleClick()
 ├── rightClick()
 ├── dragAndDrop()
 ├── back()
 ├── forward()
 └── takeScreenshot()
          │
          ▼
     ActionHelper
          │
          ▼
      WebDriver
```

Failure flow:

```text
JUnit Test
     │
     ▼
Test fails
     │
     ▼
ScreenshotExtension
     │
     ▼
ActionHelper
     │
     ▼
takeScreenshot()
     │
     ▼
screenshots/
```

---

# Prerequisites

Install the following before running the framework.

## Java

Recommended:

```text
Java 17+
```

Check:

```bash
java -version
```

---

## Maven

Check:

```bash
mvn -version
```

---

## Browsers

Install at least one supported browser:

```text
Google Chrome
Mozilla Firefox
Microsoft Edge
```

The framework can select the browser through `config.properties`.

---

# Selenium Manager

Modern Selenium versions include Selenium Manager.

Therefore, in most cases, you do not need to manually download:

```text
chromedriver
geckodriver
msedgedriver
```

Selenium Manager automatically manages the required browser driver.

---

# Configuration

Configuration is stored in:

```text
src/main/resources/config.properties
```

Example:

```properties
tm.browser=chrome

tm.url=https://www.facebook.com

tm.headless=false

tm.implicitWait=5

tm.explicitWait=20

tm.window.maximize=true

tm.screenshot.path=screenshots
```

---

# Configuration Properties

| Property | Description |
|---|---|
| `tm.browser` | Browser to execute the test |
| `tm.url` | Application URL |
| `tm.headless` | Run browser without UI |
| `tm.implicitWait` | Global implicit wait |
| `tm.explicitWait` | Explicit wait timeout |
| `tm.window.maximize` | Maximize browser window |
| `tm.screenshot.path` | Screenshot directory |

Supported browsers:

```text
chrome
firefox
edge
```

---

# ConfigReader

`ConfigReader` loads values from:

```text
config.properties
```

Example:

```java
String browser =
        ConfigReader.getValue(
                "tm.browser"
        );
```

Get URL:

```java
String url =
        ConfigReader.getValue(
                "tm.url"
        );
```

Get a value with a default:

```java
String value =
        ConfigReader.get(
                "some.property",
                "defaultValue"
        );
```

---

# Driver Management

The framework separates browser creation into:

```text
DriverFactory
      │
      └── DriverManager
```

`DriverManager` creates the browser driver.

`DriverFactory` provides the driver to the framework.

Architecture:

```text
ConfigReader
     │
     ▼
DriverFactory
     │
     ▼
DriverManager
     │
     ├── ChromeDriver
     ├── FirefoxDriver
     └── EdgeDriver
```

---

# Browser Selection

The browser can be changed without modifying Java code.

For Chrome:

```properties
tm.browser=chrome
```

For Firefox:

```properties
tm.browser=firefox
```

For Edge:

```properties
tm.browser=edge
```

This allows the same test suite to run against different browsers.

---

# BaseClass

`BaseClass` maintains the WebDriver instance.

Example:

```java
package org.example;

import org.openqa.selenium.WebDriver;

import static org.example.driver.DriverFactory.getDriver;

public class BaseClass {

    public static WebDriver driver;

    static {
        driver = getDriver();
    }
}
```

---

# tm.java

`tm` acts as a reusable framework facade.

Tests and Page Objects can use methods such as:

```java
click();

enterText();

waitForVisible();

scrollDown();

hover();

doubleClick();

rightClick();

back();

forward();

refresh();

takeScreenshot();
```

Instead of directly interacting with Selenium everywhere.

---

# ActionHelper

`ActionHelper` contains the reusable Selenium operations.

It is the main utility layer of the framework.

Example:

```java
click(locator);
```

instead of:

```java
driver.findElement(locator).click();
```

This keeps test code clean and makes framework-level changes easier.

---

# Wait Utilities

## Implicit Wait

The framework supports implicit waits.

Example:

```java
waitTm(5);
```

Internally:

```java
driver.manage()
      .timeouts()
      .implicitlyWait(
          Duration.ofSeconds(5)
      );
```

Implicit wait applies globally to element searches.

---

# Explicit Wait

The framework uses:

```java
WebDriverWait
```

for explicit waits.

---

## Wait For Visible

```java
waitForVisible(locator);
```

Example:

```java
waitForVisible(
    By.id("username")
);
```

---

## Wait For Clickable

```java
waitForClickable(locator);
```

Example:

```java
waitForClickable(
    By.id("login")
);
```

---

## Wait For Invisible

```java
waitForInvisible(locator);
```

Example:

```java
waitForInvisible(
    By.id("loading")
);
```

---

# Element Validation

## Check Visibility

```java
if (isVisible(locator)) {

    // Element is visible
}
```

---

## Check Presence

```java
if (isPresent(locator)) {

    // Element exists in DOM
}
```

---

## Check Enabled

```java
if (isEnabled(locator)) {

    // Element is enabled
}
```

---

## Check Selected

```java
if (isSelected(locator)) {

    // Element is selected
}
```

---

# Click

```java
click(locator);
```

Example:

```java
click(
    By.id("login")
);
```

The framework first waits for the element to become clickable.

---

# Conditional Click

```java
clickIfVisible(locator);
```

Example:

```java
clickIfVisible(
    By.id("close")
);
```

The click occurs only if the element is visible.

---

# Enter Text

```java
enterText(
    locator,
    "test@example.com"
);
```

Example:

```java
enterText(
    By.id("username"),
    "test@example.com"
);
```

The framework:

```text
Find element
     ↓
Wait for visible
     ↓
Clear existing text
     ↓
Enter new text
```

---

# Get Text

```java
String text =
        getText(locator);
```

Example:

```java
String title =
        getText(
            By.id("title")
        );
```

---

# Clear Text

```java
clear(locator);
```

---

# Get Attribute

```java
String value =
        getAttribute(
            locator,
            "value"
        );
```

Other examples:

```java
getAttribute(locator, "href");

getAttribute(locator, "class");

getAttribute(locator, "id");

getAttribute(locator, "data-testid");
```

---

# Scrolling

## Scroll Down

```java
scrollDown();
```

---

## Scroll Up

```java
scrollUp();
```

---

## Scroll To Element

```java
scrollToElement(locator);
```

Example:

```java
scrollToElement(
    By.id("footer")
);
```

---

# Mouse Actions

## Hover

```java
hover(locator);
```

Example:

```java
hover(
    By.id("menu")
);
```

Useful for:

```text
Dropdown menus
Hover menus
Tooltips
Navigation menus
```

---

# Double Click

```java
doubleClick(locator);
```

---

# Right Click

```java
rightClick(locator);
```

---

# Drag and Drop

```java
dragAndDrop(
    source,
    target
);
```

Example:

```java
dragAndDrop(
    By.id("source"),
    By.id("target")
);
```

---

# Keyboard Actions

## Enter

```java
pressEnter(locator);
```

---

## Escape

```java
pressEscape();
```

---

## Tab

```java
pressTab(locator);
```

---

# Browser Navigation

## Open URL

```java
openUrl(
    "https://www.example.com"
);
```

---

## Refresh

```java
refresh();
```

---

## Back

```java
back();
```

---

## Forward

```java
forward();
```

---

# Browser Window

## Maximize

```java
maximizeWindow();
```

---

## Minimize

```java
minimizeWindow();
```

---

## Get Window Size

```java
Dimension size =
        getWindowSize();
```

---

# Window and Tab Handling

The framework supports switching between browser windows and tabs.

Example:

```java
switchToNewWindow();
```

Close the current window:

```java
closeCurrentWindow();
```

The framework can be extended with:

```text
switchToWindow()
getWindowHandles()
closeCurrentWindow()
```

for more advanced multi-tab scenarios.

---

# iframe Handling

## Switch To iframe

```java
switchToFrame(locator);
```

Example:

```java
switchToFrame(
    By.id("payment-frame")
);
```

---

## Switch Back To Main Page

```java
switchToDefaultContent();
```

Flow:

```text
Main Page
    │
    ▼
iframe
    │
    ▼
Perform actions
    │
    ▼
defaultContent()
    │
    ▼
Main Page
```

---

# Alert Handling

## Accept Alert

```java
acceptAlert();
```

---

## Dismiss Alert

```java
dismissAlert();
```

---

## Get Alert Text

```java
String message =
        getAlertText();
```

---

# JavaScript Execution

The framework provides a generic JavaScript method:

```java
executeScript(
    "window.scrollTo(0, document.body.scrollHeight);"
);
```

Another example:

```java
executeScript(
    "arguments[0].click();",
    element
);
```

JavaScript should generally be used only when normal Selenium interaction is insufficient.

---

# Screenshots

Manual screenshot:

```java
takeScreenshot(
    "login-page"
);
```

Screenshots are stored under:

```text
screenshots/
```

Example:

```text
screenshots/
├── login-page.png
├── home-page.png
└── login-failed.png
```

---

# Automatic Screenshot On Failure

The framework uses JUnit 5:

```java
TestExecutionExceptionHandler
```

The extension:

```text
ScreenshotExtension.java
```

automatically captures a screenshot when a test fails.

The extension is registered using:

```java
@ExtendWith(ScreenshotExtension.class)
```

in:

```text
tm.java
```

Therefore every test class extending `tm` automatically gets failure screenshot functionality.

---

# Failure Screenshot Flow

```text
@Test
   │
   ▼
Test Executes
   │
   ├──────── PASS ────────► Test Complete
   │
   │
   └──────── FAIL
             │
             ▼
     ScreenshotExtension
             │
             ▼
        ActionHelper
             │
             ▼
      takeScreenshot()
             │
             ▼
        screenshots/
```

---

# Page Object Model

The framework follows the Page Object Model.

Page Objects contain:

```text
Locators
   +
Page Actions
   +
Business Actions
```

Tests contain:

```text
@Test
+
Assertions
+
Test Scenarios
```

---

# Example Page Object

`HomePage.java`

```java
package org.example.page;

import org.example.tm;
import org.openqa.selenium.By;

public class HomePage extends tm {

    public final By username =
            By.id("username");

    public final By password =
            By.id("password");

    public final By loginButton =
            By.id("login");

    public void enterUsername(
            String value
    ) {

        enterText(
            username,
            value
        );
    }

    public void enterPassword(
            String value
    ) {

        enterText(
            password,
            value
        );
    }

    public void clickLogin() {

        click(loginButton);
    }

    public void login(
            String username,
            String password
    ) {

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }
}
```

---

# Test Class

Actual JUnit tests are stored under:

```text
src/test/java
```

Example:

```java
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

        openUrl(
            getValue("tm.url")
        );
    }

    @Test
    public void loginTest() {

        homePage.login(
                "test@example.com",
                "password123"
        );
    }
}
```

---

# Important Page Object Rule

Do not put:

```java
@Test
```

inside:

```text
HomePage.java
LoginPage.java
```

Page Objects should contain reusable actions.

Tests should contain:

```java
@Test
```

For example:

```text
HomePage.java
    │
    └── login()

HomePageTest.java
    │
    └── @Test
        loginTest()
```

This keeps the framework maintainable.

---

# Locator Strategy

Recommended locator priority:

```text
1. ID
2. Name
3. CSS Selector
4. Accessibility / ARIA attributes
5. XPath
```

Prefer stable locators.

Example:

```java
By.id("username");
```

over a long XPath:

```java
By.xpath(
    "/html/body/div/div/div/form/div[1]/input"
);
```

Avoid absolute XPath whenever possible.

---

# Example Locator Strategies

## ID

```java
By.id("username");
```

---

## Name

```java
By.name("username");
```

---

## CSS

```java
By.cssSelector(
    "input[name='username']"
);
```

---

## XPath

```java
By.xpath(
    "//input[@name='username']"
);
```

---

# JUnit 5

The framework uses JUnit 5.

Use:

```java
import org.junit.jupiter.api.Test;
```

and:

```java
import org.junit.jupiter.api.BeforeEach;
```

Example:

```java
@BeforeEach
public void setup() {

    // Setup before every test
}

@Test
public void testLogin() {

    // Test steps
}
```

---

# Test Lifecycle

Current framework supports:

```text
@BeforeEach
     │
     ▼
@Test
     │
     ▼
TestExecutionExceptionHandler
     │
     ├── PASS
     │
     └── FAIL
          │
          ▼
      Screenshot
```

The framework can later be extended with:

```text
@BeforeAll
@AfterEach
@AfterAll
```

for complete test lifecycle management.

---

# Reddit CAPTCHA Limitation

During browser automation testing, Reddit may detect automated browser activity and display a human-verification page.

Example:

```text
Prove your humanity

We're committed to safety and security.
But not for bots.

[ I'm not a robot ]
```

The browser may also display:

```text
Chrome is being controlled by automated test software
```

when Selenium is controlling Chrome.

Because Reddit can present a CAPTCHA/human-verification challenge during automated execution, the framework should **not attempt to automate or bypass the CAPTCHA**.

The CAPTCHA is a security mechanism intended to distinguish human users from automated traffic.

Therefore, for the framework demonstration, the test is kept on a different web page/site instead of attempting to automate the Reddit CAPTCHA.

Example configuration:

```properties
tm.url=https://www.facebook.com
```

The framework itself is independent of the application under test and can be used with other websites or internal test environments.

---

# Why Use a Test Environment?

For real automation projects, the recommended environment is:

```text
Development
     │
     ▼
QA / Test Environment
     │
     ▼
Automation
     │
     ▼
Regression Testing
```

Instead of automating public production websites that may contain:

```text
CAPTCHA
Rate limiting
Bot detection
Authentication restrictions
Anti-automation mechanisms
```

A dedicated QA/staging environment gives the automation team predictable test data and stable locators.

---

# Running Tests

## IntelliJ IDEA

Right-click the test:

```text
HomePageTest
    └── loginTest()
```

Then select:

```text
Run 'loginTest'
```

---

# Maven

Run all tests:

```bash
mvn test
```

Run a specific test class:

```bash
mvn -Dtest=HomePageTest test
```

Run a specific test method:

```bash
mvn -Dtest=HomePageTest#loginTest test
```

---

# Test Output

Failed screenshots are stored under:

```text
screenshots/
```

Example:

```text
screenshots/
├── loginTest_FAILED_1758523456789.png
├── invalidLogin_FAILED_1758523456790.png
└── homePageTest_FAILED_1758523456791.png
```

---

# Supported Utilities

| Category | Utility |
|---|---|
| Wait | `waitTm()` |
| Wait | `waitForVisible()` |
| Wait | `waitForClickable()` |
| Wait | `waitForInvisible()` |
| Validation | `isVisible()` |
| Validation | `isPresent()` |
| Validation | `isEnabled()` |
| Validation | `isSelected()` |
| Click | `click()` |
| Click | `clickIfVisible()` |
| Text | `enterText()` |
| Text | `getText()` |
| Text | `clear()` |
| Attribute | `getAttribute()` |
| Scroll | `scrollDown()` |
| Scroll | `scrollUp()` |
| Scroll | `scrollToElement()` |
| Mouse | `hover()` |
| Mouse | `doubleClick()` |
| Mouse | `rightClick()` |
| Mouse | `dragAndDrop()` |
| Keyboard | `pressEnter()` |
| Keyboard | `pressEscape()` |
| Keyboard | `pressTab()` |
| Browser | `openUrl()` |
| Browser | `refresh()` |
| Browser | `back()` |
| Browser | `forward()` |
| Window | `maximizeWindow()` |
| Window | `minimizeWindow()` |
| Window | `getWindowSize()` |
| Tab | `switchToNewWindow()` |
| Tab | `closeCurrentWindow()` |
| iframe | `switchToFrame()` |
| iframe | `switchToDefaultContent()` |
| Alert | `acceptAlert()` |
| Alert | `dismissAlert()` |
| Alert | `getAlertText()` |
| JavaScript | `executeScript()` |
| Screenshot | `takeScreenshot()` |

---

# Framework Design Principles

## Reusability

Common Selenium operations are implemented once inside:

```text
ActionHelper
```

Tests can then use:

```java
click();

enterText();

scrollDown();

hover();

doubleClick();
```

without duplicating Selenium implementation.

---

## Abstraction

The test should describe the action rather than the Selenium implementation.

Instead of:

```java
driver.findElement(
    By.id("username")
).sendKeys("test@example.com");
```

the test uses:

```java
enterText(
    username,
    "test@example.com"
);
```

---

## Configuration Driven

Browser and application configuration is stored in:

```text
config.properties
```

Example:

```properties
tm.browser=chrome
tm.url=https://www.facebook.com
```

This allows configuration changes without changing Java source code.

---

## Page Object Model

The framework separates:

```text
Page Objects
     │
     ├── Locators
     ├── Page Actions
     └── Business Actions

Tests
     │
     ├── Test Scenarios
     ├── Assertions
     └── Test Data
```

---

## Failure Debugging

When a test fails:

```text
Test Failure
     │
     ▼
ScreenshotExtension
     │
     ▼
ActionHelper
     │
     ▼
Screenshot
     │
     ▼
screenshots/
```

This makes UI failures easier to investigate.

---
=
---

# Future Framework Architecture

The framework can eventually be expanded into:

```text
                    Web Automation Framework
                              │
          ┌───────────────────┼───────────────────┐
          │                   │                   │
          ▼                   ▼                   ▼
     Configuration         Driver              Utilities
          │                   │                   │
          │             ┌─────┼─────┐       ┌─────┼─────┐
          │             │     │     │       │     │     │
          ▼             ▼     ▼     ▼       ▼     ▼     ▼
    ConfigReader      Chrome Firefox Edge   Wait  Actions JS
                                              │
                                              ▼
                                         Page Objects
                                              │
                                              ▼
                                            Tests
                                              │
                                              ▼
                                            JUnit 5
                                              │
                              ┌───────────────┴───────────────┐
                              │                               │
                             PASS                            FAIL
                              │                               │
                              ▼                               ▼
                           Report                    Screenshot
```

---

# Example End-to-End Test

```java
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

        openUrl(
            getValue("tm.url")
        );
    }

    @Test
    public void loginTest() {

        homePage.login(
                "test@example.com",
                "password123"
        );
    }
}
```

---

# Execution Flow

```text
config.properties
       │
       ▼
  ConfigReader
       │
       ▼
  DriverFactory
       │
       ▼
  DriverManager
       │
       ▼
   WebDriver
       │
       ▼
      tm
       │
       ├───────────────┐
       ▼               ▼
 ActionHelper      Page Object
       │               │
       └───────┬───────┘
               ▼
             Test
               │
               ▼
            JUnit 5
               │
         ┌─────┴─────┐
         ▼           ▼
       PASS         FAIL
                     │
                     ▼
             ScreenshotExtension
                     │
                     ▼
                Screenshot
```

---


Built using:

```text
Java
Selenium WebDriver
JUnit 5
Maven
Chrome
Firefox
Edge
```
