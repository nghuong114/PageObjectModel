package huong.test.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>(); //Khởi tạo threadLocal -> lưu một list session driver
    private DriverManager(){

    }
    public static WebDriver getDriver(){
        return driver.get(); // lấy ra sessionid của driver hiện tại trong thread local
    }
    public static void setDriver(WebDriver driver){
    // Lưu giá trị driver vào trong thredlocal
    DriverManager.driver.set(driver);

    }
    public static void quit(){
        //Xoá session của driver khỏi threadlocal
        DriverManager.driver.get().quit();
        driver.remove();
    }
}
