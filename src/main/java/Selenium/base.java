package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;

public class base {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.18.0-win64\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		
		driver.get("https://testsso.secure.fedex.com/ecisdev/jsp/customerContact.xhtml");
		
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys("804229");
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("804229");
		
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
	
		
		driver.findElement(By.xpath(".//*[@id='myForm:qckaccno_input']")).sendKeys("696593400");
		
		driver.findElement(By.xpath(".//*[@id='myForm:qckaccno_input']")).sendKeys(Keys.RETURN);
		
		Thread.sleep(10000);
		
		String Cust_Stat=driver.findElement(By.xpath(".//*[@id='myForm:custsum:custStatusVal']")).getText();
		System.out.println("Customer Status is "+Cust_Stat);
		
		
		String Cust_Name=driver.findElement(By.xpath(".//*[@id='myForm:custsum:custNameVal']")).getText();
		System.out.println("Customer Name is "+Cust_Name);
		
		String Shipper_Address1=driver.findElement(By.xpath(".//*[@id='myForm:custsum:shipperAddrl1']")).getText();
		System.out.println("Shipper Address Line 1 "+Shipper_Address1);
		
		String Shipper_Address2=driver.findElement(By.xpath(".//*[@id='myForm:custsum:shipperaddrl3']")).getText();
		System.out.println("Shipper Address Line 2 "+Shipper_Address2);
		
		
	}

}



