package driver;

import org.testng.Reporter;
import org.testng.annotations.Test;

import utils.Utils;

public class TestDriver extends Utils{

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
			
			Reporter.log(id +" "+pass ,true);
			
			if(id.contains( "admin4") && pass.contains( "pass4") ) {
				td.setData(i, "pass");
			}
			else if(id.contains( "admin7") && pass.contains( "pass7") ) {
				td.setData(i, "blocked");
			}
			else 
				td.setData(i, "fail");			
		}	
	}
	
}










