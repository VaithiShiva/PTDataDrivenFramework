package page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;


public class SearchListPage extends CommonObjects{

	homepage home;
	private WebDriver driver;
	public final static int tableNumber = Integer.parseInt(ExcelUtils.read(9, 7, "Data"));
	public By viewSeats = By.xpath("//*[@id='"+ExcelUtils.read(9, 4, "Data")+tableNumber+ExcelUtils.read(9, 5, "Data")+"']");
	public static String seatHeadListElement1 = ExcelUtils.read(12,4,"Data")+tableNumber+ExcelUtils.read(12,5,"Data");
	public static By seatList = By.xpath("//*[@id='"+ExcelUtils.read(12,4,"Data")+tableNumber+ExcelUtils.read(12,5,"Data")+"']//child::div");
	
	public String seatsID1 = "//img[@id='"+ExcelUtils.read(10, 4, "Data")+tableNumber+ExcelUtils.read(10, 5, "Data");
	public String seatsID2 = ExcelUtils.read(10, 6, "Data")+"']";    
	public String imageSrc = "http://192.168.1.110:9061"+ExcelUtils.read(11, 4, "Data");
	public static By serviceNameLocator =  By.xpath("//*[@id='TBL']/div/ul/li[1]/div[1]/div[1]/div[1]/h4");
	public static By boardingPoint = By.xpath("//*[@id='TBL:"+tableNumber+":BOARDPOINT']");
	public static By droppingPoint = By.xpath("//*[@id='TBL:"+tableNumber+":DROPPOINT']");
	public static By selectedSeatNumbers = By.xpath("//*[@id='TBL:"+tableNumber+":SEATS']");
	String serviceName;
	public static By Fare = By.xpath(ExcelUtils.read(13, 4, "Data"));
	
	public static By totalFare = By.xpath("//*[@id='TBL:"+tableNumber+":PROCEED']"); 

	public SearchListPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	}

	public String selectSeat(int noOfSeats)
	{
		try
		{
			JavascriptExecutor executor = ((JavascriptExecutor)driver);
			executor.executeScript("arguments[0].scrollIntoView(true);", wait.until(ExpectedConditions.visibilityOfElementLocated(viewSeats)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(viewSeats)).click();
			serviceName = driver.findElement(serviceNameLocator).getText();
			List<WebElement> seats = driver.findElements(seatList);
			int size = seats.size();

			List<String> element = new ArrayList<String>();
			for(int i = 0 ; i<=size ; i++)
			{
				System.out.println("locater Path"+seatsID1+i+seatsID2);
				By seatImage = By.xpath(seatsID1+i+seatsID2);
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

				for (WebElement webElement : driver.findElements(seatImage)) {
					if(webElement!=null)
					{
						String content = webElement.toString();
						int a = content.indexOf("/");
						int b = content.lastIndexOf("]");
						element.add(content.substring(a, b));
					}
				}
			}
			int elementSize = element.size();
			int j=1;
			for(int i = 0 ; i<=elementSize ; i++)
			{
				WebElement seatSelection = driver.findElement(By.xpath(element.get(i)));
				String imageSource = seatSelection.getAttribute("src");
				if(imageSource.equalsIgnoreCase(imageSrc))
				{
					seatSelection.click();
					j++;
					if(j>noOfSeats)
					{
						break;
					}
				}
			}
		}	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return serviceName;
	}

	public List<String> chooseBoardingAndDroppingPoints()
	{
		action = new Actions(driver);
		action.moveByOffset(20, 10);
		WebElement boardingLocation = driver.findElement(boardingPoint);
		select = new Select(boardingLocation);
		String bPoint;
		if(select.getOptions().get(0).getText().equalsIgnoreCase("Select"))
		{
			select.selectByIndex(1);
			bPoint = select.getOptions().get(1).getText();
		}
		else
		{
			select.selectByIndex(0);
			bPoint = select.getOptions().get(0).getText();
		}

		WebElement droppingLocation = driver.findElement(droppingPoint);
		select = new Select(droppingLocation);
		String dPoint;
		if(select.getOptions().get(0).getText().equalsIgnoreCase("Select"))
		{
			select.selectByIndex(1);
			dPoint = select.getOptions().get(1).getText();
		}
		else
		{
			select.selectByIndex(0);
			dPoint = select.getOptions().get(0).getText();
		}

		List<String> boardingAndDroppingPoints = new ArrayList<String>();
		boardingAndDroppingPoints.add(bPoint);
		boardingAndDroppingPoints.add(dPoint);
		return boardingAndDroppingPoints;
	}

	public List<String> searchListTicketDetails() {
		List<String> searchListTicketDetails = new ArrayList<String>();
		try{
		
		String seatNumbers = driver.findElement(selectedSeatNumbers).getText();
		String serviceFare = driver.findElement(Fare).getText();
		String rupees = serviceFare.substring(3);
		WebElement totalServiceFareelement = driver.findElement(totalFare);
		String totalServiceFare = totalServiceFareelement.getAttribute("value");
		String totalRupees = totalServiceFare.substring(19);
		
		searchListTicketDetails.add(seatNumbers);
		searchListTicketDetails.add(rupees);
		searchListTicketDetails.add(totalRupees);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return searchListTicketDetails;
	}
	
	public void submit()
	{
		try
		{
		WebElement proceedToPay = driver.findElement(totalFare);
		/*action = new Actions(driver);
		action.moveToElement(proceedToPay).click().build().perform();*/
		
		capabilities = ((RemoteWebDriver)driver).getCapabilities();
		if(capabilities.getBrowserName().equalsIgnoreCase("chrome"))
		{
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+proceedToPay.getLocation().y+")");
			proceedToPay.click();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
