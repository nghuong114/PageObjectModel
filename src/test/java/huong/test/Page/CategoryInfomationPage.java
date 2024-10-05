package huong.test.Page;

import huong.test.WebUI;
import huong.test.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

public class CategoryInfomationPage {

    //    private String urlLogin = "https://crm.anhtester.com/admin/authentication";


    //Khai báo các phần tử trên Page

    private By headerCreateCategoryPage = By.xpath("//div[@class='card-header']");

    private By inputName = By.xpath("//input[@id='name']");
    private By dropdownParentCategory = By.xpath("//label[text()='Parent Category']/following-sibling::div//button");
    private By searchParentCategory = By.xpath("//label[text()='Parent Category']/following-sibling::div//input");
    private By inputOrderingNumber = By.xpath("//label[normalize-space()='Ordering Number']/following-sibling::div//input");
    private By dropdownType = By.xpath("//label[normalize-space()='Type']/following-sibling::div//button");
    private By inputImageBanner = By.xpath("//label[contains(text(),'Banner')]/following-sibling::div//div[text()='Choose File']");
    private By inputSearchImage = By.xpath("//input[@placeholder='Search your files']");
    private By imageItem = By.xpath("//div[@class='aiz-file-box-wrap'][1]");
    private By buttonAddFiles = By.xpath("//button[text()='Add Files']");
    private By inputImageIcon = By.xpath("//label[contains(text(),'Icon')]/following-sibling::div//div[text()='Choose File']");
    private By inputMetaTitle = By.xpath("//input[@name='meta_title']");
    private By textareaMetaDescription = By.xpath("//textarea[@name='meta_description']");
    private By dropdownFilteringAttributes = By.xpath("//label[text()='Filtering Attributes']/following-sibling::div//button");
    private By searchFilteringAttributes = By.xpath("//label[text()='Filtering Attributes']/following-sibling::div//input");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By alertMessage = By.xpath("//span[@data-notify='message']");


    //Hàm xử lý
    public void inputCategoryForm(String categoryName, String bannerName, String iconName, String metaTitle, String description) {

        WebUI.sendText(inputName, categoryName);
        WebUI.clickElement(dropdownParentCategory);
        WebUI.sendText(searchParentCategory, "Cake");
        DriverManager.getDriver().findElement(searchParentCategory).sendKeys(Keys.ENTER);
        WebUI.sendText(inputOrderingNumber, "100");
        WebUI.clickElement(dropdownType);
        WebUI.clickElement("//span[normalize-space()='Physical']");
        WebUI.clickElement(inputImageBanner);
        selectImage(bannerName);
        WebUI.clickElement(inputImageIcon);
        selectImage(iconName);
        WebUI.sendText(inputMetaTitle, metaTitle);
        WebUI.sendText(textareaMetaDescription, description);
        WebUI.clickElement(dropdownFilteringAttributes);
        WebUI.sendText(searchFilteringAttributes, "Size");
        DriverManager.getDriver().findElement(searchFilteringAttributes).sendKeys(Keys.ENTER);

    }

    public void selectImage(String imageName) {

        WebUI.sendText(inputSearchImage, imageName);
        WebUI.Sleep(1);
        WebUI.clickElement(imageItem);
        Assert.assertEquals(DriverManager.getDriver().findElement(By.xpath("//div[@class='aiz-uploader-selected']")).getText(), "1 File selected", "Chưa chọn được ảnh");
        WebUI.clickElement(buttonAddFiles);
    }

    public CategoriesPage clickButtonSave() {
        WebUI.clickElement(buttonSave);
        return new CategoriesPage();
    }

    public void editCategoryInfo(String fieldName, String value) {
        switch (fieldName.toUpperCase()) {
            case "NAME":
                WebUI.sendTextWithClear(inputName, value);
                break;
            case "ORDERING NUMBER":
                WebUI.sendTextWithClear(inputOrderingNumber, value);
                break;
            case "META_TITLE":
                WebUI.sendTextWithClear(inputMetaTitle, value);
                break;
            case "META_DESCRIPTION":
                WebUI.sendTextWithClear(textareaMetaDescription, value);
                break;
            default:
                break;

        }

    }

    public void verifyStayinCreateCategoryPage() {
        Assert.assertTrue(DriverManager.getDriver().findElement(headerCreateCategoryPage).isDisplayed(), "Không hiển thị header");
        Assert.assertEquals(DriverManager.getDriver().findElement(headerCreateCategoryPage).getText(), "Category Information", "Content header không chính xác");
    }

    public void verifyUpdateCategorySuccess() {
        Assert.assertTrue(DriverManager.getDriver().findElement(alertMessage).isDisplayed(), "Không hiển thị thông báo");
        Assert.assertEquals(DriverManager.getDriver().findElement(alertMessage).getText(), "Category has been updated successfully", "content message không chính xác");
    }

}
