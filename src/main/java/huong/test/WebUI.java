package huong.test;

import com.aventstack.extentreports.Status;
import huong.test.driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reports.AllureManager;
import reports.ExtentTestManager;
import utils.Log;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebUI {

    //click bằng xpath
    @Step("Click element:{0}")
    public static void clickElement(String xpath) {

        DriverManager.getDriver().findElement(By.xpath(xpath)).click();
        Log.info("Click element:" + xpath);
        ExtentTestManager.logMessage(Status.PASS, "Click element:" + xpath);
        AllureManager.saveTextLog("Click element:" + xpath);
        AllureManager.saveScreenshotPNG();
    }

    @Step("Click element:{0}")
    //click bằng WebElement
    public static void clickElement(By by) {

        DriverManager.getDriver().findElement(by).click();
        Log.info("Click element:" + by);
        ExtentTestManager.logMessage(Status.PASS, "Click element:" + by);
        AllureManager.saveTextLog("Click element:" + by);
    }

    //Click bằng xpath với thời gian time out
    @Step("Click element:{0}")
    public static void clickElement(String xpath, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        DriverManager.getDriver().findElement(By.xpath(xpath)).click();
        Log.info("Click element:" + xpath);
        ExtentTestManager.logMessage(Status.PASS, "Click element:" + xpath);
        AllureManager.saveTextLog("Click element:" + xpath);
    }

    //Click bằng element với thời gian timeout
    @Step("Click element:{0}")
    public static void clickElement(By by, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        DriverManager.getDriver().findElement(by).click();
        Log.info("Click element:" + by);
        ExtentTestManager.logMessage(Status.PASS, "Click element:" + by);
        AllureManager.saveTextLog("Click element:" + by);
    }

    //Nhập value bằng xpath
    @Step("Send text {1} on element {0}")
    public static void sendText(String xpath, String text) {

        DriverManager.getDriver().findElement(By.xpath(xpath)).sendKeys(text);
        Log.info("Send text " + text + " to element " + xpath);
        ExtentTestManager.logMessage(Status.PASS, "Send text " + text + " to element " + xpath);
        AllureManager.saveTextLog("Send text " + text + " to element " + xpath);
    }

    //Nhập value bằng WebElement
    @Step("Send text {1} on element {0}")
    public static void sendText(By by, String text) {

        DriverManager.getDriver().findElement(by).sendKeys(text);
        Log.info("Send text " + text + " to element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Send text " + text + " to element " + by);
    }

    //Nhập value với thời gian timeout
    @Step("Send text {1} on element {0}")
    public static void sendText(String xpath, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        DriverManager.getDriver().findElement(By.xpath(xpath)).sendKeys(text);
        Log.info("Send text " + text + " to element " + xpath);
        ExtentTestManager.logMessage(Status.PASS, "Send text " + text + " to element " + xpath);
    }

    @Step("Send text {1} on element {0}")
    public static void sendText(By by, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        DriverManager.getDriver().findElement(by).sendKeys(text);
        Log.info("Send text " + text + " to element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Send text " + text + " to element " + by);
    }

    @Step("Clear and send text {1} on element {0}")
    public static void sendTextWithClear(By by, String text) {
        Actions act = new Actions(DriverManager.getDriver());
        DriverManager.getDriver().findElement(by).click();
        act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        act.sendKeys(Keys.DELETE).perform();
        act.sendKeys(text).perform();
        Log.info("Send text " + text + " to element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Send text " + text + " to element " + by);
    }

    @Step("Clear and send text {1} on element {0}")
    public static void sendTextWithClear(String xpath, String text) {
        Actions act = new Actions(DriverManager.getDriver());
        DriverManager.getDriver().findElement(By.xpath(xpath)).click();
        act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        act.sendKeys(Keys.DELETE).perform();
        act.sendKeys(text).perform();
        Log.info("Send text " + text + " to element " + xpath);
        ExtentTestManager.logMessage(Status.PASS, "Send text " + text + " to element " + xpath);
    }

    @Step("Clear and send text {1} on element {0}")
    public static void sendTextWithClear(By by, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Actions act = new Actions(DriverManager.getDriver());
        DriverManager.getDriver().findElement(by).click();
        act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        act.sendKeys(Keys.DELETE).perform();
        act.sendKeys(text).perform();
        Log.info("Send text " + text + " to element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Send text " + text + " to element " + by);
    }

    @Step("Sleeping within {0}")
    public static void Sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Log.info("Sleeping within " + seconds + " seconds to waiting");
        ExtentTestManager.logMessage(Status.PASS, "Sleeping within " + seconds + " seconds to waiting");
    }

    @Step("Select value {1} of dropdown {0}")
    public static void selectDropdown(By dropdownElement, By inputElement, String value) {
        WebUI.clickElement(dropdownElement);
        WebUI.sendText(inputElement, value);
        DriverManager.getDriver().findElement(inputElement).sendKeys(Keys.ENTER);
        Log.info("Select value " + value + " from dropdown " + dropdownElement);
        ExtentTestManager.logMessage(Status.PASS, "Select value " + value + " from dropdown " + dropdownElement);
    }

    @Step("Select value {1} of dropdown {0}")
    public static void selectDynamicDropdownMultiValue(String xpathDropdownElement, ArrayList<String> listValue) {
        Select select = new Select(DriverManager.getDriver().findElement(By.xpath(xpathDropdownElement)));
        if (select.isMultiple()) {
            WebUI.clickElement(xpathDropdownElement + "/following-sibling::button"); //open dropdown
            for (String s : listValue) {
                Assert.assertTrue(DriverManager.getDriver().findElement(By.xpath(xpathDropdownElement + "/following-sibling::div//input")).isDisplayed(), "Chưa click được dropdown");
                WebUI.sendTextWithClear(xpathDropdownElement + "/following-sibling::div//input", s);
                WebUI.clickElement(xpathDropdownElement + "/following-sibling::div//span[text()='" + s + "']");
            }
        } else {
            System.out.println("Dropdown không hỗ trợ chọn nhiều giá trị.");
        }
        WebUI.clickElement(xpathDropdownElement + "/following-sibling::button");// close dropdown

        Log.info("Select value " + listValue + " from dropdown " + xpathDropdownElement);
        ExtentTestManager.logMessage(Status.PASS, "Select value " + listValue + " from dropdown " + xpathDropdownElement);
    }

    @Step("Select value {1} of dropdown {0}")
    public static void selectDropdown(By dropdownElement, By inputElement, String value, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        WebUI.clickElement(dropdownElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputElement));
        WebUI.sendText(inputElement, value);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='text'][contains(text(),'" + value + "')])[1]")));
        DriverManager.getDriver().findElement(inputElement).sendKeys(Keys.ENTER);
        Log.info("Select value " + value + " from dropdown " + dropdownElement);
        ExtentTestManager.logMessage(Status.PASS, "Select value " + value + " from dropdown " + dropdownElement);
    }

    @Step("Select value {1} of dropdown {0}")
    public static void selectDynamicDropdownWithSingleValue(By dropdownToggle, String value) {
        WebUI.clickElement(dropdownToggle);
        List<WebElement> listItems = DriverManager.getDriver().findElements(By.className("dropdown-item active"));
        for (WebElement item : listItems) {
            if (item.getText().equals(value)) {
                item.click();
                break;
            }
        }
        Log.info("Select value " + value + " from dropdown " + dropdownToggle);
        ExtentTestManager.logMessage(Status.PASS, "Select value " + value + " from dropdown " + dropdownToggle);
    }

    @Step("Scroll screen to element {0}")
    public static void scrollPageToElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(by));
        Log.info("Scroll screen to element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Scroll screen to element: " + by);
    }

    @Step("Scroll screen to element {0}")
    public static void scrollPageToElement(By by, int timeout) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(by));
        System.out.println("Scroll success");
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Log.info("Scroll screen to element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Scroll screen to element: " + by);
    }

    @Step("Verify message {0} is displayed")
    public static void verifyMessage(String expectedMessage) {
        String actualMessage = DriverManager.getDriver().findElement(By.xpath("//span[@data-notify='message']")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Not correct message content");
        Log.info("Verify success that message: " + expectedMessage + " is displayed");
        ExtentTestManager.logMessage(Status.PASS, "Verify success that message: " + expectedMessage + " is displayed");
    }
}
