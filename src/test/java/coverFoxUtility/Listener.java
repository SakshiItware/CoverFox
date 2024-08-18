package coverFoxUtility;

import java.io.IOException;
import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import coverFoxBase.Base;

public class Listener extends Base implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result)
	
	{
		Reporter.log(result.getTestName()+"TC execution started",true);
	}

	
	@Override
	public void onTestFailure(ITestResult result)  {
		
		Reporter.log("TC"+ result.getName()+" is failed",true);
		try {
			Utility.takeScreenShot(driver, result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
