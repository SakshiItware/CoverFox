package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.poi.ss.formula.functions.Address;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;


import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDetailsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxResultPage;
import coverFoxUtility.Utility;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxPOM.CoverFoxHomePage;


@Listeners(coverFoxUtility.Listener.class)
public class CoverFoxListener extends Base 
{
CoverFoxHomePage  homePage;
CoverFoxHealthPlanPage  healthPlan;
CoverFoxAddressDetailsPage  addressPage;
CoverFoxMemberDetailsPage  memberePage;
CoverFoxResultPage resultPage;
String Filepath;
public static Logger logger;

@BeforeTest
public void launchBrowser() {

logger= Logger.getLogger("Coverfoxdata");
PropertyConfigurator.configure("Log4j.properties");
logger.info("openning browser");

	openBrowser();
	homePage = new CoverFoxHomePage(driver);
	healthPlan = new CoverFoxHealthPlanPage(driver);
	addressPage = new CoverFoxAddressDetailsPage(driver);
	memberePage = new CoverFoxMemberDetailsPage(driver);
	resultPage = new CoverFoxResultPage(driver);
	Filepath = "d:\\excel\\Stringdata.xlsx";
}


@BeforeClass
public void preconditions() throws InterruptedException,IOException 
{

    // Home-Page
		Thread.sleep(1000);
		homePage=new CoverFoxHomePage(driver);
		homePage.clickOnGenderButton();
		logger.info("Clicking on Gender button");

		// Health-Plan Page
		Thread.sleep(2000);
		healthPlan=new CoverFoxHealthPlanPage(driver);
		healthPlan.clickOnNextButton();
		logger.info("Clicking on Next button");

		// Member-details Page
		Thread.sleep(2000);
		memberePage =new CoverFoxMemberDetailsPage(driver);
      
      memberePage.handleAgeDropDown(Utility.readDataFromPropertiesFile("age"));
      
      logger.warn("enter age between 18-90 years");
      logger.info("Handling age drop down");

		memberePage.clickOnNextButton();
		logger.info("Clicking on Next button");
		Thread.sleep(2000);

		// Address-Details Page
		addressPage=new CoverFoxAddressDetailsPage(driver);
		addressPage.enterPincode(Utility.readDataFromPropertiesFile("pincode"));
		logger.warn("Please enter valide pin code");
		logger.info("entering pin code");
		
		addressPage.enterMobileNumber(Utility.readDataFromPropertiesFile("mobno"));
		
		logger.warn("Please enter valide mobile number");
		logger.info("entering mobile number");
		
		addressPage.clickOnContinueButton();
		logger.info("Clicking on continue button");
		Thread.sleep(2000);
}
@Test
public void validationBanners() throws InterruptedException
{
Thread.sleep(4000);
//Assert.fail();
int bannerPlanNumber= resultPage.getPlanNumersFromBanners();
int StringPlanNumbers = resultPage.getPlanNumersFromString();
logger.info ("validating banners");
Assert.assertEquals(StringPlanNumbers,bannerPlanNumber,"Plan on banners not matching with result,TC failed");

}
@Test
public void ChecksortPlan() throws InterruptedException 
{
Thread.sleep(4000);
Assert.fail();
logger.info("validating presence of sort button");
Assert.assertTrue(resultPage.sortPlanFilterIsDisplayed(),"Sort plan Dropdown is not display,TC is fail");

}
//@AfterClass
public void CloseBrowser() throws InterruptedException{
logger.info("closong browser");
{
driver.close();	
}	

}

}
