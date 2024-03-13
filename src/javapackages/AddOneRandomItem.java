package javapackages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOneRandomItem {

	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/\\";
	Random ran = new Random();
	int RandomIndex = ran.nextInt(4);

	@BeforeTest

	public void mySetup() {

		driver.get(URL);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

	}

	@Test (priority= 1,invocationCount = 1 )

	public void addOneRandomItemTotheCart() throws InterruptedException {

		WebElement Container = driver.findElement(By.className("product-items"));

		List<WebElement> myList = Container.findElements(By.tagName("li"));

		myList.get(RandomIndex).click();
		Thread.sleep(2000);

		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {

			WebElement AddtoCartButton = driver.findElement(By.id("product-addtocart-button"));

			AddtoCartButton.click();
		} else {

			WebElement sizeContainer = driver
					.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));

			List<WebElement> theListOfSizes = sizeContainer.findElements(By.tagName("div"));

			int Randomsize = ran.nextInt(theListOfSizes.size());

			theListOfSizes.get(Randomsize).click();

			WebElement colorContainer = driver
					.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));

			List<WebElement> theListOfColors = colorContainer.findElements(By.tagName("div"));
			int Randomcolor = ran.nextInt(theListOfColors.size());

			theListOfColors.get(Randomcolor).click();
			
			WebElement AddtoCartButton = driver.findElement(By.id("product-addtocart-button"));

			AddtoCartButton.click();
			Thread.sleep(3000);

		}

	}
	
	
	
	@Test(priority = 2)
	
	
	public void checkoutProcess() throws InterruptedException {
		
		String CheckoutPage = "https://magento.softwaretestingboard.com/checkout/cart/";
		driver.get(CheckoutPage);
		Thread.sleep(1000);
		
		
	WebElement ProcessCheckButton=driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']")); // use selector hub
	
	ProcessCheckButton.click();
	
	
	
	
	
	
		
	}
	
	
@Test (priority = 3)
	
	public void SignupProcess() throws InterruptedException {
		Thread.sleep(1000);
		WebElement email = driver.findElement(By.id("customer-email")); 
		WebElement firstName = driver.findElement(By.name("firstname")); 
		WebElement lastName = driver.findElement(By.name("lastname")); 
		WebElement StreetAddress = driver.findElement(By.name("street[0]")); 
		WebElement city = driver.findElement(By.name("city")); 
		WebElement state = driver.findElement(By.name("region_id")); 
		WebElement postalCode = driver.findElement(By.name("postcode")); 
		WebElement Country = driver.findElement(By.name("country_id")); 
		WebElement phoneNumber = driver.findElement(By.name("telephone")); 
		WebElement nextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));

		email.sendKeys("abd99@gmail.com");
		firstName.sendKeys("abed");
		lastName.sendKeys("mansour");

		StreetAddress.sendKeys("amman marj alhamam");

		city.sendKeys("amman");

		state.sendKeys("marj alhamam");

		postalCode.sendKeys("19955");

		Country.sendKeys("Jordan");

		phoneNumber.sendKeys("962789948483");   
		

		nextButton.click();
		
		Select select = new Select(Country);
		
//		select.selectByValue("CN");
//		select.selectByIndex(1);
		select.selectByVisibleText("Chile");
	}
	
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	

