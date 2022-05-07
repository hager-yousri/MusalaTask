package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends PageBase
{

	public ContactUsPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(css="body > main > section.contacts > div > div > div > a.fancybox > button > span")
	WebElement contactUs ;

	@FindBy(css="#cf-1")
	WebElement name;

	@FindBy(css="#cf-2")
	WebElement email;

	@FindBy(css="#cf-4")
	WebElement subject;

	@FindBy(css="#cf-5")
	WebElement message;

	@FindBy(css="#wpcf7-f875-o1 > form > div.btn-cf-wrapper > p > input")
	WebElement sendBtn;

	@FindBy(css="#wpcf7-f875-o1 > form > p:nth-child(4) > span > span")
	WebElement errorMsg;

	@FindBy(css="#fancybox-close")
	WebElement closeBtn;

	public void openContactUs() 
	{
		contactUs.click();
	}

	public void fillContactUsForm(String invalidEmail) 
	{
		name.sendKeys("hager");
		email.sendKeys(invalidEmail);
		subject.sendKeys("subjecttest");
		message.sendKeys("message test");
		sendBtn.click();
	}

	public void clearContactUsForm() 
	{
		name.clear();
		email.clear();
		subject.clear();
		message.clear();
	}

	public String getErrorMsg() 
	{
		return errorMsg.getText();
	}

	public void closeContactUsForm() 
	{
		closeBtn.click();
	}
}
