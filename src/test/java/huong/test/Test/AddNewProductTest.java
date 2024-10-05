package huong.test.Test;

import DataProviderFactory.DataProviderFactory;
import huong.test.Page.AddNewProductPage;
import huong.test.Page.DashboardPage;
import huong.test.Page.LoginPage;
import huong.test.commonPage.BaseTest;
import listener.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;

@Listeners(TestListener.class)
public class AddNewProductTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddNewProductPage addNewProductPage;


    @Test(dataProvider = "DataAddNewProduct", dataProviderClass = DataProviderFactory.class)
    public void AddNewProduct_Success_WithColors(String productName, String category, String brand, String unit, String weight, String qty, ArrayList<String> listtags, ArrayList<String> listColors, ArrayList<String> listSize, String unitPrice, String discount, String Quantity) {
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        dashboardPage.verifyStayinDashboard();
        dashboardPage.clickMenuProducts();
        addNewProductPage = dashboardPage.clicOptionAddNewProduct();
        addNewProductPage.verifyStayInAddNewProductPage();
        addNewProductPage.inputProductInformation(productName, category, brand, unit, weight, qty, listtags);
        addNewProductPage.setProductVariation(listColors);
        addNewProductPage.setProductPriceAndStock(unitPrice, discount, "");
        addNewProductPage.clickButtonSaveAndUnpublish();

    }
}
