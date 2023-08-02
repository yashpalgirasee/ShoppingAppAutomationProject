package mypackage.pageobjects;

import java.util.List;

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
	
	@FindBy(xpath="//div/section/button/span")
	List<WebElement> countries;
	
	@FindBy(xpath="//div[@class='actions'] /a")
	WebElement placeorder;
	
	
	public void enterAndSelectCountry(String countryname){
		
		countryfield.sendKeys(countryname);
		WebElement country = countries.stream().filter(allcountry->allcountry.getText().equalsIgnoreCase(countryname)).findAny().orElse(null);
		country.click();
		
	}
	
	public ReceiptPage clickOnPlaceOrder(){
		
		placeorder.click();
		ReceiptPage receiptpage = new ReceiptPage(driver);
		return receiptpage;
	}
	
	
	
}
