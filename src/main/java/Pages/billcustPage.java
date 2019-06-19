package Pages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Utility.DBConnection;
import Utility.readConfig;

public class billcustPage {
	
	WebDriver driver;
	Properties prop;
	public Logger logger;
	String vat;
	String vatno;
	String billmode;
	String country;
	
	public billcustPage(WebDriver driver)
	{
		this.driver=driver;
	}

	
	
	public void get_billcustinfo() throws Exception
	{
		
		//logger=Logger.getLogger("custInfoPage");
		logger=Logger.getLogger("billcust");
		
		logger.info("-----------Inside the bill cust page-------------");
		
		
		/*Select drpscreen = new Select(driver.findElement(By.id("myForm:navigationDrpDwn1")));
		drpscreen.selectByVisibleText("Regulatory & Legal Info");
		
		By view=By.xpath(".//*[@id='myForm:viewButton']");
		driver.findElement(view).click();*/
		
		/*By home_page=By.id("myForm:quicksearLandngLbl_link");
		driver.findElement(home_page).click();*/
		
		driver.navigate().back();
		logger.info("------------Clicking home page--------------");
		
		
		By reg_info=By.xpath(".//*[@id='myForm:RegInfo']");
		driver.findElement(reg_info).click();
		logger.info("------------At Regulatory info page--------------");
		
		Thread.sleep(2000);
		
		By cntry=By.xpath("//input[@id='myForm:shippngcntry']");
		country=driver.findElement(cntry).getAttribute("value");
		logger.info("the country is ------"+ country);
		
		//country="US";
		
		Thread.sleep(8000);
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,4000)");
		
		By vat = By.xpath("//input[@id='myForm:vat1']");
		vatno=driver.findElement(vat).getAttribute("value");
		logger.info("the vatno is -- "+vatno);
		
		
		//Adding code for bill mode
		driver.navigate().back();
		logger.info("------------Clicking home page--------------");
		
		By cust_invoice=By.xpath(".//*[@id='myForm:custInvoice']");
		driver.findElement(cust_invoice).click();
		logger.info("-------At customer invoice page--------------");
		
		Thread.sleep(4000);
		
		//driver.findElement(By.id("myForm:qckaccno_input")).clear();
		
		logger.info("Clicking-----------------");
		
		JavascriptExecutor js1=(JavascriptExecutor) driver;
		//js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//js1.executeScript("window.scrollBy(0,4000)");
		js1.executeScript("scroll(0,8000);");
		
		try {
		By bill_mode=By.xpath(".//*[@id='myForm:billingmode1']");
		Select select_billmode=new Select(driver.findElement(bill_mode));
		billmode=select_billmode.getFirstSelectedOption().getText();
		logger.info("Domestic Bill mode before extraction "+billmode);
		String[] mybillmodes=billmode.split("-");
		billmode=mybillmodes[0];
		logger.info("Domestic Bill Mode is "+billmode);
		
		
		}
		catch (Exception e){
		By bill_mode=By.xpath(".//*[@id='myForm:intlbilngmode1']");
		Select select_billmode=new Select(driver.findElement(bill_mode));
		billmode=select_billmode.getFirstSelectedOption().getText();
		logger.info("Intl Bill Mode before extraction "+billmode);
		String[] mybillmodes=billmode.split("-");
		billmode=mybillmodes[0];
		logger.info("Intl Bill Mode is "+billmode);
			
		}
		
	} 
	
	
	public String insert_data(String test_id, String db_name, String acct_nbr, Connection con, String level) throws Exception
	{
		
		readConfig rc = new readConfig();
		prop=rc.readC();
		
		String query3="insert into E_L"+level+"_CHEERS_BILLCUST (TEST_ID, DBNAME, ACCTNBR, VATNO, BILLMODE)"
		        + " values ('"+test_id+"', '"+db_name+"', '"+acct_nbr+"',  '"+vatno+"' , '"+billmode+"')";
	
		
		logger.info("data inserted in BILLCUST table");
		
	
		
		/*driver.navigate().back();
	    logger.info("Going back to Account summary Page");
				
		
		Thread.sleep(3000);*/
		
		return query3; 
		
	}
	
	
	public String update_data(String test_id, String db_name, String acct_nbr, Connection con, String level, Statement smt2) throws Exception
	{
		
		readConfig rc = new readConfig();
		prop=rc.readC();
		
		
		ResultSet rs5 = smt2.executeQuery("select COUNTRY_CODE from countrycodes where UPPER(COUNTRY_NAME) =  '"+country+"' ");
		String query8=null ;
		
		while (rs5.next()) {
			
			String country_d=rs5.getString("COUNTRY_CODE");
		
		logger.info("The country code got is ------------------ "+country_d);
			
		 query8="update E_L"+level+"_CHEERS_SHIPPROF set CNTRY='"+country_d+"' "
		        + " WHERE ACCTNBR='"+acct_nbr+"'";
	
		
		logger.info("data updated in SHIPPROF table");
		
		}
		
		driver.navigate().back();
	    logger.info("Going back to Account summary Page");
				
		
		Thread.sleep(3000);
		
		return query8; 
		
		
	}
}
