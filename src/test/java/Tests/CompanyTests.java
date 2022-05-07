package Tests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.CompanyPage;

public class CompanyTests
{
	WebDriver driver;
	CompanyPage companyObject;
	String URL;
	ArrayList<String> browserTabs;

	@Parameters({"browser","url"})
	@BeforeClass

	public void StartDriver(String browser,String url)
	{

		if(browser.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("chrome")) 
		{ 
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver(); 
		} 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.navigate().to(url);

		companyObject=new CompanyPage(driver);
	}

	@Test (priority = '1')
	public void UserCanOpenCompany() 
	{
		companyObject.openCompany();
		URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "https://www.musala.com/company/" );
		Assert.assertEquals(true, (companyObject.getLeadershipElement()).isDisplayed());
	}


	@Test (priority = '2')
	private void OpenFacebook() 
	{
		companyObject.openFacebook();
		browserTabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(1));
		URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "https://www.facebook.com/MusalaSoft?fref=ts" );
	}

	@AfterClass
	public void stopDriver() 
	{
		driver.quit();
	}
}
