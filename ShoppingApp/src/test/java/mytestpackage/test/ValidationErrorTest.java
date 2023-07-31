package mytestpackage.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import mytestpackage.testcomponent.BaseTest;

public class ValidationErrorTest extends BaseTest {

	@Test
	public void errorMessage() {
		
		loginobject.LoginToApp("salmankhan123@gmail.com","Demo@123456");
		Assert.assertEquals("Incorrect email or password.",loginobject.getErrorMessage());
	}
	
	@Test
	public void checkLogin() {
		
		loginobject.LoginToApp("salmankhan123@gmail.com","Demo@123456");
	}
	
}
