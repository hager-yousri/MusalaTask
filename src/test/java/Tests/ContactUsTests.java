package Tests;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import Pages.ContactUsPage;

public class ContactUsTests
{
	WebDriver driver;
	ContactUsPage contactUsObject;
	CSVReader reader;
	String csvFile;
	String[] csvCell;
	String invalidEmail;

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

		contactUsObject=new ContactUsPage(driver);
	}

	@Test
	public void UserCanNotSendInvalidContactUsReqest() throws CsvValidationException, IOException 
	{
		contactUsObject.openContactUs();

		csvFile=System.getProperty("user.dir")+"/src/test/java/data/InvalidEmails.csv";
		reader=new CSVReader(new FileReader(csvFile));
		while ((csvCell=reader.readNext()) != null)
		{
			invalidEmail=csvCell[0];
			contactUsObject.fillContactUsForm(invalidEmail);

			Assert.assertEquals(true, contactUsObject.getErrorMsg().contains("The e-mail address entered is invalid."));

			contactUsObject.clearContactUsForm();
		}	
		contactUsObject.closeContactUsForm();
	}

	@AfterClass
	public void stopDriver() 
	{
		driver.quit();
	}

}
