package mypackage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mypackage.abstractcomponent.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	@FindBy(id="login")
	WebElement submitbutton;
	
	@FindBy(id="toast-container")
	WebElement errormessage;
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue LoginToApp(String userid , String userpass) {
		
		useremail.sendKeys(userid);
		userpassword.sendKeys(userpass);
		submitbutton.click();
		ProductCatalogue prodcat = new ProductCatalogue(driver);
		return prodcat;
		
	}
	
	public String getErrorMessage(){
		
		waitForWebElementAppear(errormessage);
		return errormessage.getText();
	}
	
}
