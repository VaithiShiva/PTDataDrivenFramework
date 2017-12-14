package page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class BookingDetailsPage extends CommonObjects{
	
	
	WebDriver driver;
	public BookingDetailsPage(WebDriver driver)
	{
		System.out.println("dd"+driver);
		this.driver = driver;
		System.out.println("vv"+this.driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,20);
	}

	@FindBy(xpath = "//*[@id='j_idt40']/div[2]/ul/li[1]/div[2]")
	public WebElement primeTripPNRElement;

	@FindBy(xpath = "//*[@id='j_idt42']")
	public WebElement operatorPNRElement;
	

	@FindBy(xpath = "//*[@id='j_idt40']/div[2]/ul/li[3]/div[2]")
	public WebElement bookedDateElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[2]/ul/li[4]/div[2]")
	public  WebElement mobileNumberElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[2]/ul/li[5]/div[2]")
	public WebElement emailIDElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[3]/ul/li[1]/div[2]")
	public WebElement journeyDateElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[3]/ul/li[2]/div[2]")
	public WebElement operatorNameElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[3]/ul/li[3]/div[2]")
	public WebElement busTypeElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[3]/ul/li[4]/div[2]")
	public WebElement sourceAndDestinationElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[5]/div/div/table/tbody/tr[1]/td[2]")
	public WebElement ticketFareElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[5]/div/div/table/tbody/tr[2]/td[2]")
	public WebElement operatorDiscountElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[5]/div/div/table/tbody/tr[4]/td[2]")
	public WebElement convenienceFeeElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[5]/div/div/table/tbody/tr[5]/td[2]")
	public WebElement GSTElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[5]/div/div/table/tbody/tr[6]/td[2]")
	public WebElement totalFareElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[5]/div/div/table/tbody/tr[7]/td[2]")
	public WebElement fromWalletElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[5]/div/div/table/tbody/tr[8]/td[2]")
	public WebElement otherPaymentElement;

	@FindBy(xpath = "//*[@id='j_idt40']/div[4]/div[3]/input[2]")
	public WebElement downloadPDFTicketElement;
	
	@FindBy(xpath = "//*[@id='j_idt40']/div[4]/div[3]/input[2]")
	public WebElement ticketPDF;

	public List<String> verifyTicketDetails() 
	{
		String pnrNumber = primeTripPNRElement.getText();
		String operatorPNR = operatorPNRElement.getText();
		String bookedDate = bookedDateElement.getText();
		String mobileNumber = mobileNumberElement.getText();
		String emailID = emailIDElement.getText();
		String journeyDate = journeyDateElement.getText();
		String operatorName = operatorNameElement.getText();
		String busType = busTypeElement.getText();
		String sourceAndDestination = sourceAndDestinationElement.getText();
		String ticketFare = ticketFareElement.getText();
		String operatorDiscount = operatorDiscountElement.getText();
		String convenienceFee = convenienceFeeElement.getText();
		String GST = GSTElement.getText();
		String totalFare = totalFareElement.getText();
		String fromWallet = fromWalletElement.getText();
		String otherPayment = otherPaymentElement.getText();
		
		List<String> ticketDetails = new ArrayList<String>();
		ticketDetails.add(pnrNumber);
		ticketDetails.add(operatorPNR);
		ticketDetails.add(bookedDate);
		ticketDetails.add(mobileNumber);
		ticketDetails.add(emailID);
		ticketDetails.add(journeyDate);
		ticketDetails.add(operatorName);
		ticketDetails.add(busType);
		ticketDetails.add(sourceAndDestination);
		ticketDetails.add(ticketFare);
		ticketDetails.add(operatorDiscount);
		ticketDetails.add(convenienceFee);
		ticketDetails.add(GST);
		ticketDetails.add(totalFare);
		ticketDetails.add(fromWallet);
		ticketDetails.add(otherPayment);
		
		
		
		
		for(String toPrint : ticketDetails)
		{
			System.out.println(toPrint+" \n");
		}
		ticketPDF.click();
		
		return ticketDetails;

	}




}
