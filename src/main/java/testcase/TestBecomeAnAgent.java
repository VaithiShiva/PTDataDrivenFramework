package testcase;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.BecomeAnAgent;

public class TestBecomeAnAgent extends TestBase{
	
	BecomeAnAgent becomeAnAgent;
	
	@Test(priority = 14)
	public void registration()
	{
		becomeAnAgent = PageFactory.initElements(driver, BecomeAnAgent.class);
		becomeAnAgent.registration();
	}

}
