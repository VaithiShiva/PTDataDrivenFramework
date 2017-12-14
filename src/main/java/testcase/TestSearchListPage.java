package testcase;

import java.util.List;

import org.apache.log4j.Level;
import org.testng.annotations.Test;

import utils.ExcelUtils;
import page.SearchListPage;


public class TestSearchListPage extends TestBase  {
	
	SearchListPage SearchListPage;

	@Test(priority=3)
	public void selectSeat()
	{
		try{
			extentTest = extentReports.createTest("Seat Selection");
			System.out.println("2"+driver);
			SearchListPage = new SearchListPage(driver);
			String serviceName = SearchListPage.selectSeat(Integer.parseInt(ExcelUtils.read(4, 4, "Data")));
			utils.ExcelUtils.write(4, 3, "Data", serviceName);
			logger.log(Level.ALL, "Hello logging");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=4)
	public void selectBoardingAndDroppingPoints()
	{
		try{
			extentTest =  extentReports.createTest("Boarding and Dropping Point Selection");
			List<String> boardingAndDroppingPoints = SearchListPage.chooseBoardingAndDroppingPoints();
			String boardingPoint = boardingAndDroppingPoints.get(0);
			String droppingPoint = boardingAndDroppingPoints.get(1);
			ExcelUtils.write(4, 5, "Data", boardingPoint);
			ExcelUtils.write(4, 6, "Data", droppingPoint);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=5)
	public void searchListTicketDetails()
	{
		try{
			extentTest = extentReports.createTest("Search List Page Select Seat Ticket Details");
			List<String> seatNumbers = SearchListPage.searchListTicketDetails();
			ExcelUtils.write(4, 7, "Data", seatNumbers.get(0));
			ExcelUtils.write(4, 8, "Data", seatNumbers.get(1));
			ExcelUtils.write(4, 9, "Data", seatNumbers.get(2));
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test(priority = 6)
	public void Submit()
	{
		SearchListPage.submit();
	}
	
}
