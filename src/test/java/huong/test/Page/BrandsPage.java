package huong.test.Page;

import helpers.CaptureHelper;
import huong.test.WebUI;
import huong.test.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class BrandsPage {
    //Khai báo các phần tử trên Page
    private By textMainContent = By.xpath("//h1");
    private By inputSearch = By.xpath("//input[@id='search']");
    private By inputBrandName = By.xpath("//label[normalize-space()='Name']/following-sibling::input");
    private By dropdownLogo = By.xpath("//div[@class='form-control file-amount']");
    private By inputMetaTitle = By.xpath("//label[normalize-space()='Meta Title']/following-sibling::input");
    private By textareaMetaDescription = By.xpath("//textarea[@name='meta_description']");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By inputSearchImage = By.xpath("//input[@placeholder='Search your files']");
    private By imageItem = By.xpath("//div[@class='aiz-file-box-wrap'][1]");
    private By buttonAddFiles = By.xpath("//button[text()='Add Files']");
    private By buttonAgree = By.xpath("//a[@id='delete-link']");

    public void verifyStayInBrandsPage() {
        String currentHeader = DriverManager.getDriver().findElement(textMainContent).getText();
        Assert.assertEquals(currentHeader, "All Brands", "Nội dung header trang chưa đúng");
    }

    public void searchBrand(String keyword) {
        WebUI.sendText(inputSearch, keyword);
        DriverManager.getDriver().findElement(inputSearch).sendKeys(Keys.ENTER);
        WebUI.Sleep(1);

    }

    public List<String> resultSearchBrand(String keyword) {
        List<WebElement> listNavElements = DriverManager.getDriver().findElements(By.tagName("nav"));
        List<String> listResult = new ArrayList<>();
        if (listNavElements.isEmpty()) {
            List<WebElement> listElements = DriverManager.getDriver().findElements(By.xpath("//tbody/tr"));
            for (WebElement s : listElements) {
                listResult.add(s.getText());
            }
        } else {
            List<WebElement> listPageItems = DriverManager.getDriver().findElements(By.xpath("//li[contains(@class,'page-item')]"));
            int numberOfPage = listPageItems.size() - 3;
            for (int i = 0; i < numberOfPage; i++) {
                List<WebElement> listElements = DriverManager.getDriver().findElements(By.xpath("//tbody/tr"));
                for (WebElement s : listElements) {
                    listResult.add(s.getText());
                }
                WebUI.clickElement("//a[@rel='next']/parent::li");
                WebUI.Sleep(1);
            }
        }
        return listResult;
    }

    public void verifySearchBrand(String keyword, boolean expectedExistingResult) {
        List<String> listResult = resultSearchBrand(keyword);
        if (!expectedExistingResult) {
            Assert.assertEquals(listResult.get(0), "Nothing found", "Đã tìm thấy kết quả:" + listResult);
        } else {
            for (String s : listResult) {
                String result = s.toUpperCase();
                Assert.assertTrue(result.contains(keyword.toUpperCase()), "Kết quả search không chứa từ khoá tìm kiếm");

            }
        }
    }

    public void setNewBrandInfo(String name, String imageName, String metaTitle, String metaDescription) {
        WebUI.sendText(inputBrandName, name);
        WebUI.clickElement(dropdownLogo);
        this.selectImage(imageName);
        WebUI.sendText(inputMetaTitle, metaTitle);
        WebUI.sendText(textareaMetaDescription, metaDescription);
        WebUI.clickElement(buttonSave);
    }

    public BrandInfoPage clickEditBrand(String brandName) {

        WebUI.clickElement("//td[normalize-space()='" + brandName + "']/following-sibling::td//a[@title='Edit']");
        return new BrandInfoPage();
    }

    public void clickDeleteBrand(String brandName) {
        WebUI.clickElement("//td[normalize-space()='" + brandName + "']/following-sibling::td//a[@title='Delete']");
        WebUI.clickElement(buttonAgree);
    }

    public void clickAgreeDelete() {
        WebUI.clickElement("//a[text()='Delete']");
    }

    public void clickCancelDelete() {
        WebUI.clickElement("//button[text()='Cancel']");
    }

    public void verifyDeleteSuccess(String brandName) {
        WebUI.verifyMessage("Brand has been deleted successfully");
        this.searchBrand(brandName);
        this.verifySearchBrand(brandName, false);
        CaptureHelper.takeScreenShot("DeleteBrand");
    }

    public void selectImage(String imageName) {

        WebUI.sendText(inputSearchImage, imageName);
        WebUI.Sleep(1);
        WebUI.clickElement(imageItem);
        Assert.assertEquals(DriverManager.getDriver().findElement(By.xpath("//div[@class='aiz-uploader-selected']")).getText(), "1 File selected", "Chưa chọn được ảnh");
        WebUI.clickElement(buttonAddFiles);
    }

    public void verifyAddNewBrandSuccess(String brandName) {
        WebUI.verifyMessage("Brand has been inserted successfully 123");
        this.searchBrand(brandName);
        this.verifySearchBrand(brandName, true);
        CaptureHelper.takeScreenShot("AddNewBrand");
    }

}
