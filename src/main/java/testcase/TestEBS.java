package testcase;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import page.EBSPage;

public class TestEBS extends TestBase{


	EBSPage ebsPage;

	@Test(priority = 12)
	public void cardDetails()
	{
		try
		{
		extentTest = extentReports.createTest("EBS Card Details");
		ebsPage = PageFactory.initElements(driver, EBSPage.class);
		ebsPage.cardDetails();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
