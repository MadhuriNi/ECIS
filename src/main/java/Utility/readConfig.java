package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class readConfig {

	Properties prop;
	
	public Properties readC() throws Exception
	{
		File src=new File("C://ecis//Config.property");
		FileInputStream fis=new FileInputStream(src);
		
		prop=new Properties();
		prop.load(fis);
		
		return prop;
	}
	
	 
}
