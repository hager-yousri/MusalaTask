package Tests;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.CareersPage;

public class CareersTests
{
	WebDriver driver;
	CareersPage careersObject;
	String url;
	List <WebElement> allSofiaPositions;
	List <WebElement> allSkopjePositions;
	WebDriverWait wait;
	WebElement element;

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

		careersObject=new CareersPage(driver);

	}

	@Test (priority = '1')
	private void checkThatCheckOurOpenPositionsCanBeOpened() 
	{
		careersObject.openCareers();
		careersObject.openPositions();
		url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.musala.com/careers/join-us/" );
	}

	@Test (priority = '2')
	private void checkThatTheUserCanFilterOpenPositions() 
	{
		careersObject.selectLocation("Anywhere");
		careersObject.selectQaPosition();

		Assert.assertEquals(true, careersObject.getGeneralDescriptionElement().isDisplayed());
		Assert.assertEquals(true, careersObject.getRequirementsElement().isDisplayed());
		Assert.assertEquals(true, careersObject.getResponsibilitiesElement().isDisplayed());
		Assert.assertEquals(true, careersObject.getWhatWeOfferElement().isDisplayed());
		Assert.assertEquals(true, careersObject.getApplyElement().isDisplayed());

	}

	@Test (priority = '3')
	private void checkThatTheUserCanNotApplyForPositionWithInvalidEmail() 
	{
		careersObject.cliclOnApplyBtn();	
		careersObject.uploadCVWithInvalidEmail();		
		careersObject.clickOnSendBtn();

		wait = new WebDriverWait(driver, 10);
		element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#wpcf7-f880-o1 > form > div.message-form > div > div")));

		assertTrue((careersObject.getErrorMessage()).contains("One or more fields have an error. Please check and try again."));

		careersObject.closeApplyForPositionAlert();
	}

	@Test (priority = '4')
	private void checkThatTheUserCanNotApplyForPositionWithEmptyRequiredFields() 
	{
		careersObject.clearFields();		
		careersObject.clickOnSendBtn();

		wait = new WebDriverWait(driver, 10);
		element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#wpcf7-f880-o1 > form > div.message-form > div > div")));

		assertTrue((careersObject.getErrorMessage()).contains("One or more fields have an error. Please check and try again."));

		careersObject.closeApplyForPositionAlert();
		careersObject.closeApplyForPositionForm();
	}

	@Test (priority = '5')
	private void checkThatTheUserCanSearchOpenPositionInEachCity() 
	{
		careersObject.openCareers();
		careersObject.openPositions();
		careersObject.selectLocation("Sofia");
		System.out.println("Sofia");
		allSofiaPositions = careersObject.getPositinos();
		for (int i = 1; i <= allSofiaPositions.size(); i++) 
		{
			System.out.println("Position: "+driver.findElement(By.cssSelector("#content > section > div.inner-wraper > article:nth-child("+i+") > div > a > div > div.front > h2")).getText());
			System.out.println("More info: "+driver.findElement(By.cssSelector("#content > section > div.inner-wraper > article:nth-child("+i+") > div > a")).getAttribute("href"));
		}

		careersObject.selectLocation("Skopje");
		System.out.println("Skopje");
		allSkopjePositions = careersObject.getPositinos();
		for (int i = 1; i <= allSkopjePositions.size(); i++) 
		{
			System.out.println("Position: "+driver.findElement(By.cssSelector("#content > section > div.inner-wraper > article:nth-child("+i+") > div > a > div > div.front > h2")).getText());
			System.out.println("More info: "+driver.findElement(By.cssSelector("#content > section > div.inner-wraper > article:nth-child("+i+") > div > a")).getAttribute("href"));
		}
	}

	@AfterClass
	public void stopDriver() 
	{
		driver.quit();
	}
}
