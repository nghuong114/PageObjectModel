package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extentreport/extentreport.html");
        reporter.config().setReportName("Extent Report | CMS");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Selenium Java | Huong");
        extentReports.setSystemInfo("Author", "Huong.NTQ");
        return extentReports;
    }

}
