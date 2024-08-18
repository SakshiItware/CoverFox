package coverFoxTest;


import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDetailsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxPOM.CoverFoxResultPage;
import coverFoxUtility.Utility;
import dev.failsafe.internal.util.Assert;
@Listeners()
public class CoverFoxTC1 extends Base {
	CoverFoxHomePage homePage;
	CoverFoxHealthPlanPage healthPlanPage;
	CoverFoxAddressDetailsPage addressDetailsPage;
	CoverFoxMemberDetailsPage memberDetailsPage;

	CoverFoxResultPage resultPage;
	String filePath;
	@BeforeTest
	public void launchBrowser() {
	openBrowser();
	homePage = new CoverFoxHomePage(driver);
	healthPlanPage = new CoverFoxHealthPlanPage(driver);
	addressDetailsPage = new CoverFoxAddressDetailsPage(driver);
	memberDetailsPage = new CoverFoxMemberDetailsPage(driver);
	resultPage = new CoverFoxResultPage(driver);
	filePath = "\"C:\\Users\\saksh\\OneDrive\\Documents\\sakshiExceldoc (1).xlsx\\";
	}
	@BeforeClass
	public void preConditions() throws InterruptedException,
	EncryptedDocumentException, IOException {
	homePage.clickOnGenderButton();
	Thread.sleep(1000);
	healthPlanPage.clickOnNextButton();
	Thread.sleep(1000);
	memberDetailsPage.handleAgeDropDown(Utility.readDataFromExcel(filePath,"Sheet5", 0, 0));

	memberDetailsPage.clickOnNextButton();
	Thread.sleep(1000);
	addressDetailsPage.enterPincode(Utility.readDataFromExcel(filePath, "Sheet5",0, 1));

	addressDetailsPage.enterMobileNumber(Utility.readDataFromExcel(filePath,"Sheet5", 0, 2));

	addressDetailsPage.clickOnContinueButton();
	}
	@Test
	public void validateBanners() throws InterruptedException {
	Thread.sleep(4000);
	org.testng.Assert.fail();
	int bannerPlanNumbers = resultPage.getPlanNumersFromBanners();
	int StringplanNumbers = resultPage.getPlanNumersFromString();
	org.testng.Assert.assertEquals(StringplanNumbers, bannerPlanNumbers,
	"Plans on banners not matching with results, TC failed");

	}
	@Test
	public void validatePresenceOfSortButton() throws InterruptedException, IOException
	{

	Thread.sleep(4000);
	org.testng.Assert.assertTrue(resultPage.sortPlanFilterIsDisplayed(), "Sort Plan filter is not displayed,TC is failed");

	Utility.takeScreenShot(driver, "validatePresenceOfSortButton");
	}
	@AfterClass
	public void closeBrowser() throws InterruptedException {
	browserClose();
	}
	}

