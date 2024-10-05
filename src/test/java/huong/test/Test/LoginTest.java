package huong.test.Test;

import DataProviderFactory.DataProviderFactory;
import huong.test.Page.DashboardPage;
import huong.test.Page.LoginPage;
import huong.test.WebUI;
import huong.test.commonPage.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    //Khai báo đối tượng LoginPage
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void LoginSuccess() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS("admin@example.com", "123456");
        loginPage.verifyLoginSuccess();

    }

    @Test(dataProvider = "DataLoginEmpty", dataProviderClass = DataProviderFactory.class)
    public void LoginFailed_Empty(String email, String password) {
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS(email, password);
        WebUI.Sleep(1);
        if (email == "") {
            loginPage.verifyLoginMissingEmail();
        } else if (password == "") {
            loginPage.verifyLoginMissingPassword();
        }
    }

    @Test(dataProvider = "DataLoginInvalid", dataProviderClass = DataProviderFactory.class)
    public void LoginFailed_inValidPassword(String email, String password) {
        loginPage = new LoginPage();
        dashboardPage = loginPage.LoginCMS(email, password);
        WebUI.Sleep(1);
        loginPage.verifyLoginFailed();
    }

}
