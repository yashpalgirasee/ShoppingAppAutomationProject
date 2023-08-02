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

	//String productname = "IPHONE 13 PRO";
	//String countryname = "india";
	//String useremail = "salmankhan123@gmail.com";
	//String userpassword ="Salman@123";
	String ordersuccess = "THANKYOU FOR THE ORDER.";

	
	@Test(dataProvider="getLoginData")
	public void submitOrder(HashMap<String,String> input) throws IOException {
		
		ProductCatalogue prodcat = loginobject.LoginToApp(input.get("email"),input.get("password"));
		CartPage cartpage = prodcat.addToCart(input.get("productname"));
		
		CheckoutPage checkout = cartpage.clickOnCheckout(input.get("productname"));
		checkout.enterAndSelectCountry(input.get("country"));
		
		ReceiptPage receiptpage = checkout.clickOnPlaceOrder();
		Assert.assertEquals(ordersuccess,receiptpage.VerifyOrder());
	 
		}


	@Test (dependsOnMethods={"submitOrder"},dataProvider="getLoginData")
	public void verifyProduct(HashMap<String,String> input) throws IOException {

		ProductCatalogue prodcat = loginobject.LoginToApp(input.get("email"),input.get("password"));
		OrdersPage orderpage = prodcat.goToOrders();
		Assert.assertTrue(orderpage.verifyOrderProduct(input.get("productname")));
		
	}

	
	@DataProvider
	public Object[][] getLoginData() throws IOException {
		
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email","yashpal@demo.com");
		map.put("password","demo123");
		map.put("productname","IPHONE 13 PRO"); */
		List<HashMap<String,String>> data = getJasonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\mytestpackage\\data\\purchaseOrder.json");
		
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
}
