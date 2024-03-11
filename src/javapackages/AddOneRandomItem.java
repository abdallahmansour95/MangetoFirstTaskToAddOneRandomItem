package javapackages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOneRandomItem {

	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/\\";
	Random ran = new Random();
	int RandomIndex = ran.nextInt(6);

	@BeforeTest

	public void mySetup() {

		driver.get(URL);
		driver.manage().window().maximize();

	}

	@Test (invocationCount = 5)

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

		}

	}
}
