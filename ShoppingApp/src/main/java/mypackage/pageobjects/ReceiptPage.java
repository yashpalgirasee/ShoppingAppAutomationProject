package mypackage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mypackage.abstractcomponent.AbstractComponent;

public class ReceiptPage extends AbstractComponent {

	WebDriver driver;
	
	public ReceiptPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement verifyname;
	
	@FindBy(id="toast-container")
	WebElement toastmsg;
	
	public String VerifyOrder(){
		
		waitForWebElementAppear(toastmsg);
		return verifyname.getText();	
	}
	
	public OrdersPage clickOnOrders() {
		
		goToOrders();
		OrdersPage orderpage = new OrdersPage(driver);
		return orderpage;

		
	}
	
}
