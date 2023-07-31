package mytestpackage.testcomponent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import mypackage.pageobjects.LoginPage;

public class BaseTest{
	
	public WebDriver driver;
	public LoginPage loginobject;
	
	public WebDriver initializeBrowser() throws IOException {
	
	Properties prop = new Properties();
	FileInputStream file = new FileInputStream("C:\\Users\\Xunison\\git\\MyShoppingCartFramework\\ShoppingApp\\src\\main\\java\\mypackage\\resources\\GlobalDate.properties");
	prop.load(file);
	
	String browsername = prop.getProperty("browser");
	
		if(browsername.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		
		}
		else if(browsername.equalsIgnoreCase("firefox")) {
		
			// setup firefox broswer here
		
		}
		else if(browsername.equalsIgnoreCase("edge")) {
		
			// setup edge browser here
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		return driver;
	
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchBrowser() throws IOException {
		
		initializeBrowser();
		loginobject = new LoginPage(driver);
		loginobject.goTo();
		return loginobject;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser(){
		
		driver.close();
	}
}
