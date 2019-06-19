package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class accountNumberPage {
	
	WebDriver driver;
	
	public accountNumberPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By acct_nbr=By.cssSelector("input[id='myForm:qckaccno_input']");
	
	public void enter_account_number(String acctnum)
	{
		
		
		System.out.println("Enetring the account numebr ------------- ");
		driver.findElement(acct_nbr).sendKeys(acctnum);
		System.out.println("Account Number entered ----------- ");
		driver.findElement(acct_nbr).sendKeys(Keys.RETURN);
		//driver.findElement(acct_nbr).clear();
		
	}

	
}
