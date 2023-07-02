package frameworklibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ListenersClass extends BaseTest implements ITestListener {
	
		ExtentReports extentReport;
		ExtentTest test;
		ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
		WebDriver driver;
		
		public synchronized void onStart(ITestContext context) {
		    
			System.out.println("On start");
			
			try {
				new Setup().setPropertyFile();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			String reportPath = System.getProperty("user.dir") + "\\Result\\reportSummary.html" ;
		    ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
		    extentSparkReporter.config().setDocumentTitle("Automation report");
		    extentSparkReporter.config().setReportName("Girish Sarode");
		    
		    extentReport = new ExtentReports();
		    extentReport.attachReporter(extentSparkReporter);
		  
		}
	
	
	    public void onTestStart(ITestResult result) {
	    	test = extentReport.createTest(result.getMethod().getMethodName());
	    	extentTest.set(test);
		  }

		 
		  public void onTestSuccess(ITestResult result) {
		    // not implemented
		  }

	  public void onTestFailure(ITestResult result) {
		  
		    // Fail the test and log the error 
		    System.out.println("@AfterMethod");
			System.out.println("Status:"+ result.getStatus()); 
			extentTest.get().log(Status.FAIL, "Test failed");	
			extentTest.get().fail(result.getThrowable());
			
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
						.get(result.getInstance());
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			TakesScreenshot tsc = (TakesScreenshot) driver;
			File screenShot = tsc.getScreenshotAs(OutputType.FILE);
			File desti = new File(System.getProperty("user.dir") + "//Result//" + "testName" + ".png");
			try {
				FileUtils.copyFile(screenShot,desti);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			extentTest.get().addScreenCaptureFromPath(desti.getPath());
		
	  }

		 
		  public void onTestSkipped(ITestResult result) {
		    // not implemented
		  }

		
		  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }

		
		  public void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }
		 

		  public void onFinish(ITestContext context) {
			  extentReport.flush();
		  }
}
