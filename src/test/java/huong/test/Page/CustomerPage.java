package huong.test.Page;

import huong.test.WebUI;
import huong.test.driver.DriverManager;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CustomerPage {

    //    private String urlLogin = "https://crm.anhtester.com/admin/authentication";

    //Khai báo các phần tử trên Page

    private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By headerCustomerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By searchCustomer = By.xpath("//div[@id='clients_filter']//input[@type='search']");


    //Hàm xử lý
    public void clickButtonAddNewCustomer() {
        WebUI.clickElement(buttonAddNewCustomer);
    }

    public void verifyStayinCustomerPage() {
        Assert.assertTrue(DriverManager.getDriver().findElement(headerCustomerPage).isDisplayed(), "Header Page không xuất hiện");
        Assert.assertEquals(DriverManager.getDriver().findElement(headerCustomerPage).getText(), "Customers Summary", "COntent header page không đúng");
    }


}
