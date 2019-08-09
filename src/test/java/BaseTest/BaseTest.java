package BaseTest;

import Utilities.pathHelpers;
import drivers.driverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;

public class BaseTest{

	private static final String currentDir = System.getProperty("user.dir");
	protected WebDriver driver;
	//protected ExtentTest test;
	//protected ExtentReports extent;

	//TODO will need to add logs

	@BeforeSuite
	public void CleanUp()
	{
		deleteFolder(pathHelpers.returnTestReportFolderPath());
		System.out.println("Test reports folder deleted");
		deleteFolder(pathHelpers.returnScreenShotFolderPath());
		System.out.println("Screenshots folder deleted");
	}

	private void deleteFolder(String folderPath) {
		File dir = new File(folderPath);

		if(!dir.isDirectory()) {
			System.out.println("Not a directory. Do nothing");
			return;
		}
		File[] listFiles = dir.listFiles();
		assert listFiles != null;
		for(File file : listFiles){
			System.out.println("Deleting "+file.getName());
			file.delete();
		}
	}

	@AfterSuite
	public void endReport() {
		//extent.flush();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void TearDown(){
        driver.quit();
	}

	@BeforeMethod
	public void SetUp()
	{
		driver = new driverFactory().getDriver();
	}
/*
	@BeforeTest
	public void startReport() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(currentDir + File.separator + "Reports" + File.separator + "ExtentReport.html");
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Host Name", "Helm-Mac033");
		extent.setSystemInfo("Environment", "LocalHost");
		extent.setSystemInfo("Tester", "Aakash");
		extent.setSystemInfo("App Name", "Paperless");
		extent.setSystemInfo("Version", "19.1.1615");

		htmlReporter.config().setDocumentTitle("Paperless Test Report"); 
		htmlReporter.config().setReportName("Regression Testing"); 
		htmlReporter.config().setTheme(Theme.DARK);
	}
 */
}