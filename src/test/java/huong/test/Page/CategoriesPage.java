package huong.test.Page;

import huong.test.WebUI;
import huong.test.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

public class CategoriesPage {

    //    private String urlLogin = "https://crm.anhtester.com/admin/authentication";

    //Khai báo các phần tử trên Page

    private By buttonAddNewCategory = By.xpath("//span[normalize-space()='Add New category']");
    private By headerCategoriesPage = By.xpath("//h1[normalize-space()='All categories']");
    private By searchCategory = By.xpath("//input[@id='search']");
    private By itemCategory = By.xpath("//tbody/tr[1]/td[2]");
    private By buttonEdit = By.xpath("(//i[@class='las la-edit'])[1]");
    private By buttonDelete = By.xpath("//tbody/tr[1]/td/following-sibling::*/a[@title='Delete']");
    private By buttonAgreeDelete = By.xpath("//a[normalize-space()='Delete'][1]");
    private By buttonCancel = By.xpath("//button[normalize-space()='Cancel']");
    private By alertMessage = By.xpath("//span[@data-notify='message']");

    //Hàm xử lý
    public CategoryInfomationPage clickButtonAddNewCategory() {
        WebUI.clickElement(buttonAddNewCategory);
        return new CategoryInfomationPage();
    }

    public void searchCategoryByName(String name) {
        WebUI.sendText(searchCategory, name);
        System.out.println("Nhập vào ô tìm kiếm :" + name);
        DriverManager.getDriver().findElement(searchCategory).sendKeys(Keys.ENTER);
        WebUI.Sleep(2);
    }

    public CategoryInfomationPage clickButtonEditCategory() {
        WebUI.clickElement(buttonEdit);
        return new CategoryInfomationPage();

    }

    public void clickButtonDeleteCategory() {
        WebUI.clickElement(buttonDelete);

    }

    public void DeleteCategory() {
        WebUI.clickElement(buttonAgreeDelete);
    }

    public void cancelDeleteCategory() {
        WebUI.clickElement(buttonCancel);
    }

    public void verifyStayinCategoriesPage() {
        Assert.assertTrue(DriverManager.getDriver().findElement(headerCategoriesPage).isDisplayed(), "Header Page không xuất hiện");
        Assert.assertEquals(DriverManager.getDriver().findElement(headerCategoriesPage).getText(), "All categories", "Content header page không đúng");
    }

    public void verifyFirstSearchResult(String name) {
        Assert.assertTrue(DriverManager.getDriver().findElement(itemCategory).isDisplayed(), "Không tìm thấy category nào");
        Assert.assertEquals(DriverManager.getDriver().findElement(itemCategory).getText(), name, "Kết quả tìm kiếm không đúng");
    }

    public void verifyAddNewcategorySuccess() {
        Assert.assertEquals("Category has been inserted successfully", DriverManager.getDriver().findElement(alertMessage).getText(), "content của error message không chính xác");
    }

    public void verifyDeleteCategorySuccess() {
        Assert.assertTrue(DriverManager.getDriver().findElement(alertMessage).isDisplayed(), "Không hiển thị thông báo");
        Assert.assertEquals(DriverManager.getDriver().findElement(alertMessage).getText(), "Category has been deleted successfully", "content message không chính xác");
    }

    public void verifyUpdateCategorySuccess() {
        Assert.assertTrue(DriverManager.getDriver().findElement(alertMessage).isDisplayed(), "Không hiển thị thông báo");
        Assert.assertEquals(DriverManager.getDriver().findElement(alertMessage).getText(), "Category has been updated successfully", "content message không chính xác");
    }


}
