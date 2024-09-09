package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest  extends BaseClass{
	
	@Test (groups= {"sanity","master"})
	public void verifyAccountRegistration() throws InterruptedException  {
		
		logger.info("*****TC001_AccountRegistrationTest*****");
		
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("Click on MyAccount and Register Page");
		
		AccountRegistrationPage regPage= new AccountRegistrationPage(driver);
		
		logger.info("Providing user details...");
		
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		regPage.setPassword(randomAlphaNumeric());
		regPage.setPrivacy();
		Thread.sleep(2000);
		regPage.clickContinue();
		logger.info("Click on continue button...");
		
		logger.info("Validating expected message...");
		String cnfMsg= regPage.getConfirmMesage();
		
		if(cnfMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("*****Finished TC001_AccountRegistrationTest*****");
	}
	
}
