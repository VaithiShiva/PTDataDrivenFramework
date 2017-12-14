package page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;

public class TravellerInfoPage extends CommonObjects{
	private WebDriver driver;

	@FindBy(xpath = "//*[@id='break-upR']/table/tbody/tr[1]/td[2]")
	public static WebElement totalSeat;

	@FindBy(xpath = "//*[@id='break-upR']/table/tbody/tr[2]/td[2]")
	public static WebElement ticketFare;

	@FindBy(xpath = "//*[@id='break-upR']/table/tbody/tr[3]/td[2]")
	public static WebElement operatorDiscount;

	@FindBy(xpath = "//*[@id='break-upR']/table/tbody/tr[4]/td[2]")
	public static WebElement primeTripDiscount;

	@FindBy(xpath = "//*[@id='break-upR']/table/tbody/tr[5]/td[2]")
	public static WebElement convenienceFee;

	@FindBy(xpath = "//*[@id='break-upR']/table/tbody/tr[6]/td[2]")
	public static WebElement GST;

	@FindBy(xpath = "//*[@id='break-upR']/table/tbody/tr[7]/td[2]")
	public static WebElement fromWallet;

	@FindBy(xpath = "//*[@id='break-upR']/table/tbody/tr[8]/td[2]")
	public static WebElement amountToBePaid;

	@FindBy(xpath = "//*[@id='j_idt83']")
	public static WebElement continueButton;
	
	
	@FindBy(xpath = "//*[@id='j_idt133']/span")
	public static WebElement continueButtonPassengerInfo;

	@FindBy(xpath = "//*[@id='break-upR']/table/tbody/tr[4]/td[1]")
	public static WebElement primeTripDiscountPercentage;

	@FindBys(@FindBy(xpath = "//*[@id='tab2']/div[1]/div[1]/div/table"))
	public static List<WebElement> passengerInfoTable = new ArrayList<WebElement>();
	
	@FindBy(xpath = "//*[@id='j_idt140']/div[2]")
	public static WebElement walletMoneyCheckBox;
	
	@FindBy(xpath = "//*[@id='paymentBtn']")
	public static WebElement paymentButton;
	
	@FindBy(xpath = "//*[@id='netBanking']")
	public static WebElement netBanking;

	public TravellerInfoPage(WebDriver driver) {
		try
		{
			this.driver = driver;
			wait = new WebDriverWait(driver, 20);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	public static List<String> fareDetails()
	{
		List<String> fareDetails = new ArrayList<String>();
		try
		{
			String getTotalSeat = totalSeat.getText();
			String getticketFare = ticketFare.getText();
			getticketFare = getticketFare.substring(3);
			String getOperatorDiscount = operatorDiscount.getText();
			getOperatorDiscount = getOperatorDiscount.substring(3);
			String getPrimeTripDiscount = primeTripDiscount.getText();
			getPrimeTripDiscount = getPrimeTripDiscount.substring(4);
			String getConvenienceFee = convenienceFee.getText();
			getConvenienceFee = getConvenienceFee.substring(3);
			String getGST = GST.getText();
			getGST = getGST.substring(3);
			String getFromWallet = fromWallet.getText();
			getFromWallet = getFromWallet.substring(4);
			String getAmountToBePaid = amountToBePaid.getText();
			getAmountToBePaid = getAmountToBePaid.substring(3);
			String getPrimeTripDiscountPercentage = primeTripDiscountPercentage.getText();
			getPrimeTripDiscountPercentage = getPrimeTripDiscountPercentage.substring(getPrimeTripDiscountPercentage.indexOf("(")+1,getPrimeTripDiscountPercentage.indexOf("%"));

			fareDetails.add(getTotalSeat);
			fareDetails.add(getticketFare);
			fareDetails.add(getPrimeTripDiscountPercentage);
			fareDetails.add(getOperatorDiscount);
			fareDetails.add(getConvenienceFee);
			fareDetails.add(getGST);
			fareDetails.add(getFromWallet);
			fareDetails.add(getAmountToBePaid);
			fareDetails.add(getPrimeTripDiscount);


			System.out.println("getTotalSeat: "+getTotalSeat+'\n'+"getticketFare: "+getticketFare+'\n'+"getOperatorDiscount: "+getOperatorDiscount+'\n'+"getPrimeTripDiscount: "+getPrimeTripDiscount+'\n'+"getConvenienceFee: "+getConvenienceFee+'\n'+"getGST: "+getGST+'\n'+"getFromWallet: "+getFromWallet+'\n'+"getAmountToBePaid: "+getAmountToBePaid+'\n'+"getPrimeTripDiscountPercentage :"+getPrimeTripDiscountPercentage);
			//return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return fareDetails;
	}

	public static void continueButton()
	{
		continueButton.click();;
	}

	public void passengerInfo()
	{
		try
		{
			int size = passengerInfoTable.size();
			System.out.println(size);
			int z = 0;
			HashMap<String,String> passenger = new HashMap<String,String>();
			String[] key = {"Name","Age","Gender"};
			for(int i=9; i<=i+size;i++)
			{
				for(int y=0;y<3;y++)
				{
					passenger.put(key[y]+i,ExcelUtils.read(i, y, "Data"));	
				}

				z++;
				if(z==size)
				{
					break;
				}
			}
			/*for(Entry<String,String> entry: passenger.entrySet())
		{
		System.out.println(entry);
		}*/
			int h = 9;
			for(int i=0 ; i<size;i++)
			{
				
				System.out.println("map value :"+passenger.get("Name"+h));
				driver.findElement(By.xpath("//input[@id='Traveller_Details:"+i+":Name']")).sendKeys(passenger.get("Name"+h));
				driver.findElement(By.xpath("//input[@id='Traveller_Details:"+i+":Age']")).sendKeys(passenger.get("Age"+h));
				WebElement gender = driver.findElement(By.xpath("//select[@id='Traveller_Details:"+i+":title']"));
				Select select = new Select(gender);
				if(passenger.get("Gender"+h).equalsIgnoreCase("Male"))
				{
					select.selectByValue("Mr");
				}
				if(passenger.get("Gender"+h).equalsIgnoreCase("Female"))
				{
					select.selectByValue("Miss");
				}
				if(passenger.get("Gender"+h).equalsIgnoreCase("Married"))
				{
					select.selectByValue("Mrs");
				}
				h++;
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void continuePassengerInfo()
	{
		try{
			//continueButtonPassengerInfo.click();
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", continueButtonPassengerInfo);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void billingInfo()
	{
		try
		{
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", walletMoneyCheckBox);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(walletMoneyCheckBox)).click();
			//walletMoneyCheckBox.click();
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(paymentButton)).click();
			paymentButton.click();
			Thread.sleep(1000);
			netBanking.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
