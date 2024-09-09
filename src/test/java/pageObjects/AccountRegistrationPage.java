package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	JavascriptExecutor js =(JavascriptExecutor)driver;
	
	public AccountRegistrationPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy (xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy (xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy (xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy (xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy (xpath="//input[@id='input-newsletter']") WebElement checkNewsletter;
	@FindBy (xpath="//input[@name='agree']") WebElement checkPrivacy;
	@FindBy (xpath="//button[normalize-space()='Continue']") WebElement btnContinue;
	@FindBy (xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement confirmMsg;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void setNewsLetter() {
		checkNewsletter.click();
	}
	
	public void setPrivacy() {
		js.executeScript("arguments[0].click()",checkPrivacy);
	}
	
	public void clickContinue() {
		js.executeScript("arguments[0].click()",btnContinue);
	}
	
	public String getConfirmMesage() {
		try {
			return (confirmMsg.getText());
		}
		catch(Exception e) {
			return (e.getMessage());
		}
	}
	
}
