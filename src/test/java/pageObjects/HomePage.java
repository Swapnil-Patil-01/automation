package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy (xpath="//span[normalize-space()='My Account']") WebElement clkMyAcc;
	@FindBy (xpath="//a[normalize-space()='Register']") WebElement clkReg;
	@FindBy (xpath="//a[@class='dropdown-item'][normalize-space()='Login']") WebElement clkLogin;
	
	public void clickMyAccount() {
		clkMyAcc.click();
	}
	
	public void clickRegister() {
		clkReg.click();
	}
	
	public void clickLogin() {
		clkLogin.click();
	}
}
