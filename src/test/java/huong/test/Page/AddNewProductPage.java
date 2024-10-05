package huong.test.Page;

import huong.test.WebUI;
import huong.test.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.ArrayList;

public class AddNewProductPage {
    //Khai báo các phần tử trên Page

    private By textMainContent = By.xpath("//h5[normalize-space()='Add New Product']");
    private By inputProductName = By.xpath("//label[contains(text(),'Product Name')]/following-sibling::div/input");
    private By dropdownCategory = By.xpath("//label[contains(text(),'Category')]/following-sibling::div//button");
    private By inputCategory = By.xpath("//label[contains(text(),'Category')]/following-sibling::div//input");
    private By dropdownBrand = By.xpath("//label[contains(text(),'Brand')]/following-sibling::div//button");
    private By inputBrand = By.xpath("//label[contains(text(),'Brand')]/following-sibling::div//input");
    private By inputUnit = By.xpath("//label[contains(text(),'Unit')]/following-sibling::div/input");
    private By inputWeight = By.xpath("//label[contains(text(),'Weight')]/following-sibling::div/input");
    private By inputMinimunPurchaseQty = By.xpath("//label[contains(text(),'Minimum Purchase Qty')]/following-sibling::div/input");
    //    private By inputTag = By.xpath("//label[contains(text(),'Tags')]/following-sibling::div/tags");
    private By inputTag = By.xpath("//span[@role='textbox']");

    private By selectImageGalery = By.xpath("");
    private By selectThmbnailImage = By.xpath("");
    private By dropdownVideoprovider = By.xpath("");
    private By inputVideoLink = By.xpath("");
    private By checkboxEnableColors = By.xpath("//input[@name='colors_active']/following-sibling::span");
    private String dropdownColors = "//select[@id='colors']";
    private By selectedColors = By.xpath("//select[@id='colors']/following-sibling::button");
    private String dropdownSize = "//select[@id='size']";
    private String dropdownQuality = "//select[@id='quality']";
    private By textSelectedColors = By.xpath("//select[@id='colors']/following-sibling::button/div");
    private By textSelectedSize = By.xpath("//select[@id='size']/following-sibling::button/div");
    private By textSelectedQuality = By.xpath("//select[@id='quality']/following-sibling::button/div");
    private String dropdownAttributes = "//select[@id='choice_attributes']";
    private By inputUnitPrice = By.xpath("//label[contains(text(),'Unit price')]/following-sibling::div/input");

    private By inputDiscount = By.xpath("//h5[normalize-space()='Product price + stock']/following::input[@name='discount']");
    private By dropdownDiscountUnit = By.xpath("");
    private By inputQuantity = By.xpath("//label[contains(text(),'Quantity')]/following-sibling::div/input");
    private By inputExternalLink = By.xpath("");
    private By inputExternalLinkButtonText = By.xpath("");
    private By inputProductDescription = By.xpath("");
    private By selectPDFSpecification = By.xpath("");
    private By inputMetaTitle = By.xpath("");
    private By inputSEOMetaTagDescription = By.xpath("");
    private By selectMetaImage = By.xpath("");
    private By inputLowStockQuantityWarning = By.xpath("");
    private By selectModeShowStockQuantity = By.xpath("");
    private By selectModeShowStockWithTextOnly = By.xpath("");
    private By selectModeHideStock = By.xpath("");
    private By selectModeCashOnDeliveryStatus = By.xpath("");
    private By selectModeFeaturedStatus = By.xpath("");
    private By selectModeTodaysDealStatus = By.xpath("");
    private By dropdownAddToFlash = By.xpath("");
    private By inputFlashDealDiscount = By.xpath("");
    private By dropdownFlashDealDiscountType = By.xpath("");
    private By inputShippingDays = By.xpath("");
    private By inputTax = By.xpath("");
    private By selectTaxUnit = By.xpath("");

    private By buttonSaveAndUnpublish = By.xpath("//button[normalize-space()='Save & Unpublish']");
    private By buttonSaveAndPublish = By.xpath("");

    public void inputProductInformation(String productName, String category, String brand, String unit, String weight, String qty, ArrayList<String> Tag) {
        WebUI.sendText(inputProductName, productName);
        WebUI.selectDropdown(dropdownCategory, inputCategory, category);
        WebUI.selectDropdown(dropdownBrand, inputBrand, brand);
        WebUI.sendText(inputUnit, unit);
        WebUI.sendText(inputWeight, weight);
        WebUI.sendText(inputMinimunPurchaseQty, qty);
        WebUI.Sleep(2);
        WebUI.scrollPageToElement(inputTag);
        for (String s : Tag) {
            WebUI.sendText(inputTag, s);
            System.out.println(s);
            DriverManager.getDriver().findElement(inputTag).sendKeys(Keys.ENTER);
        }
        System.out.println("input success");
    }

    public void setProductImage() {

    }

    public void setProductVideos() {
    }

    public void setProductVariation(ArrayList<String> arrayListColors) {
        WebUI.scrollPageToElement(By.xpath("//h5[normalize-space()='Product Variation']"), 2);
        if (!arrayListColors.isEmpty()) {
            WebUI.clickElement(checkboxEnableColors);
            WebUI.selectDynamicDropdownMultiValue(dropdownColors, arrayListColors);
        }
        if (arrayListColors.size() > 1) {
            Assert.assertEquals(DriverManager.getDriver().findElement(selectedColors).getText(), arrayListColors.size() + " items selected", "Sai số lượng màu đã chọn");
        } else if (arrayListColors.size() == 1) {
            Assert.assertEquals(DriverManager.getDriver().findElement(selectedColors).getText(), arrayListColors.get(0), "Sai màu đã chọn");
        } else {
            Assert.assertEquals(DriverManager.getDriver().findElement(selectedColors).getText(), "Nothing selected", "Đã có chọn màu");
        }
        System.out.println("Selected colors:" + DriverManager.getDriver().findElement(selectedColors).getText());
    }


    public void setProductPriceAndStock(String unitPrice, String discount, String Quantity) {
        WebUI.scrollPageToElement(By.xpath("//h5[normalize-space()='Product price + stock']"), 2);
        if (unitPrice != null && !unitPrice.isEmpty()) {
            WebUI.sendText(inputUnitPrice, unitPrice);
        }

        if (discount != null && !discount.isEmpty()) {
            WebUI.sendText(inputDiscount, discount);
        }

        if (Quantity != null && !Quantity.isEmpty()) {
            WebUI.sendText(inputQuantity, Quantity);
        }
        System.out.println("input price and stock success");

    }


    public void setProductDescription() {
    }

    public void setPDFSpecification() {
    }

    public void setSEOMetaTags() {
    }

    public void setLowStockQuantityWarning() {
    }

    public void setStockVisibilityState() {
    }

    public void setCashOnDelivery() {
    }

    public void setFeatured() {
    }

    public void setTodaysDeal() {
    }

    public void setFlashDeal() {
    }

    public void setEstimateShippingTime() {
    }

    public void setVatAnfTax() {
    }

    public ProductPage clickButtonSaveAndUnpublish() {
        WebUI.scrollPageToElement(buttonSaveAndUnpublish, 2);
        WebUI.clickElement(buttonSaveAndUnpublish);
        System.out.println("click button save and unpublish success");
        return new ProductPage();
    }

    public void clickButtonSaveAndPublish() {
    }

    public void verifyStayInAddNewProductPage() {
        Assert.assertEquals(DriverManager.getDriver().findElement(textMainContent).getText(), "Add New Product", "Wrong main content of page");
        System.out.println("Verify stay in success");
    }


}
