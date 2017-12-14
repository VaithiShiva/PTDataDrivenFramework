package page;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;



public class homepage extends CommonObjects {

	WebDriver driver;
	public static String homepageURL = null;
	public static final By greyStrip = By.xpath("//div[@class='col-lg-12']/em/b"); 
	public static final By source = By.xpath("//input[@id='Source_input']");
	public static final By sourcePanel = By.xpath("//div[@id='Source_panel']/ul/li");
	public static final By destination = By.xpath("//input[@id='Destination_input']");
	public static final By destinationPanel = By.xpath("//div[@id='Destination_panel']/ul/li");
	public static final By calender = By.xpath("//table[@class='ui-datepicker-calendar']//td");
	public static final By search = By.xpath("//input[@id='search']");
	public static final By signIn = By.xpath("//*[@id='layoutform:j_idt24']/span");
	public static final By username = By.xpath("//input[@id='j_idt144']");
	public static final By password = By.xpath("//input[@id='j_idt148']");
	public static final By submit = By.xpath("//*[@id='j_idt151']");
	public static final By verifySignIn = By.xpath("//span[@class='ui-growl-title']");
	public static final By calenderMonth = By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]");
	public static final By calenderYear = By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]");
	public final By calenderPreviousButton = By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span");
	public final By calenderNextButton = By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span");
	boolean isPresent = false;

	public homepage(WebDriver driver){
		this.driver=driver;
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public homepage() {
		// TODO Auto-generated constructor stub
	}

	public homepage open()
	{
		try
		{
			homepageURL = ExcelUtils.configuration("websiteURL");
			driver.get(homepageURL);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}

	public String signIn(String name, String key)
	{

		WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(signIn));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", signInButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(name);
		driver.findElement(password).sendKeys(key);
		wait.until(ExpectedConditions.visibilityOfElementLocated(submit)).click();
		String message = driver.findElement(verifySignIn).getText();
		return message;
	}

	public String verifyGreyStrip()
	{
		String greyStripText= driver.findElement(greyStrip).getText();
		return greyStripText;
	}

	public void searchBus(String from, String to, String date) 
	{
		try
		{
			WebElement sourceData = driver.findElement(source);
			sourceData.sendKeys(from);
			List<WebElement> sourceAjaxList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(sourcePanel));
			CommonObjects.ajaxListSelector(driver, sourceAjaxList, from);

			Thread.sleep(1000);
			WebElement destinationData = wait.until(ExpectedConditions.presenceOfElementLocated(destination));
			destinationData.sendKeys(to);
			List<WebElement> destinationAjaxList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(destinationPanel));
			CommonObjects.ajaxListSelector(driver, destinationAjaxList, to);

			//calender
			//List<WebElement> datepicker = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calender));
			wait.until(ExpectedConditions.visibilityOfElementLocated(calender)).click();
			String fullDate = ExcelUtils.read(4,2,"Data");
			String[] splitDate = fullDate.split("-", 3);
			String year = splitDate[2];
			String month = splitDate[1];
			String dateToSelect =  splitDate[0];
			
			
			
			this.SelectDateFromDatePicker(year,month,dateToSelect);
			//CommonObjects.ajaxListSelector(driver, datepicker, date);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(search)).click();
			//driver.findElement(search).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void SelectDateFromDatePicker(String year, String month, String date)
    {
		String yearElement = driver.findElement(calenderYear).getText();
		
		
        while (year.equalsIgnoreCase(yearElement)!=true)
        {
            if (Integer.parseInt(year) < Integer.parseInt(yearElement))
            {
                driver.findElement(calenderPreviousButton).click();
            }
            else
            {
                driver.findElement(calenderNextButton).click();
            }
            yearElement = driver.findElement(calenderYear).getText();
        }
        
        String monthElement = driver.findElement(calenderMonth).getText();

       /* while (lblMonth.Text != "January")
        {
            btnPrevious.Clicks();
        }*/

        while (month.equalsIgnoreCase(monthElement)!=true)
        {
        	driver.findElement(calenderNextButton).click();
        	 monthElement = driver.findElement(calenderMonth).getText();
        }
        
        List<WebElement> datepicker = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calender));
        CommonObjects.ajaxListSelector(driver, datepicker, date);

        /*IWebElement dateField = PropertiesCollection.driver.FindElement(By.XPath("//a[text()=\""+ date+"\"]"));
        dateField.Clicks();*/
    }




}
