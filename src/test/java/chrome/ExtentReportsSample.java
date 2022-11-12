package chrome;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import Demo.DemoLog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsSample {
	//public static ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/ExtentReport1.html");
	public static ExtentReports extent = new ExtentReports();
	
	public static ExtentHtmlReporter reporter =  new ExtentHtmlReporter(System.getProperty("user.dir")+"//target//Reports//extentReport1.html");
	
public static String screenShot;
	
	WebDriver driver;

	@Test
	public void openTest() throws Exception
	{
		DemoLog.sample(2);
		extent.attachReporter(reporter);
		ExtentTest logger1 = extent.createTest("Open Test");
		logger1.log(Status.INFO, "Open the Application");
		try {
			WebDriverManager.chromedriver().setup();
		 	driver = new ChromeDriver();
			driver.get("https://www.google.com/");
			DemoLog.sample(1);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			logger1.log(Status.PASS, "Application opened");
		}
		catch( Exception e)
		{
			DemoLog.sample(3);
			logger1.log(Status.FAIL, "Failed Logout");
		}
		extent.flush();
		
		DemoLog.sample(1);
		ExtentTest logger2= extent.createTest("Checking Test");
		logger2.log(Status.INFO, "Checking the title of  application");
		String expectedResult ="Gmail";
		String actualResult = (driver.getTitle());
		try {
			DemoLog.sample(1);
			Assert.assertTrue((expectedResult.equals(actualResult)) ? true : false);
			System.out.println("Pass");
			logger2.log(Status.PASS, "Title verified");
			}
		catch(AssertionError e)
		{
			logger2.log(Status.FAIL, "Failed title verification");
			DemoLog.sample(3);
			//screenShot = screenShot.capture();
			//logger2.addScreenCaptureFromPath(screenShot);
			//logger2.addScreenCaptureFromPath("D:\\Delivery\\Automation Testing\\Automation Testing.jpg");
		}
		extent.flush();
	}

	@AfterTest
	public void closeTest()
	{
		DemoLog.sample(1);
		extent.attachReporter(reporter);
		ExtentTest logger3 = extent.createTest("Closing the application");
		logger3.log(Status.INFO, "Logout of application");
		try {
			DemoLog.sample(1);
			driver.quit();
			logger3.log(Status.PASS, "Passed Closed");
		}
		catch(Exception e)
		{
			logger3.log(Status.FAIL, "Failed Closing");
			DemoLog.sample(3);
		}
		extent.flush();
	}
}
