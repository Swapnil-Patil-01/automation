package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	JavascriptExecutor js =(JavascriptExecutor)driver;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (xpath="//h2[normalize-space()='My Account']") WebElement txtMyAcc;
	@FindBy (xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement clkLogout;
	
	public boolean isMyAccountPageExist() {
		try {
			return (txtMyAcc.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
//		clkLogout.click();
		js.executeScript("arguments[0].click()",clkLogout);
	}
}
