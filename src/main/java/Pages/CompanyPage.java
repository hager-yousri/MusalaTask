package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompanyPage extends PageBase
{
	public CompanyPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(css="#menu-main-nav-1 > li.menu-item.menu-item-type-post_type.menu-item-object-page.menu-item-887 > a")
	WebElement company ;

	@FindBy(css="#content > div.entry-content > section.company-members > div > h2")
	WebElement leadership;

	@FindBy(css="body > footer > div > div > a:nth-child(5) > span")
	WebElement facebookIcon ;

	public void openCompany() 
	{
		company.click();
	}

	public WebElement getLeadershipElement() 
	{
		return leadership;
	}

	public void openFacebook() 
	{
		facebookIcon.click();
	}
}
