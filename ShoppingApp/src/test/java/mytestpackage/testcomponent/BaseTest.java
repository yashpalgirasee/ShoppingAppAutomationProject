package mytestpackage.testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import mypackage.pageobjects.LoginPage;

public class BaseTest{
	
	public WebDriver driver;
	public LoginPage loginobject;
	
	public WebDriver initializeBrowser() throws IOException {
	
	Properties prop = new Properties();
	FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\mypackage\\resources\\GlobalDate.properties");
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
	
	public String getScreenshot(String testCaseName , WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png" ;
		
		
	}
	
	public List<HashMap<String,String>> getJasonDataToMap(String filePath) throws IOException {
		
		// read jason to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		// string to Hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String , String>> data = mapper.readValue(jsonContent, new TypeReference <List<HashMap<String , String>>>(){});
		return data;
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
