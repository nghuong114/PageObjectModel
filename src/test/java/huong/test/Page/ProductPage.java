package huong.test.Page;

import huong.test.WebUI;
import huong.test.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

public class ProductPage {
    //Khai báo các phần tử trên Page
    private By textMainContent = By.xpath("");
    private By checkboxCheckAll = By.xpath("");
    private By buttonAddNewProduct = By.xpath("");
    private By dropdownSortBy = By.xpath("");
    private By inputSearch = By.xpath("");
    private By buttonExpand = By.xpath("");
    private By checkboxSelectItem = By.xpath("");
    private By imageThumbnail = By.xpath("");
    private By textProductName = By.xpath("");
    private By textProductInfo = By.xpath("");

    // các hàm
    public AddNewProductPage clickButtonAddNewProduct() {
        WebUI.clickElement(buttonAddNewProduct);
        return new AddNewProductPage();
    }

    public void selectSortBy(String sortBy) {
        WebUI.selectDynamicDropdownWithSingleValue(dropdownSortBy, sortBy);
    }

    public void searchProduct(String value) {
        WebUI.sendText(inputSearch, value);
        DriverManager.getDriver().findElement(inputSearch).sendKeys(Keys.ENTER);
    }

    public void selectAll() {
        WebUI.clickElement(checkboxCheckAll);
    }

    public void clickIconOptions(String item, String option) {
        WebUI.clickElement("(//span[contains(text(),'" + item + "')]/ancestor::td)/following-sibling::td/a[@title='" + option + "']");
    }

    public void clickShowAttributesOfProduct(String productName) {
        By iconExpand = By.xpath("//span[contains(text(),'" + productName + "')]/ancestor::tr//span[contains(@class,'fooicon-plus')]");
        WebUI.clickElement(iconExpand);
        Assert.assertEquals(DriverManager.getDriver().findElements(iconExpand).size(), 0, "Chưa click được icon plus ");
    }

    public void clickCollapseAttributesOfProduct(String productName) {
        By iconCollapse = By.xpath("//span[contains(text(),'" + productName + "')]/ancestor::tr//span[contains(@class,'fooicon-minus')]");
        WebUI.clickElement(iconCollapse);
        Assert.assertEquals(DriverManager.getDriver().findElements(iconCollapse).size(), 0, "Chưa click được icon minus");
    }


    public void updateStatusOfAttribute(String attribute, Boolean status) {
        By checkbox = By.xpath("//tbody//th[contains(text(),'" + attribute + "')]/following-sibling::td/label/input");
        Boolean currentStatus = DriverManager.getDriver().findElement(checkbox).isSelected();
        if (!currentStatus.equals(status)) {
            WebUI.clickElement(checkbox);
        }
    }

    public void verifyAddNewProductSuccess(String ProductName) {
        WebUI.verifyMessage("Product has been inserted successfully");
        Assert.assertEquals(DriverManager.getDriver().findElement(By.xpath("//tbody/tr[1]/td[2]//span")).getText(), ProductName, "Tên sản phẩm không đúng");

    }

    public void verifyCoppyProductSuccess(String productName) {

    }

    public void verifyDeleteProductSuccess(String productName) {

    }

    public void gotoPageOfTable(int num) {

    }
}
