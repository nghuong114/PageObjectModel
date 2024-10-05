package huong.test.commonPage;

import helpers.CaptureHelper;
import helpers.PropertiesHelper;
import huong.test.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.time.Duration;


public class BaseTest {
    public static WebDriver driver;

    @BeforeMethod

    public void createDriver(Method method) {
        PropertiesHelper.loadAllFiles();
        WebDriver driver = setBrowser(PropertiesHelper.getValue("browser"));
        CaptureHelper.startRecord(method.getName());
        DriverManager.setDriver(driver); // theem driver vào trong thread local
        System.out.println("create driver success");
    }

    public WebDriver setBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName.trim().toUpperCase()) {
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            default:
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver(ITestResult result) {
        // Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Step
        // Ở đây sẽ so sánh điều kiện nếu testcase passed hoặc failed
        // passed = SUCCESS và failed = FAILURE
        CaptureHelper.stopRecord();
        if (ITestResult.FAILURE == result.getStatus()) {
            try {

                CaptureHelper.takeScreenShot(result.getName());

            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
        DriverManager.quit();
    }

    public static void Sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
