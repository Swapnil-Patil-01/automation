package testCases;
	import org.testng.Assert;
	import org.testng.annotations.Test;
	import pageObjects.HomePage;
	import pageObjects.LoginPage;
	import pageObjects.MyAccountPage;
	import testBase.BaseClass;

	public class TC004_gitprac  extends BaseClass {

		@Test (groups= {"regression","master"})
		public void verifyLogin() {
			logger.info("*****TC002_LoginPage*****");
		
//			try {
			//HomePage
			HomePage hp= new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			
			//LoginPage
			LoginPage lp= new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
//			Thread.sleep(5000);
			lp.clickLoginButton();
			
			//MyAccountPage
			MyAccountPage map= new MyAccountPage(driver);
			boolean status =map.isMyAccountPageExist();
			Assert.assertTrue(status);
			
//			}
//			catch(Exception e) {
//				Assert.fail();
//			}
//			logger.info("*****Finished TC002_LoginPage*****");
		
	}


}
