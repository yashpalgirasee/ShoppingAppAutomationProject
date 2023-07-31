package mypackage.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mypackage.abstractcomponent.AbstractComponent;


public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;
	public OrdersPage (WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (xpath="//tr/td[2]")
	List<WebElement> orderedproducts;
	
	public boolean verifyOrderProduct(String productname) {
		
		boolean findproduct = orderedproducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return findproduct;
		
	}

}
