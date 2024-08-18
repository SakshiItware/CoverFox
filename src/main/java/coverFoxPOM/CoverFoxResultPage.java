package coverFoxPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoverFoxResultPage {
	@FindBy(xpath = "//div[contains(text(),'Insurance Plans')]")
	private WebElement resultText;
	@FindBy(className = "plan-card-container")
	private List<WebElement> banners;
	@FindBy(xpath = "//div[@class='title']")private WebElement sortPlanFilter ;
	public CoverFoxResultPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	public void validateBanners() {
	String[] ar = resultText.getText().split(" ");
	int result = Integer.parseInt(ar[0]);
	if (result == banners.size()) {
	System.out.println("Result is matching with banners, TC passed");
	} else {
		System.out.println("Result is not matching with banners, TC failed");
		}
		}
		public int getPlanNumersFromString()
		{
		String ar[]=resultText.getText().split(" ");
		int result = Integer.parseInt(ar[0]);
		return result;
		}
		public int getPlanNumersFromBanners()
		{
		int bannerSize = banners.size();
		return bannerSize;
		}
		public boolean sortPlanFilterIsDisplayed()
		{
		boolean result = sortPlanFilter.isDisplayed();
		return result;
		}
		}
