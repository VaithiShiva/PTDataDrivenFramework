package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BecomeAnAgent {
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id='j_idt65']")
	public WebElement fileUpload;
	
	@FindBy(xpath = "//*[@id='layoutform']/div/ul/li[4]/a/span")
	public WebElement becomeAnAgentLink;
	
	@FindBy(xpath = "//*[@id='name']")
	public WebElement agentName;
	
	@FindBy(xpath = "//*[@id='organization']")
	public WebElement organizationName;
	
	@FindBy(xpath = "//*[@id='address']")
	public WebElement address;
	
	@FindBy(xpath = "//*[@id='STATE']")
	public WebElement state;
	
	@FindBy(xpath = "//*[@id='CITY']")
	public WebElement city;
	
	@FindBy(xpath = "//*[@id='pincode']")
	public WebElement pincode;
	
	@FindBy(xpath = "//*[@id='mobileNumber']")
	public WebElement mobileNumber;
	
	
	@FindBy(xpath = "//*[@id='emailId']")
	public WebElement emailID;
	
	@FindBy(xpath = "//*[@id='j_idt60']")
	public WebElement documentType;
	
	@FindBy(xpath = "//*[@id='comments']")
	public WebElement remarks;
	
	public BecomeAnAgent(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void registration()
	{
		becomeAnAgentLink.click();
		fileUpload.sendKeys("E://seleniumWorkspace//Selenium//downloads//TICKET_FWP170001593.pdf");
	}

}
