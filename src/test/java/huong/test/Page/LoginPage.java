package huong.test.Page;

import helpers.PropertiesHelper;
import huong.test.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class LoginPage {

    //Khai báo các object/ element trong page
    JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
    //Kieu By

    By headerLoginPage = By.xpath("//p[normalize-space()='Login to your account.']");
    By inputEmail = By.xpath("//input[@id='email']");
    By inputPassword = By.xpath("//input[@id='password']");
    By buttonLogin = By.xpath("//button[@type='submit']");
    By checkboxRemenberMe = By.xpath("//input[@id='remember']");
    By lableForgotPassword = By.xpath("//a[normalize-space()='Forgot password ?']");
    By alertDanger = By.xpath("//span[contains(text(),'Invalid login creden')]");

    // xây dụng các hàm xử lý trên Page\

    private void setInputEmail(String email) {
//        js.executeScript("arguments[0].style.border='3px solid red'", inputEmail);
        DriverManager.getDriver().findElement(inputEmail).sendKeys(email);

    }

    private void setInputPassword(String password) {
//        js.executeScript("arguments[0].style.border='3px solid red'", inputPassword);
        DriverManager.getDriver().findElement(inputPassword).sendKeys(password);
    }

    private void clickButtonLogin() {
//        js.executeScript("arguments[0].style.border='3px solid red'", buttonLogin);
        DriverManager.getDriver().findElement(buttonLogin).click();
    }

    public DashboardPage LoginCMS() {
        PropertiesHelper.loadAllFiles();
        DriverManager.getDriver().get(PropertiesHelper.getValue("urlLogin"));
        setInputEmail(PropertiesHelper.getValue("email"));
        setInputPassword(PropertiesHelper.getValue("password"));
        clickButtonLogin();
        return new DashboardPage();
    }

    public DashboardPage LoginCMS(String email, String password) {
        PropertiesHelper.loadAllFiles();

        DriverManager.getDriver().get(PropertiesHelper.getValue("urlLogin"));
        setInputEmail(email);
        setInputPassword(password);
        clickButtonLogin();

        return new DashboardPage();
    }

    public void verifyLoginSuccess() {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Active eCommerce CMS | Anh Tester Demo");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://cms.anhtester.com/admin", "Endpoint không chính xác");
//        Assert.assertFalse(DriverManager.getDriver().findElement(alertDanger).isDisplayed(), "Alert Invalid is displayed");
        
        System.out.println("Login successfully");
    }

    public void verifyLoginFailed() {
        Assert.assertTrue(DriverManager.getDriver().findElement(alertDanger).isDisplayed(), "Alert Invalid is not displayed");
        Assert.assertEquals(DriverManager.getDriver().findElement(alertDanger).getText(), "Invalid login credentials", "Content of alert is not correct");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), PropertiesHelper.getValue("urlLogin"), "User is navigated to another page:" + DriverManager.getDriver().getCurrentUrl());
        System.out.println("Login failed");
    }

    public void verifyLoginMissingEmail() {
        String validationMsg = DriverManager.getDriver().findElement(inputEmail).getAttribute("validationMessage");
        Assert.assertEquals(validationMsg, "Please fill out this field.", "Email is not required");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), PropertiesHelper.getValue("urlLogin"), "User is navigated to another page:" + DriverManager.getDriver().getCurrentUrl());
        System.out.println("Email is empty");
    }

    public void verifyLoginMissingPassword() {
        String validationMsg = DriverManager.getDriver().findElement(inputPassword).getAttribute("validationMessage");
        Assert.assertEquals(validationMsg, "Please fill out this field.", "Email is not required");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), PropertiesHelper.getValue("urlLogin"), "User is navigated to another page:" + DriverManager.getDriver().getCurrentUrl());
        System.out.println("Password is empty");
    }

}
