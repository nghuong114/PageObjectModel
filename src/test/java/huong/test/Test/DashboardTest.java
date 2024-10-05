package huong.test.Test;

import huong.test.Page.CustomerPage;
import huong.test.Page.DashboardPage;
import huong.test.Page.LoginPage;
import huong.test.commonPage.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;

    @Test
    public void Logout_Success() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        loginPage.verifyLoginSuccess();
        dashboardPage.LogoutCRM();

    }

    @Test
    public void OpenCustomersMenu_Success() {
        loginPage = new LoginPage();
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS();
        loginPage.verifyLoginSuccess();
        customerPage = dashboardPage.clickMenuCustomers();
        customerPage.verifyStayinCustomerPage();
    }
}
