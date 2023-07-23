package pagelibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	@FindBy(id ="email") public WebElement userName;   
	@FindBy(id ="old-password") public WebElement password;
	@FindBy(xpath ="//img[@alt='Decathlon']") public WebElement clickButton;
	

	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor)driver;
		wait = new WebDriverWait(driver,30);
	}
	
	
	public void loginStripeApi() {
		driver.get(System.getProperty("StripeURL"));
	}
	
	
}
