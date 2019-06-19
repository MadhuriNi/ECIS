package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {

	public WebDriver driver;
	
	public loginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
/*	By uid=By.id("username"); 
	By pass=By.name("password");
	By submit=By.id("submit");*/
	
	By uid=By.cssSelector("input[id='username']");
	By pass=By.cssSelector("input[type='password']");
	By submit=By.cssSelector("input[type='submit']");
	
	public void enterData(String user, String pwd)
	{
		driver.findElement(uid).sendKeys(user);
		driver.findElement(pass).sendKeys(pwd);
		driver.findElement(submit).click();
	}
	
	
}
