package Selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utility.readConfig;

public class baseClass {

	WebDriver driver;
	Properties prop;
	
	public WebDriver intializeDriver() throws Exception {

		readConfig rc=new readConfig();
		prop=rc.readC();

		if (prop.getProperty("Browser").equalsIgnoreCase("firefox")) {
			//System.setProperty(prop.getProperty("firefoxDriver"), prop.getProperty("firefoxPath"));
			System.setProperty(prop.getProperty("firefoxDriver"), ".//resources//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		if(prop.getProperty("Browser").equalsIgnoreCase("chrome")) {
			//System.setProperty(prop.getProperty("chromeDriver"), prop.getProperty("chromePath"));
			System.setProperty(prop.getProperty("chromeDriver"), ".//resources//chromedriver.exe");
			driver=new ChromeDriver();
		}

		return driver;

	}

}



