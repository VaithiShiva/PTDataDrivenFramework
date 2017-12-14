package testcase;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.ExcelUtils;


public class TestBase implements ITestListener  {

	public static WebDriver driver;

	public static ExtentReports extentReports;
	public static ExtentHtmlReporter extentHtmlReporter;
	public static ExtentTest extentTest;
	
	public Robot robot;


	utils.FareCalculation fareCalculation;
	Logger logger = Logger.getLogger(TestBase.class);
	public static Properties properties = new Properties();

	public TestBase()
	{

	}

	@BeforeSuite
	public void extentReport(){
		try
		{
			extentHtmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/ExtentReport/extent-report.html"));
			extentReports = new ExtentReports();	
			extentReports.attachReporter(extentHtmlReporter);
			extentReports.setSystemInfo("Host Name", "SVN");
			extentReports.setSystemInfo("Environment", "Deployment Server");
			extentReports.setSystemInfo("User", "Vaithi");
			extentHtmlReporter.config().setDocumentTitle("Public Portal Automation Results");
			extentHtmlReporter.config().setReportName("Booking Process");
			extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			extentHtmlReporter.config().setTheme(Theme.DARK);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	@AfterMethod
	public void getResult(ITestResult result)
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			extentTest.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+"Test case failed due to below issues", ExtentColor.RED));
			extentTest.fail(result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"Test case passed", ExtentColor.BLUE));
		}
		else
		{
			extentTest.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"Test case skipped due to below reason", ExtentColor.ORANGE));
			extentTest.skip(result.getThrowable());
		}

	}


	@AfterSuite
	public void saveReport()
	{
		extentReports.flush();
	}

	@Parameters({"chrome","mozilla"})
	public TestBase(String chrome,String mozilla)
	{
		try{
			if(chrome.equalsIgnoreCase("chrome"))
				System.setProperty("webdriver.chrome.driver", ExcelUtils.configuration("chromeDriverPath"));
			
			Map<String,Object> chromePreferences = new HashMap<String,Object>();
			chromePreferences.put("profile.default_content_settings.popups", 0);
			File file = new File(System.getProperty("user.dir")+"/downloads/");
			file.mkdir();
			chromePreferences.put("download.default_directory", file.toString());
			//chromePreferences.put("download.default_directory", "E://seleniumWorkspace//Selenium//downloads//");
			ChromeOptions options = new ChromeOptions();
			
			Map<String,Object> chromeOptionsMap = new HashMap<String,Object>();
			options.setExperimentalOption("prefs", chromePreferences);
			options.addArguments("--test-type");
			options.addArguments("--disable-extentions");
			
			DesiredCapabilities chromeCapabilities = new DesiredCapabilities().chrome();
			chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(chromeCapabilities);
			driver.manage().window().maximize();
			PropertyConfigurator.configure(ExcelUtils.configuration("log4jProperties"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/*public void logInfo(String message)
	{
		logger.info(message);
	}*/
	public void test()
	{

	}

	@Override
	public void onTestStart(ITestResult result) {
		
		logger.info("Test " +result.getName()+ " started" );
		//System.out.println("This is just a Test" +result.getName());
	}


	public void onTestSuccess(ITestResult result) {
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.info("Test "+result.getName()+" completed in "+result.getEndMillis()+" seconds");
			System.out.println("This is just a success" +result.getName());
		}
	}
	@Override
	public void onTestFailure(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.error("Test "+result.getName()+" failed");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if(result.getStatus() == ITestResult.SKIP)
		{
			logger.info("Test "+result.getName()+" skipped");
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//logger.error(message);
	}


	@Override
	public void onStart(ITestContext context) {
		logger.info("Test Started" );
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test Completed "+context.getPassedTests());
	}
	
	public void downloadFileToPath(String filepath)
	{
		
		
	}
	
	
}