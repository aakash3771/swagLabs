package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
	public static String readApplicationFile(String key)
    { 
    	String value = "";
        try
        {         	  
	          Properties prop = new Properties();
	          File f = new File(pathHelpers.returnConfigsPath()+"Application.Properties");
	          if(f.exists())
	          {
		          prop.load(new FileInputStream(f));
		          value = prop.getProperty(key); 		                 
           	  }
	   }
        catch(Exception e)
        {  
           System.out.println("Failed to read from application.properties file.");  
        }
        return value;
     } 

}
