package mytestpackage.test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import mypackage.abstractcomponent.AbstractComponent;
import mypackage.pageobjects.CartPage;
import mypackage.pageobjects.CheckoutPage;
import mypackage.pageobjects.LoginPage;
import mypackage.pageobjects.OrdersPage;
import mypackage.pageobjects.ProductCatalogue;
import mypackage.pageobjects.ReceiptPage;
import mytestpackage.testcomponent.BaseTest;

public class StandAlone extends BaseTest {

	String productname = "IPHONE 13 PRO";
	String countryname = "india";
	String useremail = "salmankhan123@gmail.com";
	String userpassword ="Salman@123";
	String ordersuccess = "THANKYOU FOR THE ORDER.";

	
	@Test
	public void submitOrder() throws IOException {
		
		ProductCatalogue prodcat = loginobject.LoginToApp(useremail,userpassword);
		CartPage cartpage = prodcat.addToCart(productname);
		
		CheckoutPage checkout = cartpage.clickOnCheckout(productname);
		checkout.enterAndSelectCountry(countryname);
		
		ReceiptPage receiptpage = checkout.clickOnPlaceOrder();
		Assert.assertEquals(ordersuccess,receiptpage.VerifyOrder());
	 
		}


	@Test (dependsOnMethods={"submitOrder"})
	public void verifyProduct() throws IOException {

		ProductCatalogue prodcat = loginobject.LoginToApp(useremail,userpassword);
		OrdersPage orderpage = prodcat.goToOrders();
		Assert.assertTrue(orderpage.verifyOrderProduct(productname));
		
	}

	/*
	@DataProvider
	public Object[][] getLoginData() {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email","yashpal@demo.com");
		map.put("password","demo123");
		map.put("productname","IPHONE 13 PRO");
		
		return  Object [] {{map}};
	} */
}
