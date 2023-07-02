package testscript;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import frameworklibrary.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pagelibrary.HomePage;



public class StripeApi extends BaseTest {

	
// This test launch magneto application
@Test()
public void launchMagneto() throws InterruptedException {	
	
	HomePage homePage = new HomePage(driver);
	homePage.loginStripeApi();
	// Hello world
	
	}

}