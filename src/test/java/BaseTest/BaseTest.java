package BaseTest;

import Utilities.PropertyReader;
import Utilities.folderHelper;
import Utilities.pathHelpers;
import drivers.driverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class BaseTest{
	protected WebDriver driver;

public WebDriver getDriver()
{
	return driver;
}
	@BeforeSuite
	public void setup()
	{
		CleanUp();
	}
	private void CleanUp()
	{
		folderHelper.CreateDirectory(pathHelpers.returnScreenShotFolderPath());
		folderHelper.deleteFolder(pathHelpers.returnScreenShotFolderPath());
		folderHelper.CreateDirectory(pathHelpers.returnTestReportFolderPath());
		folderHelper.deleteFolder(pathHelpers.returnTestReportFolderPath());
		folderHelper.CreateDirectory(pathHelpers.returnLogsFolderPath());
		folderHelper.deleteFolder(pathHelpers.returnLogsFolderPath());
	}

	@AfterMethod(alwaysRun = true)
	public void TearDown() {
        driver.quit();
	}

	@Parameters({ "platform","browser", "nodeURL" })
	@BeforeMethod(alwaysRun=true)
	public void startUp(@Optional("Mac") String platform, @Optional("chrome") String browser, @Optional(" ") String nodeURL) throws MalformedURLException {
		if (PropertyReader.readApplicationFile("type").equals("solo"))
			driver = new driverFactory().getDriver(browser);
		if (PropertyReader.readApplicationFile("type").equals("seleniumgrid"))
		driver = new driverFactory().getRemoteDriver(platform, browser, nodeURL);
	}
}