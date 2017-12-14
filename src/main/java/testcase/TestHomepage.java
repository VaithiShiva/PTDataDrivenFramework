package testcase;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import utils.ExcelUtils;
import page.homepage;

//@Listeners(test.TestBase.class)		

public class TestHomepage extends TestBase{


	private homepage homepage;

	
	@Test(alwaysRun = true)
	public void openHomePage()
	{
		extentTest = extentReports.createTest("Open Homepage");
		homepage = new homepage(driver).open(); 
	}
	@Test(priority=1)
	public void verifySignIn()
	{
		try{
			extentTest = extentReports.createTest("User SignIn");
			String message = homepage.signIn(ExcelUtils.read(1,1,"Data"), ExcelUtils.read(1,2,"Data"));
			assertEquals(message, ExcelUtils.read(1,3,"Data"));
			logger.info("Vaithi");
			ExcelUtils.write(1, 4, "Data", "Pass");
			logger.warn(message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test(priority=2)
	public void searchBus()
	{
		extentTest = extentReports.createTest("SearchBus");
		homepage.searchBus(ExcelUtils.read(4, 0, "Data"), ExcelUtils.read(4, 1, "Data"), ExcelUtils.read(4, 2, "Data"));
	}
}
