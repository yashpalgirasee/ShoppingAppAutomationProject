package mypackage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryfield;
	
	@FindBy(xpath="//section/button[2]")
	WebElement country;
	
	@FindBy(xpath="//div[@class='actions'] /a")
	WebElement placeorder;
	
	
	public void enterAndSelectCountry(String countryname){
		
		countryfield.sendKeys(countryname);
		country.click();
	}
	
	public ReceiptPage clickOnPlaceOrder(){
		
		placeorder.click();
		ReceiptPage receiptpage = new ReceiptPage(driver);
		return receiptpage;
	}
	
	
	
}
