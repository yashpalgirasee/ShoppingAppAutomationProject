package mypackage.abstractcomponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mypackage.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent (WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//ul/li/button[@routerlink='/dashboard/cart']")
	WebElement mycart;
	
	@FindBy(xpath="//ul/li/button[@routerlink='/dashboard/myorders']")
	WebElement orders;
	
	@FindBy (css=".fa-sign-out")
	WebElement logout;
	
	public void goToCart() {
		
		mycart.click();
	}
	
	public void logOut() {
		
		logout.click();
	}
	
	public OrdersPage goToOrders() {
		
		orders.click();
		OrdersPage orderpage = new OrdersPage(driver);
		return orderpage;
	}
	
	
	public void waitForElementAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));	
	}
	
	public void waitForWebElementAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));	
	}
	
	
	public void waitForElementInvisible(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
}
