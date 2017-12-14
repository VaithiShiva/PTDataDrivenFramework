package testcase;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import utils.ExcelUtils;
import utils.FareCalculation;
import page.TravellerInfoPage;

public class TestTravellerInfoPage extends TestBase{

	TravellerInfoPage travellerInfoPage;


	@Test(priority = 7)
	public void checkFareDetails()
	{
		try
		{
			extentTest = extentReports.createTest("Check Ticket Fares");
			travellerInfoPage = PageFactory.initElements(driver, TravellerInfoPage.class);
			List<String> fareDetails = TravellerInfoPage.fareDetails();
			List<Float> floatfareDetails = new ArrayList<Float>();
			int j= 0;
			for(String i:fareDetails)
			{
				if(i.contains(","))
				{
					i = i.replace(",","");
				}
				floatfareDetails.add(Float.parseFloat(i));
				System.out.println("* "+floatfareDetails.get(j));
				j++;
			}
			//List<Integer> intfareDetails = fareDetails.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
			fareCalculation = new FareCalculation();
			List<Float> calculatedValues = new ArrayList<Float>();
			calculatedValues = fareCalculation.fareCalculation(floatfareDetails.get(0), Float.parseFloat(ExcelUtils.read(4, 8, "Data")), floatfareDetails.get(2), floatfareDetails.get(3), floatfareDetails.get(5), floatfareDetails.get(6), floatfareDetails.get(7));
			assertEquals(Integer.parseInt(fareDetails.get(0)), Integer.parseInt(ExcelUtils.read(4, 4, "Data")),"Total no of seats not equals");
			System.out.println(floatfareDetails.get(1)+"fare"+calculatedValues.get(4) +"fare not equals");
			assertEquals(floatfareDetails.get(1),calculatedValues.get(4),"fare not equals");
			
			System.out.println(floatfareDetails.get(8)+"primeTrip discount"+calculatedValues.get(0)+"PrimeTrip discount not equals");
			assertEquals(floatfareDetails.get(8), calculatedValues.get(0),"PrimeTrip discount not equals");
			
			System.out.println(floatfareDetails.get(5)+"GST"+ calculatedValues.get(2)+"GST not equals");
			assertEquals(floatfareDetails.get(5), calculatedValues.get(2),"GST not equals");
			
			System.out.println(floatfareDetails.get(6)+"wallet"+ calculatedValues.get(5)+"Amount to be paid from wallet not equals");
			assertEquals(floatfareDetails.get(6), calculatedValues.get(5),"Amount to be paid from wallet not equals");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test(priority = 8)
	public void continuePage()
	{
		TravellerInfoPage.continueButton();
	}

	@Test(priority = 9)
	public void passengerInfo()
	{
		extentTest = extentReports.createTest("Traveller Details");
		travellerInfoPage.passengerInfo();
	}

	@Test(priority = 10)
	public void continuePassengerInfo()
	{
		try
		{
		travellerInfoPage.continuePassengerInfo();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		
	}
	
	@Test(priority = 11)
	public void billingInfoFrame()
	{
		try{
		extentTest = extentReports.createTest("Payment Selection");
		travellerInfoPage.billingInfo();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}




