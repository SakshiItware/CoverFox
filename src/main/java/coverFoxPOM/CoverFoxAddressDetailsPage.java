package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxAddressDetailsPage {
	@FindBy(className = "mp-input-text")
	private WebElement pinCodeField;
	@FindBy(id = "want-expert")
	private WebElement mobileNumberField;

	@FindBy(className = "next-btn")
	private WebElement continueButton;
	//pinError
	//mobileNumError

	public CoverFoxAddressDetailsPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	public void enterPincode(String pincodeValue) {
	pinCodeField.sendKeys(pincodeValue);
	Reporter.log("reading pincode",true);
	}
	public void enterMobileNumber(String mobileNumber) {
	mobileNumberField.sendKeys(mobileNumber);
	Reporter.log("reading mobile number",true);
	}
	public void clickOnContinueButton() {
	continueButton.click();
	Reporter.log("clickOnContinueButton",true);
	}
	

	}

