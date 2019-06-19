package Pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import Utility.DBConnection;
import Utility.readConfig;

public class shipprofPage {
	
	
	WebDriver driver;
	Properties prop;
	public Logger logger;
	
	String alphaid;
	String agent;
	String status;
	String country;
	
	String custname;
	String acctype;
	String Phone ;
	String primaryName;
	String primaryNameTitle;
	String city;
	String state;
	String postal;
	
	
	
	String crdt_cd_type;
	String selected_crdt_cd_type;
	
	String crdt_cd_nbr;
	String crdt_cd_exp_dt;
	
	String credit_mnth;
	String credit_yr;
	
	
	public shipprofPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void get_shipinfo(String flag) throws Exception
	{
		
		logger=Logger.getLogger("shipprofPage");
				
		logger.info("-----------Inside the ship prof page-------------");
		
		
		Thread.sleep(3000);
		

		By alpha=By.cssSelector("input[id='myForm:custsum:alphaIdVal']");
		alphaid=driver.findElement(alpha).getAttribute("value");
		logger.info("Alpha ID is "+alphaid);
		
		By customer_name=By.name("myForm:custsum:custNameVal");
		custname=driver.findElement(customer_name).getAttribute("value");
		logger.info("Customer name is ------"+custname);
			
		
		By sts = By.cssSelector("input[id='myForm:custsum:custStatusVal']");
		String count=driver.findElement(sts).getAttribute("value");
		String[] mycounts=count.split("-");
		System.out.println("The length of array is ---------------------"+mycounts.length);
		status=mycounts[0];	
		logger.info("Status is "+status);
		
		By act_type=By.name("myForm:custsum:accounttype");
		String act=driver.findElement(act_type).getAttribute("value");
		String[] myacts=act.split("-");
		System.out.println("The length of act array is ----------------"+myacts.length);
		acctype=myacts[0];
		logger.info("Accout Type is "+acctype);
		
		By PHONE=By.id("myForm:custsum:phoneNum");
		Phone=driver.findElement(PHONE).getText();
		if(Phone.contains("ext"))
		{
			String myphones[]= Phone.split(" ");
			Phone=myphones[0];
		}
		logger.info("Shipper Primary Phone is "+Phone);
		
		/*	By stn =	By.cssSelector("input[id='myForm:custsum:stationIdVal']");	
		station =driver.findElement(stn).getText();
		station="";
		logger.info("Station is "+station);*/
	
//	    By agnt = By.cssSelector("select[id='myForm:custsum:_t155']");
//		agent=driver.findElement(agnt).getAttribute("value");
		agent="N";
		logger.info("Agent is  "+agent);
			
		By primary_name=By.xpath(".//*[@id='myForm:custsum:fname']");
		primaryName=driver.findElement(primary_name).getText();
		logger.info("Primary Name is "+primaryName);
			
			
		String[] mytitles=primaryName.split(" ");
		primaryNameTitle=mytitles[0];
		logger.info("Primary name title is "+primaryNameTitle);
			
			
	
			//capturing country from billcust page (regulatory and info page)	
			By city_state_postal= By.cssSelector("label[id='myForm:custsum:shipperaddrl3']");
			String str=driver.findElement(city_state_postal).getText();
			String[] mystrs=str.split(",");
			
			By cntry=By.id("myForm:custsum:shipperCntry");
			String country=driver.findElement(cntry).getText();
			
			if(country.equalsIgnoreCase("USA")||country.equalsIgnoreCase("US"))
			{
				
			logger.info("the country is "+country);
			
			city=mystrs[0].trim();
			logger.info("City is "+city);
			
			state=mystrs[1].trim();
			logger.info("State is "+state);
			
			postal=mystrs[2].trim();
			logger.info("Postal is "+postal);
			
			}
			else
			{
				logger.info("the country is "+country);
			
				if(mystrs.length>2)
					
				{
				city=mystrs[0].trim();
				logger.info("City is "+city);
				
				state=mystrs[1].trim();
				logger.info("State is "+state);
				
				city=city+","+state;
				logger.info("The final city is "+city);
				
				postal=mystrs[2].trim();
				logger.info("Postal is "+postal);
				
				}
				else
				{
				
					city=mystrs[0].trim();
					logger.info("City is "+city);
					
					state="";
					logger.info("State is "+state);
							
					postal=mystrs[1].trim();
					logger.info("Postal is "+postal);
				}
				
				
			}
		
		
		
		driver.navigate().back();
	
		logger.info("-----------going to home page-----------------");
		
		Thread.sleep(1000);
		
		By bank_collection=By.xpath(".//*[@id='myForm:bankingColl']");
		driver.findElement(bank_collection).click();
		logger.info("------------At banking and Collection page--------------");

	
		
		Thread.sleep(3000);
		
		logger.info("Inside the banking and collections page");
		
		
		JavascriptExecutor js1=(JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,500)");
		
		
		//By credit_type=By.cssSelector("select[id='myForm:crdttypeval']");
		// myForm:custsum:credtCrdTypVal
		By credit_type=By.cssSelector("select[id='myForm:crdttypeval']");
		Select crdt_cd_type=new Select(driver.findElement(credit_type));
		selected_crdt_cd_type=crdt_cd_type.getFirstSelectedOption().getText();
		logger.info("Credit typeeeeee "+selected_crdt_cd_type);
		if(selected_crdt_cd_type.equals("Select"))
		{
			logger.info("inside if");
			selected_crdt_cd_type="";
		}
		
		//Added on 29th Oct 2019
		else {
			
			logger.info("inside else");
			String[] types=selected_crdt_cd_type.split("-");
			selected_crdt_cd_type=types[0];
		}
		
		
		logger.info("Credit Type is "+selected_crdt_cd_type);
		
		By credit_num=By.cssSelector("input[id='myForm:cardNumval']");
		crdt_cd_nbr=driver.findElement(credit_num).getAttribute("value");
		logger.info("Credit Number is "+crdt_cd_nbr);
		
					
		/*JavascriptExecutor js=(JavascriptExecutor) driver;
		driver.manage().window().maximize();
		js.executeScript("window.scrollBy(0,500)");
		*/
		
		/*By credit_exp=By.cssSelector("");
		crdt_cd_exp_dt=driver.findElement(credit_exp).getAttribute("value");*/
		By crdt_cd_exp_mnth=By.cssSelector("input[id='myForm:cCExpiryMonth']");
		credit_mnth=driver.findElement(crdt_cd_exp_mnth).getAttribute("value");
		By crdt_cd_exp_year=By.cssSelector("input[id='myForm:cCExpiryYear']");
		credit_yr=driver.findElement(crdt_cd_exp_year).getAttribute("value");
		
		crdt_cd_exp_dt=credit_mnth+"/"+credit_yr;
		
		if(crdt_cd_exp_dt=="/")
		{
			crdt_cd_exp_dt=null;
		}
		
		logger.info("Credit Expiry is "+crdt_cd_exp_dt);
		
		
		
		/*By cust_ref=By.id("myForm:custsum:custRefVal");
		CUST_REF=driver.findElement(cust_ref).getAttribute("value");
		System.out.println("The cust ref is "+CUST_REF);
				
		By pws=By.id("myForm:custsum:autmtnDevicsTypsVal");
		PWS_AUTO=driver.findElement(pws).getAttribute("value");
		System.out.println("The pws auto is "+PWS_AUTO);
		
		By first_ship=By.id("myForm:custsum:firstShpDateVal");
		FIRST_SHIP=driver.findElement(first_ship).getAttribute("value");
		System.out.println("The first ship is "+FIRST_SHIP);
		
		By last_ship=By.id("myForm:custsum:lastShpDateVal");
		LAST_SHIP=driver.findElement(last_ship).getAttribute("value");
		System.out.println("The last ship is "+LAST_SHIP);
		
		
		By nat_acct=By.id("myForm:custsum:natnlAccVal");
		NATL_ACCT=driver.findElement(nat_acct).getAttribute("value");
		System.out.println("The nat account is "+NATL_ACCT);
		
		By geo=By.id("myForm:custsum:geoAggregtnVal");
		GEO_AGGR=driver.findElement(geo).getAttribute("value");
		System.out.println("The geo is "+GEO_AGGR);
		
		By ss=By.id("myForm:custsum:ssAggrgtnVal");
		SS_AGGR=driver.findElement(ss).getAttribute("value");
		System.out.println("The ss is "+SS_AGGR);*/
		
		}
		


	
	public String insert_data(String test_id, String db_name, String acct_nbr, Connection con, String level) throws Exception
	{
		readConfig rc = new readConfig();
		prop=rc.readC();
	
			
		/*String query1="insert into E_L3_CHEERS_SHIPPROF_TEST (TEST_ID, DBNAME, ACCTNBR, ALPHA_ID, AGENT, NAME, STATUS, STATION, CNTRY, CREDT_CD_TYPE, CREDT_CD_NBR, CREDT_CD_EXP_DT )"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";*/
		
		String query1="insert into E_L"+level+"_CHEERS_SHIPPROF (TEST_ID, DBNAME, ACCTNBR, ALPHA_ID, AGENT, STATUS, CREDT_CD_TYPE, CREDT_CD_NBR, CREDT_CD_EXP_DT, CUST_TYPE, CONTACT_PHONE, CO_NAME, CITY, ST_PV, POSTAL, CONTACT, CONTACT_TITLE )"
		        + " values ('"+test_id+"', '"+db_name+"', '"+acct_nbr+"', '"+alphaid+"', '"+agent+"', '"+status+"', '"+selected_crdt_cd_type+"', '"+crdt_cd_nbr+"', '"+crdt_cd_exp_dt+"', '"+acctype+"', '"+Phone+"', '"+custname+"', '"+city+"', '"+state+"', '"+postal+"','"+primaryName+"', '"+primaryNameTitle+"')";
		
		return query1; 
		
	}

}
