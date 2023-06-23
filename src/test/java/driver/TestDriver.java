package driver;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import common.Common;

public class TestDriver extends Common{

	int rowCount ;
	TestDriver td;

	ExtentReports er;
	ExtentTest et;
	ExtentSparkReporter spark;

	@Test(priority = 1)
	public void test() throws Throwable {

		td= new TestDriver();
		rowCount= td.rowCount();		
		Reporter.log(rowCount+" ",true);
	}

	@Test(priority = 2)
	public void	getData() throws Throwable {

		er= new ExtentReports();
		spark= new ExtentSparkReporter("/home/ajit/eclipse_Space/Data_Driven/output/reports.html");
		er.attachReporter(spark);



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


			et= er
					.createTest("loginTest")
					.assignAuthor("Ajit")
					.assignCategory("functional");


			if(currURL.contains("successfully")) {
				td.setData(i, "pass");

				et.log(Status.PASS, "login pass");
				er.flush();

				dr.findElement(By.xpath("//a[@href='https://practicetestautomation.com/practice-test-login/']")).click();

				dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			}
			else {
				td.setData(i, "fail");	

				et.log(Status.FAIL, "fail cradentials");
				er.flush();
			}
		}	
		
	}
}










