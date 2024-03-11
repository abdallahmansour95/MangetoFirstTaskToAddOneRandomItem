package javapackages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavaClass {
	
	
	
	WebDriver driver = new ChromeDriver();
	
	
	
	@BeforeTest
	
	
	
	
	public void myBeforeTest() {
		
		
		driver.manage().window().maximize();
				
	}
	
	
	
	
	
	
	@Test
	
	public void myfirstTest() throws InterruptedException {
		
		
		driver.get("https://magento.softwaretestingboard.com/\"");
		
//		driver.findElement(By.linkText("Sign In")).click();
//		driver.findElement(By.partialLinkText("Create")).click();
		driver.findElement(By.linkText("Create an Account")).click();
		Thread.sleep(2000);
//	   driver.findElement(By.tagName("input")).sendKeys("ahmad"); 
		
		
		WebElement thrForm = driver.findElement(By.id("form-validate"));
		
		List <WebElement> myinputFeilds = thrForm.findElements(By.tagName("input"));
		
		System.out.println(myinputFeilds.size());
		
		for(int i =0; i<myinputFeilds.size() ;i++) {
			
			
			if (myinputFeilds.get(i).getAttribute("type").equals("hidden")) {
				
				continue;
			}
			
			else
				
			{
				myinputFeilds.get(i).sendKeys("ahmad");
				
			}
	
		}		
	}
	
	
	
	
	
	
	
	
	



	
	
}