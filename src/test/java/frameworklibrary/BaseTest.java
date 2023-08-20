package frameworklibrary;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


@Listeners(frameworklibrary.ListenersClass.class)

public class BaseTest {
	
	public WebDriver driver;
	ThreadLocal<WebDriver> extentDriver = new ThreadLocal<WebDriver>();
	
     
	public String takeScreenShot(String methodName) {
		
		// take the screen shot and attach to result 
		TakesScreenshot tsc = (TakesScreenshot) extentDriver.get();
		File screenShot = tsc.getScreenshotAs(OutputType.FILE);
		File desti = new File(System.getProperty("user.dir") + "//Result//" + "testName" + ".png");
		try {
			FileUtils.copyFile(screenShot,desti);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return desti.getPath();
	}
//################################################################################################################	
	@BeforeMethod
	public synchronized void beforeMethod(ITestResult testName) {
		System.out.println("@BeforeMethod");
		System.setProperty("webdriver.chrome.driver", "D:\\Automation\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		extentDriver.set(driver);			
	}
	
//################################################################################################################		
	@DataProvider(name="testData")
	public Object[][] testData(Method m) throws IOException {
		// Fetch the class name and method name 
		String className = this.getClass().getSimpleName();
		String methodName = m.getName();
		TestDataComponent tdc = new TestDataComponent(className,methodName); 
		ArrayList<Map<String,String>> data = tdc.getData();
		int size = data.size();
		Object[][] obj = new Object[size][1]; 
		
		for(int i=0;i<size;i++) {
			obj[i][0] = data.get(i);
		}
		
		return obj;
	}

	
//################################################################################################################	
	@AfterMethod
	public synchronized void afterMethod(ITestResult testName) throws IOException {
	   extentDriver.get().close();
	}
//################################################################################################################	
	
	@AfterClass
	public void afterClass() {
		System.out.println("@AfterClass");
	}	

//################################################################################################################		
	@AfterTest
	public void afterTest() {
		System.out.println("@AfterTest");
	}
	
}
