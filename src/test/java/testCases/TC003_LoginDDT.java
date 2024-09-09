package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid 	- login success - test pass - logout
 * 				 	- login fail    - test fail 
 * 
 * Data is invalid  - login success - test fail - logout
 * 					- login fail	- test pass
 * 
 */

public class TC003_LoginDDT extends BaseClass{

	@Test (dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= {"dataDriven","master"})
	public void verify_LoginDDT(String email, String pass, String exp){
		
		logger.info("*****verify_LoginDDT*****");
		
		try {
		//HomePage
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
				
		//LoginPage
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pass);
		lp.clickLoginButton();
				
		//MyAccountPage
		MyAccountPage map= new MyAccountPage(driver);
		boolean status =map.isMyAccountPageExist();
		
		if(exp.equalsIgnoreCase("valid")) {
			if(status==true) {
				map.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid")) {
			if(status==true) {
				map.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("*****Finished verify_LoginDDT*****");
		
	}
}
