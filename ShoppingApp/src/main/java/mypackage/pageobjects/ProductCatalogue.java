package mypackage.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mypackage.abstractcomponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		}
	
	@FindBy(css =".mb-3")
	List <WebElement> allproducts;
	
	By productload = By.cssSelector(".mb-3");
	//By productname = By.cssSelector("h5");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastmsg = By.id("toast-container");
	
	public List<WebElement> getProducts() {
		waitForElementAppear(productload);
		return allproducts;
		}
	
	public WebElement getProductName(String productname) {
		
		WebElement prod = getProducts().stream().filter(product->
		product.findElement(By.cssSelector("h5")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
	}
	
	public CartPage addToCart(String productname) {
		
		WebElement product = getProductName(productname);
		product.findElement(addToCart).click();
		waitForElementInvisible(toastmsg);
		goToCart();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	 
}
