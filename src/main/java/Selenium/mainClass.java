package Selenium; 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Pages.accountNumberPage;
import Pages.billcustPage;
import Pages.custinfoPage;
import Pages.custservPage;
import Pages.custserv_widoutservice;
import Pages.loginPage;
import Pages.shipprofPage;
import Utility.DBConnection;
import Utility.readConfig;

public class mainClass extends baseClass {

	WebDriver driver;
	Properties prop;
	public Logger logger;
	String query,query1,query2,query3,query4,query5,query6,query7,query8;
	Statement ps,ps1,ps2,ps3,ps4;
	String acct_nbr;
	String flag ="Y";

	@Test
	public void baseNavigation() throws Exception {

		// logger
		logger = Logger.getLogger("mainclass");
		PropertyConfigurator.configure("Log4j.properties");
		

		readConfig rc = new readConfig();
		prop = rc.readC();

		
	
		DBConnection dbc=new DBConnection();
		Connection con=dbc.getDBConnection();
		
		Statement smt = con.createStatement();
		Statement smt1= con.createStatement();
		Statement smt2= con.createStatement();
		logger.info("Statement created");
		
		ps  = con.createStatement();
		ps1 = con.createStatement();
		ps2 = con.createStatement();
		ps3 = con.createStatement();
		ps4 = con.createStatement();
		
		String level=prop.getProperty("level");
		
		ResultSet rs1 = smt.executeQuery("truncate table E_L"+level+"_CHEERS_CUSTINFO");
		ResultSet rs2 = smt.executeQuery("truncate table E_L"+level+"_CHEERS_SHIPPROF");
		ResultSet rs3 = smt.executeQuery("truncate table E_L"+level+"_CHEERS_CUSTSERV");
		ResultSet rs4 = smt.executeQuery("truncate table E_L"+level+"_CHEERS_BILLCUST");
		
		
		ResultSet rs = smt.executeQuery("select * from CUSTNUM");
		//ResultSet rs = smt.executeQuery("Select * from CUSTNUM where ACCT_NBR NOT IN (SELECT ACCTNBR FROM E_L3_CHEERS_SHIPPROF)  and ACCT_NBR not in ('160937467','205270485','294162100','181375051','275393720','272133298','311770209','186178122') ");
		//ResultSet rs = smt.executeQuery("Select * from CUSTNUM where ACCT_NBR IN('764954920') ");
		
		
		while (rs.next()) {
			
		
			driver = intializeDriver();
			
			if(prop.getProperty("Level").equalsIgnoreCase("2"))
			{
			    driver.get(prop.getProperty("L2_URL"));
			    System.out.println("The URL got is ----------- "+prop.getProperty("L2_URL"));
			}
			else
			{
				driver.get(prop.getProperty("L3_URL"));
				System.out.println("The URL got is ----------- "+prop.getProperty("L3_URL"));
			}
			
		
		
			driver.manage().window().maximize();
			
			// login
			logger.info("Enetring the login details");
			loginPage lp = new loginPage(driver);
			lp.enterData(prop.getProperty("userid"), prop.getProperty("password"));

			// enter account number
			logger.info("Entering the account number");
			accountNumberPage an = new accountNumberPage(driver);	

			String test_id = rs.getString("test_id");
			String db_name = rs.getString("dbname");
			acct_nbr = rs.getString("acct_nbr");
			//acct_nbr="113665734";

			logger.info("Running for Account Number -------------------------------- " + acct_nbr);
			logger.info("The test id is  " + test_id);
			logger.info("The db_name is " + db_name);

			an.enter_account_number(acct_nbr);
			
			//Adding now
			//By err_msg=By.id("myForm:ErrMsgText"); 
			//String msg=driver.findElement(err_msg).getText();
			
			Thread.sleep(8000);
		
			try {
			
			if(driver.findElement(By.id("myForm:viewButton")).isDisplayed()==true)	
			{
			//Thread.sleep(10000);
			Thread.sleep(10000);

			
			// get cust info E_L3_CHEERS_CUSTINFO_TEST
			custinfoPage cp = new custinfoPage(driver);
			try {
				
			 cp.get_custinfo();
			 query=cp.insert_data(test_id, db_name, acct_nbr,con,level);
			 logger.info("The query for CUSTINFO is "+query);
			// ps=con.prepareStatement(query);
			 ps.addBatch(query);
			
			}		catch(Exception e)
			{
				    logger.info("The CUSTINFO code failed for account number "+acct_nbr+" with exception "+e);
			}

			// get shipprof info E_L3_CHEERS_SHIPPROF_TEST
			try {
			shipprofPage sp = new shipprofPage(driver);
			sp.get_shipinfo(flag);
			query1=sp.insert_data(test_id, db_name, acct_nbr,con,level);
			logger.info("The query for SHIPPROF is "+query1);
			//ps1=con.prepareStatement(query1);
			ps1.addBatch(query1);
			}	catch(Exception e)
			{
				logger.info("The SHIPPROF code failed for account number "+acct_nbr+" with exception "+e);
				
			}

			// get custserv info E_L3_CHEERS_CUSTSERV_TEST
			try {
			
			/*custservPage cr = new custservPage(driver);
			cr.get_custserv();*/
				
				custserv_widoutservice cr = new custserv_widoutservice(driver);
				cr.get_custserv();
			//query2=cr.insert_data(test_id, db_name, acct_nbr,con,level);
			query2=cr.insert_data(test_id, db_name, acct_nbr,con,level,cp.NATL_ACCT);
			logger.info("The query for CUSTSERV is "+query2);
			ps2.addBatch(query2);
			
			}	catch(Exception e)
			{
				logger.info("The CUSTSERV code failed for account number "+acct_nbr+" with exception "+e);
			}
			
			
			
			// get billcust info E_L3_CHEERS_BILLCUST_TEST
			try {
			billcustPage bc = new billcustPage(driver);
			bc.get_billcustinfo();
			
			query3=bc.insert_data(test_id, db_name, acct_nbr,con,level);
			logger.info("The query for BILLCUST is "+query3);
			ps3.addBatch(query3);
			
			query8=bc.update_data(test_id, db_name, acct_nbr, con, level, smt2);
			logger.info("The update query for SHIPPROF is  "+query8);
			ps4.addBatch(query8);
			
			}catch(Exception e)
			{
				logger.info("The BILLCUST code failed for account number "+acct_nbr+" with exception "+e);
			}

			driver.findElement(By.id("myForm:qckaccno_input")).clear();
			

		}
			
			}
			
			catch(Exception e)
			{
				
				By err_msg=By.id("myForm:ErrMsgText"); 
				String msg=driver.findElement(err_msg).getText();
				
				if(msg.contains("No Results Found For") )
				{
				String err_msg1="Customer not Found";
				logger.info("Customer not Found "+acct_nbr);
				
				query4="insert into E_L"+level+"_CHEERS_CUSTINFO (TEST_ID, DBNAME, ACCTNBR, ERR_MSG ) values ('"+test_id+"', '"+db_name+"', '"+acct_nbr+"', '"+err_msg1+"')";
				ps.addBatch(query4);
				
				query5="insert into E_L"+level+"_CHEERS_SHIPPROF (TEST_ID, DBNAME, ACCTNBR, PROGMSG ) values ('"+test_id+"', '"+db_name+"', '"+acct_nbr+"', '"+err_msg1+"')";
				ps1.addBatch(query5);
				
				query6="insert into E_L"+level+"_CHEERS_CUSTSERV ( ACCTNBR, ERROR ) values ('"+acct_nbr+"', '"+err_msg1+"')";
				ps2.addBatch(query6);
				
				query7="insert into E_L"+level+"_CHEERS_BILLCUST (TEST_ID, DBNAME, ACCTNBR, ERR_MSG ) values ('"+test_id+"', '"+db_name+"', '"+acct_nbr+"', '"+err_msg1+"')";
				ps3.addBatch(query7);
				
				driver.findElement(By.id("myForm:qckaccno_input")).clear();
				}
				else
				{
					logger.info("Nothing has happened in exception");
				}
				
				if(msg.contains("Only accounts"))
				{
				String err_msg1="A system error has occurred. Please contact eCIS tech support";
				logger.info("Customer not Found "+acct_nbr);
				
				query4="insert into E_L"+level+"_CHEERS_CUSTINFO (TEST_ID, DBNAME, ACCTNBR, ERR_MSG ) values ('"+test_id+"', '"+db_name+"', '"+acct_nbr+"', '"+err_msg1+"')";
				ps.addBatch(query4);
				
				query5="insert into E_L"+level+"_CHEERS_SHIPPROF (TEST_ID, DBNAME, ACCTNBR, PROGMSG ) values ('"+test_id+"', '"+db_name+"', '"+acct_nbr+"', '"+err_msg1+"')";
				ps1.addBatch(query5);
				
				query6="insert into E_L"+level+"_CHEERS_CUSTSERV ( ACCTNBR, ERROR ) values ('"+acct_nbr+"', '"+err_msg1+"')";
				ps2.addBatch(query6);
				
				query7="insert into E_L"+level+"_CHEERS_BILLCUST (TEST_ID, DBNAME, ACCTNBR, ERR_MSG ) values ('"+test_id+"', '"+db_name+"', '"+acct_nbr+"', '"+err_msg1+"')";
				ps3.addBatch(query7);
				
				driver.findElement(By.id("myForm:qckaccno_input")).clear();
				}
				else
				{
					logger.info("Nothing has happened in exception");
				} 
			}
			
			logger.info("Finall insert statement");
			
			try {
			ps.executeBatch();
			}catch(Exception e)
			{
				logger.info("The insert query failed with "+e +"  for account number "+acct_nbr);
			}
			
			try {
			ps1.executeBatch();
			}catch (Exception e) {
				logger.info("The insert query failed with "+e +"  for account number "+acct_nbr);
			}
			try{
				ps2.executeBatch();
			}catch (Exception e) {
				logger.info("The insert query failed with "+e +"  for account number "+acct_nbr);
			}
			try{
				ps3.executeBatch();
			}catch (Exception e) {
				logger.info("The insert query failed with "+e +"  for account number "+acct_nbr);
			}
			try{
				ps4.executeBatch();
			}catch (Exception e) {
				logger.info("The upadate query failed with "+e +"  for account number "+acct_nbr);
				//smt1.executeQuery("delete from E_L"+level+"_CHEERS_SHIPPROF");
				
			}
				
			
			
			driver.close();
					
			
			
}
		
		con.close();	
			
	}

}
