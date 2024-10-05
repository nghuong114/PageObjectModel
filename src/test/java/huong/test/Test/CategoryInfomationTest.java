package huong.test.Test;

import DataProviderFactory.DataProviderFactory;
import huong.test.Page.CategoriesPage;
import huong.test.Page.CategoryInfomationPage;
import huong.test.Page.DashboardPage;
import huong.test.Page.LoginPage;
import huong.test.WebUI;
import huong.test.commonPage.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CategoryInfomationTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CategoriesPage categoriesPage;
    CategoryInfomationPage categoryInfomationPage;
    String categoryName_global = "";

    @Test(dataProvider = "DataAddNewCategory", dataProviderClass = DataProviderFactory.class)
    public void AddNewCategory_Success(String categoryName, String bannerName, String iconName, String metaTitle, String description) {
        categoryName_global = categoryName;
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        dashboardPage.verifyStayinDashboard();
        dashboardPage.clickMenuProducts();
        categoriesPage = dashboardPage.clicOptionCategory();
        categoriesPage.verifyStayinCategoriesPage();
        categoryInfomationPage = categoriesPage.clickButtonAddNewCategory();
        categoryInfomationPage.verifyStayinCreateCategoryPage();
        categoryInfomationPage.inputCategoryForm(categoryName, bannerName, iconName, metaTitle, description);
        categoriesPage = categoryInfomationPage.clickButtonSave();
        categoriesPage.verifyStayinCategoriesPage();
        WebUI.Sleep(1);
        categoriesPage.verifyAddNewcategorySuccess();
        categoriesPage.searchCategoryByName(categoryName);
        categoriesPage.verifyFirstSearchResult(categoryName);


    }

    @Test
    @Parameters({"editedCategoryName"})
    public void EditCategory_Success(String editedData) {
        String categoryName = categoryName_global;
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        dashboardPage.verifyStayinDashboard();
        dashboardPage.clickMenuProducts();
        categoriesPage = dashboardPage.clicOptionCategory();
        categoriesPage.verifyStayinCategoriesPage();
        categoriesPage.searchCategoryByName(categoryName);
        WebUI.Sleep(2);
        categoryInfomationPage = categoriesPage.clickButtonEditCategory();
        categoryInfomationPage.editCategoryInfo("Meta Title", editedData);
        Sleep(1);
        categoryInfomationPage.clickButtonSave();
        Sleep(1);
        categoryInfomationPage.verifyUpdateCategorySuccess();

    }

    @Test
    @Parameters({"categoryName"})
    public void deleteCategory_success() {
        String categoryName = categoryName_global;
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        dashboardPage.verifyStayinDashboard();
        dashboardPage.clickMenuProducts();
        categoriesPage = dashboardPage.clicOptionCategory();
        categoriesPage.verifyStayinCategoriesPage();
        categoriesPage.searchCategoryByName(categoryName);
        WebUI.Sleep(2);
        categoriesPage.clickButtonDeleteCategory();
        categoriesPage.DeleteCategory();
        categoriesPage.verifyDeleteCategorySuccess();
    }
}
