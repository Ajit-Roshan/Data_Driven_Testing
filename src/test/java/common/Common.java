package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import utils.Utils;

public class Common extends Utils {
	
	public static WebDriver dr;
	
	
	@BeforeTest
	@Parameters("url")
	public void setUp(String url) {
		dr= new FirefoxDriver();
		dr.manage().window().maximize();
		
		dr.get(url);
	}
	
	
	@AfterTest
	public void tearDown() {
		dr.close();
	}
	
}
