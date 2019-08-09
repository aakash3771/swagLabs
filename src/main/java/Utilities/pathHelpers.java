package Utilities;

import java.io.File;

public class pathHelpers {
	private static final String currentDir = System.getProperty("user.dir");
	/*public static String returnDriverExePath(String driver)
	{
		return currentDir + File.separator + "src" + File.separator + "main" + File.separator +
				"java" + File.separator + "drivers" + File.separator + driver + "driver.exe";
	}*/

	public static String returnConfigsPath()
	{
		return currentDir + File.separator + "src" + File.separator + "main" + File.separator +
				"java" + File.separator + "Configs" + File.separator;
	}
	
	public static String returnTestDataPath()
	{
		return currentDir + File.separator + "src" + File.separator + "test" + File.separator +
				"java" + File.separator + "testData" + File.separator;
	}

	public static String returnPropertiesPackagePath(){
		return currentDir + File.separator + "src" + File.separator + "main" + File.separator +
				"java" + File.separator + "Properties" + File.separator + "properties.xml";
	}

	public static String returnScreenShotFolderPath()
	{
		return currentDir + File.separator + "test-output" + File.separator + "Screenshots" + File.separator;
	}
	
	public static String returnTestReportFolderPath()
	{
		return currentDir + File.separator + "Reports" + File.separator;
	}
}
