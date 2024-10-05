package listener;

import com.aventstack.extentreports.Status;
import helpers.PropertiesHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.AllureManager;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.Log;


public class TestListener implements ITestListener {
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onFinish(ITestContext result) {
        Log.info("End testing " + result.getName());

        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onStart(ITestContext result) {
        PropertiesHelper.loadAllFiles();
        Log.info("#########################################");
        //Khởi tạo report (Extent và Allure)
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("Test case " + result.getName() + " is failed.");
        //Screenshot khi fail
        //CaptureHelper.captureScreenshot(result.getName());
        Log.error(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.addScreenshot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");
//Allure Report
        AllureManager.saveTextLog(result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.error("Test case " + result.getName() + " is skipped.");
        Log.error(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());

    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("#########################################");
        Log.info("Running test case " + result.getName());

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Test case " + result.getName() + " is passed.");

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");

    }
}

