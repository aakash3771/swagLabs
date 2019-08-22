package Utils.listener;

import BaseTest.BaseTest;
import Utilities.ExtentTestManager;
import Utilities.folderHelper;
import Utilities.pathHelpers;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class RetryAnalyser extends BaseTest implements IRetryAnalyzer {
    private int counter = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {                      //Check if test not succeed
            int retryLimit = 1;
            if (counter < retryLimit) {                            //Check if maxtry count is reached
                counter++;                                     //Increase the maxTry count by 1
                result.setStatus(ITestResult.FAILURE);  //Mark test as failed and take base64Screenshot
                extendReportsFailOperations(result);    //ExtentReports fail operations
                return true;                                 //Tells TestNG to re-run the test
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }

    private void extendReportsFailOperations(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String testClassName = iTestResult.getInstanceName();
        String timeStamp = String.valueOf(new Timestamp(new Date().getTime()));
        String testMethodName = iTestResult.getName();
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
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
    }
}
