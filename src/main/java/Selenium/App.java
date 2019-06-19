package Selenium;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.io.IOException;
import java.util.List;
import org.testng.collections.Lists;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add("testng.xml");//path to xml..
		testng.setTestSuites(suites);
		testng.run();
    }
}
