package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonObjects {
	public static WebDriverWait wait;
	public Select select;
	public Actions action ;
	public Capabilities capabilities;
	public static boolean assertAndVerify(By element,WebDriver driver) throws InterruptedException
	{
		boolean isPresent = false;
		for(int i=0;i<=5;i++)
		{
			try
			{
				if(driver.findElement(element) != null)
				{
					isPresent = true;
					break;	
				}
			}
			catch(Exception e)
			{
				Thread.sleep(5000);
			}
		}
		return isPresent;
	}	

	public static void ajaxListSelector(WebDriver driver,List<WebElement> list,String value )
	{
		for(WebElement element : list)
		{
			String pick = element.getText();
			if(pick.equalsIgnoreCase(value))
			{
				element.click();
			}
		}
	}

}
