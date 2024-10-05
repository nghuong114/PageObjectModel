package huong.test.Test;

import huong.test.Page.BrandsPage;
import huong.test.Page.DashboardPage;
import huong.test.Page.LoginPage;
import huong.test.commonPage.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Log;

public class BrandsTest extends BaseTest {
    DashboardPage dashboardPage;
    BrandsPage brandsPage;
    LoginPage loginPage;

    @Test
    @Parameters({"keyword1"})
    public void Searchbrand_ExistingSingle(String keyword) {
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        dashboardPage.verifyStayinDashboard();
        dashboardPage.clickMenuProducts();
        brandsPage = dashboardPage.clicOptionBrand();
        brandsPage.verifyStayInBrandsPage();
        brandsPage.searchBrand(keyword);
        brandsPage.verifySearchBrand(keyword, true);
    }

    @Test
    @Parameters({"keyword2"})
    public void Searchbrand_ExistingMultiple(String keyword) {
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        dashboardPage.verifyStayinDashboard();
        dashboardPage.clickMenuProducts();
        brandsPage = dashboardPage.clicOptionBrand();
        brandsPage.verifyStayInBrandsPage();
        brandsPage.searchBrand(keyword);
        brandsPage.verifySearchBrand(keyword, true);
        Log.info("Search success");
    }

    @Test
    @Parameters({"brandsName"})
    public void AddNewBrand(String brandsName) {
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        dashboardPage.verifyStayinDashboard();
        dashboardPage.clickMenuProducts();
        brandsPage = dashboardPage.clicOptionBrand();
        brandsPage.verifyStayInBrandsPage();
        brandsPage.setNewBrandInfo(brandsName, "car", "Tiêu đề tên hãng 001", "Mô tả tiêu đề tên hãng 001");
        brandsPage.verifyAddNewBrandSuccess(brandsName);
    }

    @Test
    @Parameters({"brandsName"})
    public void DeleteBrand(String brandName) {
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        dashboardPage.verifyStayinDashboard();
        dashboardPage.clickMenuProducts();
        brandsPage = dashboardPage.clicOptionBrand();
        brandsPage.verifyStayInBrandsPage();
        brandsPage.searchBrand(brandName);
        brandsPage.clickDeleteBrand(brandName);
        brandsPage.verifyDeleteSuccess(brandName);
    }


}
