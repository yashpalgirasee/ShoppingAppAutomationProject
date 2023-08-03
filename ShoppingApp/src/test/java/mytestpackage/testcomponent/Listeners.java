package mytestpackage.testcomponent;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		//System.out.println("New Test Started" +result.getName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		//System.out.println("onTestSuccess Method" +result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println("onTestFailure Method" +result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//System.out.println("onTestSkipped Method" +result.getName());
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//System.out.println("onTestFailedButWithinSuccessPercentage" +result.getName());
	}
	
}
