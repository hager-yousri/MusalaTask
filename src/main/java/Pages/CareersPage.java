package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CareersPage extends PageBase
{

	public CareersPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(css="#menu-main-nav-1 > li.menu-item.menu-item-type-post_type.menu-item-object-page.menu-item-478 > a")
	WebElement careers ;

	@FindBy(css="#content > div.entry-header > div > div.image-overlay > div > section > div > a > button > span")
	WebElement openPositions ;

	@FindBy(css="#get_location")
	WebElement locations ;

	@FindBy(css="#content > section > div.inner-wraper > article")
	List <WebElement> positions;

	@FindBy(css="#content > section > div.inner-wraper > article:nth-child(6) > div > a > div > div.front > h2")
	WebElement qaPosition;

	@FindBy(css="#post-1501 > div > div:nth-child(3) > div.entry-content > div:nth-child(1) > div.requirements.pull-right > div.content-title > h2")
	WebElement generalDescription;

	@FindBy(css="#post-1501 > div > div:nth-child(3) > div.entry-content > div:nth-child(1) > div.requirements.pull-left > div.content-title > h2")
	WebElement requirements;

	@FindBy(css="#post-1501 > div > div:nth-child(3) > div.entry-content > div:nth-child(2) > div.requirements.pull-right > div.content-title > h2")
	WebElement responsiblities;

	@FindBy(css="#post-1501 > div > div:nth-child(3) > div.entry-content > div:nth-child(2) > div.requirements.pull-left > div.content-title > h2")
	WebElement whatWeOffer;

	@FindBy(css="#post-1501 > div > div:nth-child(3) > div.btn-apply-container > a > input")
	WebElement applyBtn;

	@FindBy(css="#fancybox-close")
	WebElement closeApplyFormBtn;

	@FindBy(css="#cf-1")
	WebElement name;

	@FindBy(css="#cf-2")
	WebElement email;

	@FindBy(css="#cf-3")
	WebElement mobile;

	@FindBy(css="#cf-4")
	WebElement uploadCV;

	@FindBy(css="#cf-6")
	WebElement message;

	@FindBy(css="#wpcf7-f880-o1 > form > div.btn-cf-wrapper > p > input")
	WebElement sendBtn;

	@FindBy(css="#wpcf7-f880-o1 > form > div.message-form > div > div")
	WebElement errorMessage;

	@FindBy(css="#wpcf7-f880-o1 > form > div.message-form > div > button")
	WebElement closeApplyAlertBtn;

	public void openCareers() 
	{
		careers.click();
	}

	public void openPositions() 
	{
		openPositions.click();
	}

	public void selectLocation(String city) 
	{
		locations.click();
		Select locationsDropDownMenu=new Select(locations);
		locationsDropDownMenu.selectByVisibleText(city);
	}

	public void selectQaPosition() 
	{
		qaPosition.click();
	}

	public WebElement getGeneralDescriptionElement() 
	{
		return generalDescription;
	}

	public WebElement getRequirementsElement() 
	{
		return requirements;
	}

	public WebElement getResponsibilitiesElement() 
	{
		return responsiblities;
	}

	public WebElement getWhatWeOfferElement() 
	{
		return whatWeOffer;
	}

	public WebElement getApplyElement() 
	{
		return applyBtn;
	}
	public void cliclOnApplyBtn() 
	{
		applyBtn.click();
	}

	public void uploadCVWithInvalidEmail() 
	{
		name.sendKeys("joe");
		email.sendKeys("invalidemail");
		mobile.sendKeys("01001478965");
		String csv=System.getProperty("user.dir")+"/src/test/java/Data/CV.doc";
		uploadCV.sendKeys(csv);
		message.sendKeys("test message");
	}

	public void clickOnSendBtn() 
	{
		sendBtn.click();
	}

	public void clearFields() 
	{
		name.clear();	
		email.clear();
		mobile.clear();
		message.clear();
	}

	public String getErrorMessage() 
	{
		return errorMessage.getText();
	}

	public void closeApplyForPositionAlert() 
	{
		closeApplyAlertBtn.click();
	}

	public void closeApplyForPositionForm() 
	{
		closeApplyFormBtn.click();
	}

	public List <WebElement> getPositinos() 
	{
		return positions;
	}

}
