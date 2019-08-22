package Utils.listener;

import BaseTest.BaseTest;
import Utilities.ExtentManager;
import Utilities.ExtentTestManager;
import Utilities.folderHelper;
import Utilities.pathHelpers;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class customListener extends BaseTest implements ITestListener {
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public synchronized void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public synchronized void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        //ExtentTestManager.endTest();//TODO check if this change makes any diff and then uncomment and try again. Note diff.
        ExtentManager.getInstance().flush();
    }

    public synchronized void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public synchronized void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");

        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String testClassName = result.getInstanceName();
        String timeStamp = String.valueOf(new Timestamp(new Date().getTime()));
        String testMethodName = result.getName();
        String screenShotName = testMethodName + timeStamp + ".png";
        try {
            folderHelper.CreateDirectory(pathHelpers.returnScreenShotFolderPath()
                    + pathHelpers.fileSeperator() + testClassName + pathHelpers.fileSeperator());

            File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            File destination = new File(pathHelpers.returnScreenShotFolderPath() + pathHelpers.fileSeperator()
                    + testClassName + pathHelpers.fileSeperator() + screenShotName);
            FileUtils.copyFile(source, destination);
            ExtentTestManager.getTest().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(
                    pathHelpers.returnScreenShotFolderPath() + pathHelpers.fileSeperator() + testClassName
                            + pathHelpers.fileSeperator() + screenShotName).build());
        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }
}
