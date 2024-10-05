package huong.test.Page;

import huong.test.WebUI;
import huong.test.driver.DriverManager;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardPage {

//    private String urlLogin = "https://crm.anhtester.com/admin/authentication";


    //Khai báo các phần tử trên Page

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuProducts = By.xpath("//span[normalize-space()='Products']");
    private By menuSales = By.xpath("//span[normalize-space()='Sales']");
    private By menuUploadedFiles = By.xpath("//span[normalize-space()='Uploaded Files']");
    private By menuReports = By.xpath("//span[normalize-space()='Reports']");
    private By menuBlogSystem = By.xpath("//span[normalize-space()='Blog System']");
    private By menuMarketing = By.xpath("//span[normalize-space()='Marketing']");
    private By menuSupport = By.xpath("//span[normalize-space()='Support']");

    private By optionCategory = By.xpath("//span[normalize-space()='Category']");
    private By optionBrands = By.xpath("//span[normalize-space()='Brand']");
    private By dropdownUserProfile = By.xpath("//span[contains(@class,'avatar')]/ancestor::div[contains(@class,'dropdown')]");
    private By optionLogOut = By.xpath("//a[normalize-space()='Logout']");
    private By optionProfile = By.xpath("//a[normalize-space()='Profile']");
    private By optionAddNewProduct = By.xpath("//span[normalize-space()='Add New Product']");

    //Hàm xử lý

    public CustomerPage clickMenuCustomers() {
        WebUI.clickElement(menuCustomers);
        return new CustomerPage();
    }

    public void clickMenuProducts() {
        WebUI.clickElement(menuProducts);

    }

    public CategoriesPage clicOptionCategory() {
        WebUI.clickElement(optionCategory);
        return new CategoriesPage();
    }

    public BrandsPage clicOptionBrand() {
        WebUI.clickElement(optionBrands);
        return new BrandsPage();
    }

    public AddNewProductPage clicOptionAddNewProduct() {
        WebUI.clickElement(optionAddNewProduct);
        return new AddNewProductPage();
    }

    public void LogoutCRM() {
        WebUI.clickElement(dropdownUserProfile);
        WebUI.Sleep(1);
        WebUI.clickElement(optionLogOut);
    }

    public void verifyStayinDashboard() {
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://cms.anhtester.com/admin", "Chuyển hướng sai endpoint");
    }


}
