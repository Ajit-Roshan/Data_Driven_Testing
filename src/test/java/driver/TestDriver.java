package driver;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.Common;

public class TestDriver extends Common{

	int rowCount ;
	TestDriver td;


	@Test(priority = 1)
	public void test() throws Throwable {

		td= new TestDriver();
		rowCount= td.rowCount();		
		Reporter.log(rowCount+" ",true);
	}

	@Test(priority = 2)
	public void	getData() throws Throwable {

		
		for(int i= 1 ; i< rowCount; i++) {
			String id= td.getId(i);
			String pass= td.getPass(i);

			WebElement uName= dr.findElement(By.id("username"));
			WebElement uPass=  dr.findElement(By.id ("password"));
			WebElement signIn=  dr.findElement(By.id ("submit"));

			
			uName.clear();
			uPass.clear();


			uName.sendKeys(id);
			uPass.sendKeys(pass);
			signIn.click();
			
			dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

			String currURL= dr.getCurrentUrl();
			currURL= currURL.toLowerCase();

			//			assertEquals( "successfully" , currURL.contains("successfully") );

			//			Reporter.log(id +" "+pass ,true);
			//			
			if(currURL.contains("successfully")) {
				td.setData(i, "pass");
				dr.findElement(By.xpath("//a[@href='https://practicetestautomation.com/practice-test-login/']")).click();

				dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			}
			else {
				td.setData(i, "fail");		
			}

		}	
	}

}










