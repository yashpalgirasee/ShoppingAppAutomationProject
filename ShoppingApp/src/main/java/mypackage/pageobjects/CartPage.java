package mypackage.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartWrap")
	List<WebElement> cartproducts;
	
	By checkout = By.xpath("//div[@class='infoWrap'] /div[3]/button[1]");
	
	public WebElement checkAddedProduct(String productname) {
		
		WebElement addedprod = cartproducts.stream().filter(product->product.findElement(By.cssSelector("h3")).getText().equals(productname)).findFirst().orElse(null);
		System.out.println(addedprod);
		return addedprod;
	}
	
	public CheckoutPage clickOnCheckout(String productname){
		
		checkAddedProduct(productname).findElement(checkout).click();
		CheckoutPage checkout = new CheckoutPage(driver);
		return checkout;
	}
}
