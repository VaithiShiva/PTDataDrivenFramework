package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EBSPage extends CommonObjects {
	
	WebDriver driver;
	public EBSPage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		driver.getCurrentUrl();
	}
	
	@FindBy(xpath = "//*[@id='change-card-no']/input[1]")
	public WebElement cardNumber;
	
	@FindBy(xpath = "//*[@id='mm1']")
	public WebElement mm;
	
	@FindBy(xpath = "//*[@id='yy1']")
	public WebElement yy;
	
	@FindBy(xpath = "//*[@id='cvvSection']/input")
	public WebElement cvv;
	
	@FindBy(xpath = "//*[@id='mode-1']/div[3]/input[2]")
	public WebElement nameOnCard;
	
	@FindBy(xpath = "//*[@id='mode-1']/div[3]/button")
	public WebElement makePayment;
	
	public void cardDetails()
	{
		cardNumber.sendKeys("411111000000");
		mm.sendKeys("05");
		yy.sendKeys("26");
		cvv.sendKeys("123");
		nameOnCard.sendKeys("e");
		makePayment.click();
	}

}
