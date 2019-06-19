package Pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import Utility.DBConnection;
import Utility.readConfig;

public class custinfoPage {
	
	WebDriver driver;
	Properties prop;
	public Logger logger;
	
	String Cust_Stat;
	String Cust_Name;
	String Shipper_Address1;
	String Shipper_Address2;
	String Shipper_Address3;
	String Load_Date;
	String Station;
	String Lang;
	String Bill_Acct;
	String EDI_Acct;
	String BILL_Name;
	String BILL_Addr1;
	String BILL_Addr2;
	String BILL_Addr3;
	String Ship_Phone;
	String Credit_Rating;
	String Credit_Limit;
	
	String Currency;
	String Last_Pay;
	String CC_TYPE;
	String INTL_Mode;
	String Cash_Reason;
	String Cash_DT;
	String Intl_Cycle;
	String DT_Cycle;
	String EBPP;
	
	String FLT_nbr;
	String Ground;
	String BRK_INFO;
	String CUST_REF;
	String PWS_AUTO;
	String FIRST_SHIP;
	String LAST_SHIP;
	public String NATL_ACCT;
	String GEO_AGGR;
	String SS_AGGR;
	String BILLING_NOTES;
	String CORRES;
	String FWDBRKR;
	
	
	public custinfoPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void get_custinfo() throws Exception
	{
		
		logger=Logger.getLogger("custInfoPage");
		
		logger.info("-----------Inside the cust info page-------------");
		
		Thread.sleep(5000);
			
		By custstats = By.cssSelector("input[id='myForm:custsum:custStatusVal']");
		String count=driver.findElement(custstats).getAttribute("value");
		String[] mycounts=count.split("-");
		System.out.println("The length of array is ---------------------"+mycounts.length);
		if(mycounts.length>2)
		{
			Cust_Stat=mycounts[1]+"-"+mycounts[2].charAt(0);		
			logger.info("Customer Status is "+Cust_Stat);
		}
		else
		{
			if(mycounts.length==2 && mycounts[0]!="")
			{
			logger.info("inside else block for customer status for length 1");
			Cust_Stat=mycounts[1];		
			logger.info("Customer Status is "+Cust_Stat);
			}
			else
			{
				logger.info("inside else block for customer status for length 0");
				Cust_Stat="";		
				logger.info("Customer Status is "+Cust_Stat);
			}
		}
		
		
		By custname = By.name("myForm:custsum:custNameVal");
		Cust_Name=driver.findElement(custname).getAttribute("value");
		logger.info("Customer Name is "+Cust_Name.trim());
		
		By shipperad1 = By.cssSelector("label[id='myForm:custsum:shipperAddrl2']");
		Shipper_Address1	=driver.findElement(shipperad1).getText();
		logger.info("Shipper Address Line 1 "+Shipper_Address1);
		
		By shipperad2 =	By.cssSelector("label[id='myForm:custsum:shipperAddrl1']");	
		Shipper_Address2 =driver.findElement(shipperad2).getText();
		logger.info("Shipper Address Line 2 "+Shipper_Address2);
		
		By shipperad3 =	By.cssSelector("label[id='myForm:custsum:shipperaddrl3']");	
		Shipper_Address3 =driver.findElement(shipperad3).getText();
		logger.info("Shipper Address Line 3 "+Shipper_Address3);
		
		
		By loaddate=By.cssSelector("input[id='myForm:custsum:creatnDateVal']");
		Load_Date=driver.findElement(loaddate).getAttribute("value");
		logger.info("Load Date is "+Load_Date);
		
		By station=By.cssSelector("input[id='myForm:custsum:stationIdVal']");
		Station=driver.findElement(station).getAttribute("value");
		logger.info("Station is "+Station);
		
		By lang=By.cssSelector("input[id='myForm:custsum:langCodVal']");
		Lang=driver.findElement(lang).getAttribute("value");
		logger.info("Language is "+Lang);
		
					
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		By billacct=By.cssSelector("input[id='myForm:custsum:billToAccInfo']");
		Bill_Acct=driver.findElement(billacct).getAttribute("value");
		logger.info("Bill to Account Number "+Bill_Acct);
		
		By ediacct=By.cssSelector("input[id='myForm:custsum:ediAccNoInfo']");
	    EDI_Acct=driver.findElement(ediacct).getAttribute("value");
	    logger.info("EDI Account Number "+EDI_Acct);
		
		By billname=By.cssSelector("label[id='myForm:custsum:addrBl1']");
		BILL_Name=driver.findElement(billname).getText();
		logger.info("Bill Name is "+BILL_Name);
		
		By BILLAddr1=By.id("myForm:custsum:addrBl5");
		BILL_Addr1=driver.findElement(BILLAddr1).getText();
		logger.info("Bill Address 1 is "+BILL_Addr1);
		
		By BILLAddr2=By.id("myForm:custsum:addrBl2");
		BILL_Addr2=driver.findElement(BILLAddr2).getText();
		logger.info("Bill Address 2 is "+BILL_Addr2);
		
		By BILLAddr3=By.id("myForm:custsum:addrBl3");
		BILL_Addr3=driver.findElement(BILLAddr3).getText();
		logger.info("Bill Address 3 is "+BILL_Addr3);
		
		By SHIPPHONE=By.id("myForm:custsum:phoneNum");
		Ship_Phone=driver.findElement(SHIPPHONE).getText();
		logger.info("Shipper Phone is "+SHIPPHONE);
			
		By Credit_rate=By.id("myForm:custsum:credtRatngVal");
		Credit_Rating=driver.findElement(Credit_rate).getAttribute("value");
		logger.info("Credit Rating is "+Credit_Rating);
		
		By Credit_lim=By.id("myForm:custsum:credtLimitVal");
		Credit_Limit=driver.findElement(Credit_lim).getAttribute("value");
		logger.info("Credit Limit is "+Credit_Limit);
		
		/*By curr=By.id("myForm:custsum:currencyVal");
		Currency=driver.findElement(curr).getAttribute("value");
		logger.info("Currency is "+Currency);*/
		
		
		By curr=By.id("myForm:custsum:currencyVal");
		//String count1=driver.findElement(curr).getAttribute("value");
		Currency=driver.findElement(curr).getAttribute("value");
		if(Currency!="")
		{
			logger.info("Currency issssss  "+Currency);
		String[] mycounts1=Currency.split("-");
		System.out.println("The length of array is ---------------------"+mycounts1.length);
		Currency=mycounts1[1];
		}
        logger.info("Currency is "+Currency);
				
		By lastpay=By.id("myForm:custsum:LastPayVal");
		Last_Pay=driver.findElement(lastpay).getAttribute("value");
		logger.info("Last Pay is "+Last_Pay);
		
		By cc=By.id("myForm:custsum:credtCrdTypVal");
		CC_TYPE=driver.findElement(cc).getAttribute("value");
		logger.info("Credit Card is "+CC_TYPE);	
		
		By intl_mode=By.id("myForm:custsum:intlBilngModVal");
		INTL_Mode=driver.findElement(intl_mode).getAttribute("value");
		logger.info("Intl Mode is "+INTL_Mode);
		
		By cashreason=By.id("myForm:custsum:cashResonVal");
		String rsn=driver.findElement(cashreason).getAttribute("value");
		if(rsn!=null && !"".equalsIgnoreCase(rsn.trim()))
		{	
		String[] myresaons=rsn.split("-");
		Cash_Reason=myresaons[1];
		}
		else
		{
			Cash_Reason="";
		}
		logger.info("Cash Reason "+Cash_Reason);
		
		By cashdt=By.id("myForm:custsum:cashDateVal");
		Cash_DT=driver.findElement(cashdt).getAttribute("value");
		logger.info("Cash Date is "+Cash_DT);
		
		By dtcycle=By.id("myForm:custsum:dutyTaxBilngCycleVal");
		DT_Cycle=driver.findElement(dtcycle).getAttribute("value");
		logger.info("Duty cycle is "+DT_Cycle);
		
		By ebpp=By.id("myForm:custsum:ebppAcntVal");
		EBPP=driver.findElement(ebpp).getAttribute("value");
		logger.info("EBPP is "+EBPP);
				
		By intlcyc=By.id("myForm:custsum:intiBilngCycleVal");
		Intl_Cycle=driver.findElement(intlcyc).getAttribute("value");
		logger.info("Intl Cycle is "+Intl_Cycle);
		
		String s=Intl_Cycle;
		StringBuffer r=new StringBuffer();
		for(int k=0;k<s.length();k++)
		{
			if(Character.isLetter(s.charAt(k)))
				r.append(s.charAt(k));
		}
		
		Intl_Cycle=r.toString();
		
		logger.info("Intl Cycle is "+Intl_Cycle);
		
		By flt=By.id("myForm:custsum:flyngTigerNumVal");
		FLT_nbr=driver.findElement(flt).getAttribute("value");
		logger.info("The flt nbr is "+FLT_nbr);
	
		//------------
		By grd=By.id("myForm:custsum:grndNumVal");
		Ground=driver.findElement(grd).getAttribute("value");
		logger.info("The ground is "+Ground);
		
		By brk=By.id("myForm:custsum:brokerCodeVal");
		BRK_INFO=driver.findElement(brk).getAttribute("value");
		logger.info("The break info is "+BRK_INFO);
		
		By cust_ref=By.id("myForm:custsum:custRefVal");
		CUST_REF=driver.findElement(cust_ref).getAttribute("value");
		logger.info("The cust ref is "+CUST_REF);
				
		By pws=By.id("myForm:custsum:autmtnDevicsTypsVal");
		PWS_AUTO=driver.findElement(pws).getAttribute("value");
		logger.info("The pws auto is "+PWS_AUTO);
		
		By first_ship=By.id("myForm:custsum:firstShpDateVal");
		FIRST_SHIP=driver.findElement(first_ship).getAttribute("value");
		logger.info("The first ship is "+FIRST_SHIP);
		
		By last_ship=By.id("myForm:custsum:lastShpDateVal");
		LAST_SHIP=driver.findElement(last_ship).getAttribute("value");
		logger.info("The last ship is "+LAST_SHIP);
		
		
		By nat_acct=By.id("myForm:custsum:natnlAccVal");
		NATL_ACCT=driver.findElement(nat_acct).getAttribute("value");
		logger.info("The nat account is "+NATL_ACCT);
		
		By geo=By.id("myForm:custsum:geoAggregtnVal");
		GEO_AGGR=driver.findElement(geo).getAttribute("value");
		logger.info("The geo is "+GEO_AGGR);
		
		By ss=By.id("myForm:custsum:ssAggrgtnVal");
		SS_AGGR=driver.findElement(ss).getAttribute("value");
		logger.info("The ss is "+SS_AGGR);
		
		By billingnotes=By.cssSelector("textarea[id='myForm:custsum:comentToBilngNots_input']");
		BILLING_NOTES=driver.findElement(billingnotes).getAttribute("value");
		logger.info("Billing Notes is "+BILLING_NOTES);
		
		
		By corres=By.cssSelector("input[id='myForm:custsum:corespndcVal']");
		CORRES=driver.findElement(corres).getAttribute("value");
		logger.info("Correspondance is "+CORRES);
		
		By fwdbrk=By.cssSelector("input[id='myForm:custsum:fwdBrokerVal']");
		FWDBRKR=driver.findElement(fwdbrk).getAttribute("value");
		logger.info("Forward Broker is "+FWDBRKR);
		

	}

	
	public String insert_data(String test_id, String db_name, String acct_nbr, Connection con, String level) throws Exception
	{
		readConfig rc = new readConfig();
		prop=rc.readC();
		
		/*Connection con = DriverManager.getConnection(prop.getProperty("RSC_CR3_DB"),prop.getProperty("RSC_CR3_UID"),prop.getProperty("RSC_CR3_PWD"));
		
		Statement smt = con.createStatement();*/
		
		
		
		/*DBConnection dbc=new DBConnection();
		Connection con=dbc.getDBConnection();*/

		
		/*String query="insert into E_L3_CHEERS_CUSTINFO_TEST (TEST_ID, DBNAME, ACCTNBR, STATUS, SHIPNAME, SHIPADR1, SHIPADR2,  STATION,BILLTO, EDI_NBR, BILLNAME, BILLADR1, BILLADR2, BILLADR3, LOADDATE,LANG, SHIPHONE, CREDITRATING, CREDITLIMIT, CURRENCY, LASTPAY, CCTYPE, INTLMODE, CASHRSN, CASHDT, DTCYCLE, EBPP, INTLCYCLE, "
				+ "FTL_NBR,Ground, BRKINFO, CUSTREF, PWSAUTO,FIRSTSHP,LASTSHIP,NATLACCT,GEOAGGR,SSAGGR,SHIPADR3,CUSTOMERBILLINGCYCLE,CORRESP,FWDBRKR)"
		        + " values ('"+test_id+"', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);*/
		
		String query="insert into E_L"+level+"_CHEERS_CUSTINFO (TEST_ID, DBNAME, ACCTNBR, STATUS, SHIPNAME, SHIPADR1, SHIPADR2,  STATION,BILLTO, EDI_NBR, BILLNAME, BILLADR1, BILLADR2, BILLADR3, LOADDATE,LANG, SHIPHONE, CREDITRATING, CREDITLIMIT, CURRENCY, LASTPAY, CCTYPE, INTLMODE, CASHRSN, CASHDT, DTCYCLE, EBPP, INTLCYCLE, "
				+ "FTL_NBR,Ground, BRKINFO, CUSTREF, PWSAUTO,FIRSTSHP,LASTSHIP,NATLACCT,GEOAGGR,SSAGGR,SHIPADR3,CUSTOMERBILLINGCYCLE,CORRESP,FWDBRKR)"
		        + " values ('"+test_id+"', '"+db_name+"', '"+acct_nbr+"', '"+Cust_Stat+"', '"+Cust_Name+"', '"+Shipper_Address1+"', '"+Shipper_Address2+"', '"+Station+"', '"+Bill_Acct+"', '"+EDI_Acct+"', '"+BILL_Name+"', '"+BILL_Addr1+"', '"+BILL_Addr2+"', '"+BILL_Addr3+"', '"+Load_Date+"', '"+Lang+"', '"+Ship_Phone+"','"+Credit_Rating+"','"+Credit_Limit+"','"+Currency+"','"+Last_Pay+"','"+CC_TYPE+"','"+INTL_Mode+"','"+Cash_Reason+"','"+Cash_DT+"','"+DT_Cycle+"','"+EBPP+"','"+Intl_Cycle+"','"+FLT_nbr+"','"+Ground+"','"+BRK_INFO+"','"+CUST_REF+"','"+PWS_AUTO+"','"+FIRST_SHIP+"','"+LAST_SHIP+"','"+NATL_ACCT+"','"+GEO_AGGR+"','"+SS_AGGR+"','"+Shipper_Address3+"','"+BILLING_NOTES+"','"+CORRES+"','"+FWDBRKR+"')";
		
		return query; 
		
		/*PreparedStatement ps=con.prepareStatement(query);
		
		ps.setString(1, test_id);
		ps.setString(2, db_name);
		ps.setString(3, acct_nbr);
		ps.setString(4, Cust_Stat);
		ps.setString(5, Cust_Name);
		ps.setString(6, Shipper_Address1);
		ps.setString(7, Shipper_Address2);		
		ps.setString(8, Station);		
		ps.setString(9, Bill_Acct);
		ps.setString(10, EDI_Acct);
		ps.setString(11, BILL_Name);
		ps.setString(12, BILL_Addr1);
		ps.setString(13, BILL_Addr2);
		ps.setString(14, BILL_Addr3);
		ps.setString(15, Load_Date);
		ps.setString(16, Lang);
		ps.setString(17, Ship_Phone);
		ps.setString(18, Credit_Rating);
		ps.setString(19, Credit_Limit);		
		ps.setString(20, Currency);
		ps.setString(21, Last_Pay);
		ps.setString(22, CC_TYPE);
		ps.setString(23, INTL_Mode);
		ps.setString(24, Cash_Reason);
		ps.setString(25, Cash_DT);
		ps.setString(26, DT_Cycle);
		ps.setString(27, EBPP);
		ps.setString(28,Intl_Cycle);
		
		ps.setString(29, FLT_nbr);
		ps.setString(30, Ground);		
		ps.setString(31, BRK_INFO);
		ps.setString(32, CUST_REF);
		ps.setString(33, PWS_AUTO);
		ps.setString(34, FIRST_SHIP);
		ps.setString(35, LAST_SHIP);
		ps.setString(36, NATL_ACCT);
		ps.setString(37, GEO_AGGR);
		ps.setString(38, SS_AGGR);
		ps.setString(39, Shipper_Address3);
		ps.setString(40, BILLING_NOTES);
		ps.setString(41, CORRES);
		ps.setString(42, FWDBRKR);
		*/
		
		
	/*	//ps.execute();
		logger.info("Adding query");
		ps.addBatch(query);
		logger.info("Query added");
		//con.close();
		
		logger.info("the query for CUSTINFO IS "+query);
		logger.info("data inserted in CUSTINFO table");*/
		
	//	return ps;
		
	}
}
