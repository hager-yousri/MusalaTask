package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
	protected WebDriver drvier ; 
	protected Actions action;

	public PageBase(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		action = new Actions(driver); 
	}
}
