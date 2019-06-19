package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnection {

	public Connection getDBConnection() throws Exception
	{
		Properties prop;
		Logger logger = null;
		
		readConfig rc=new readConfig();
		prop=rc.readC();
		
		logger = Logger.getLogger("getDBConnection");
		
		Connection con = DriverManager.getConnection(prop.getProperty("RSC_CR3_DB"), prop.getProperty("RSC_CR3_UID"),
				prop.getProperty("RSC_CR3_PWD"));
		logger.info("Connection is created");

		Statement smt = con.createStatement();
		logger.info("Statement created");
		
		return con;
		 
	}
	
}
