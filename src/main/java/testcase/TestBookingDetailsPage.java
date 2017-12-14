package testcase;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.BookingDetailsPage;
import utils.FareCalculation;

public class TestBookingDetailsPage extends TestBase{

	
	BookingDetailsPage bookingDetailsPage;
	
	@Test(priority=13)
	public void testBookingDetailsPage()
	{
		try
		{
			System.out.println("cc"+driver);
		bookingDetailsPage = PageFactory.initElements(driver, BookingDetailsPage.class);
		List<String> ticketDetails = new ArrayList<String>();
		ticketDetails = bookingDetailsPage.verifyTicketDetails();
		String pnrNumber = ticketDetails.get(0);
		String operatorPNR = ticketDetails.get(1);
		String bookedDate = ticketDetails.get(2);
		String mobileNumber = ticketDetails.get(3);
		String emailID = ticketDetails.get(4);
		String journeyDate = ticketDetails.get(5);
		String operatorName = ticketDetails.get(6);
		String busType = ticketDetails.get(7);
		String sourceAndDestination = ticketDetails.get(8);
		String ticketFare = ticketDetails.get(9);
		String operatorDiscount = ticketDetails.get(10);
		String convenienceFee = ticketDetails.get(11);
		String GST = ticketDetails.get(12);
		String totalFare = ticketDetails.get(13);
		String fromWallet = ticketDetails.get(14);
		String otherPayment = ticketDetails.get(15);
		
		ticketFare = ticketFare.replace("Rs","").trim();
		operatorDiscount = operatorDiscount.replace("Rs","").trim();
		convenienceFee = convenienceFee.replace("Rs","").trim();
		GST = GST.replace("Rs","").trim();
		totalFare = totalFare.replace("Rs","").trim();
		fromWallet = fromWallet.replace("Rs","").trim();
		otherPayment = otherPayment.replace("Rs","").trim();
		
		if(ticketFare.contains(","))
		{
			ticketFare = ticketFare.replace(",", "");
		}
		
		if(totalFare.contains(","))
		{
			totalFare = totalFare.replace(",", "");
		}
		
		if(fromWallet.contains(","))
		{
			fromWallet = fromWallet.replace(",", "");
		}
		
		if(otherPayment.contains(","))
		{
			otherPayment.replace(",", "");
		}
		FareCalculation fareCalculation = new FareCalculation();
		System.out.println(ticketFare+"Fare"+ fareCalculation.getBaseFare()+"Base fare does not match");
		assertEquals(ticketFare, fareCalculation.getBaseFare(),"Base fare does not match");
		
		System.out.println(convenienceFee+"CF"+fareCalculation.getConvenienceFee()+"Convenience fee does not match");
		assertEquals(convenienceFee, fareCalculation.getConvenienceFee(),"Convenience fee does not match");
		
		System.out.println(GST+"GST "+ fareCalculation.getGST()+"GST does not match");
		assertEquals(GST, fareCalculation.getGST(),"GST does not match");
		
		System.out.println(totalFare+"total fare"+ fareCalculation.gettotalFare()+"Total Fare does not match");
		assertEquals(totalFare, fareCalculation.gettotalFare(),"Total Fare does not match");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
