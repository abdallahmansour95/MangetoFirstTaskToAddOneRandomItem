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
import org.testng.Assert;

public class AddOneRandomItem {

	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/\\";
	String Password = "ASDasd123!@";

	@BeforeTest

	public void mySetup() {

		driver.get(URL);
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

	}

	@Test(priority = 1, invocationCount = 1)

	public void addOneRandomItemTotheCart() throws InterruptedException {
		
		Random rand = new Random();

		WebElement Container = driver.findElement(By.className("product-items"));

		List<WebElement> myList = Container.findElements(By.tagName("li"));
		int RandomIndex = rand.nextInt(myList.size());
		

		myList.get(RandomIndex).click();
		Thread.sleep(2000);

		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {

			WebElement AddtoCartButton = driver.findElement(By.id("product-addtocart-button"));

			AddtoCartButton.click();
		} else {

			WebElement sizeContainer = driver
					.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));

			List<WebElement> theListOfSizes = sizeContainer.findElements(By.tagName("div"));

			int Randomsize = rand.nextInt(theListOfSizes.size());

			theListOfSizes.get(Randomsize).click();

			WebElement colorContainer = driver
					.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));

			List<WebElement> theListOfColors = colorContainer.findElements(By.tagName("div"));
			int Randomcolor = rand.nextInt(theListOfColors.size());

			theListOfColors.get(Randomcolor).click();

			WebElement AddtoCartButton = driver.findElement(By.id("product-addtocart-button"));

			AddtoCartButton.click();
			Thread.sleep(3000);

		}

		WebElement Msg = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));

		boolean actual = Msg.getText().contains("You added");
		boolean expected = true;
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 2, enabled = false)

	public void checkoutProcess() throws InterruptedException {

		String CheckoutPage = "https://magento.softwaretestingboard.com/checkout/cart/";
		driver.get(CheckoutPage);
		Thread.sleep(1000);

		WebElement ProcessCheckButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']")); // use
																													// selector
																													// hub

		ProcessCheckButton.click();

	}

	@Test(priority = 3, enabled = false)

	public void SignupProcess() throws InterruptedException {

		String ExpectedMsg = "Thank you for registering with Main Website Store.";

		Thread.sleep(4000);
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

		email.sendKeys("mansourabdallah237@yahoo.com");
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
		select.selectByVisibleText("Jordan");

		Thread.sleep(4000);

		WebElement NextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));
		NextButton.click();

		Thread.sleep(5000);

		WebElement placeOrder = driver.findElement(By.cssSelector(".action.primary.checkout"));
		placeOrder.click();

		Thread.sleep(5000);

		WebElement CreateAnAccountButton = driver.findElement(
				By.xpath("//a[@href='https://magento.softwaretestingboard.com/checkout/account/delegateCreate/']"));

		CreateAnAccountButton.click();
		Thread.sleep(5000);

		WebElement PasswordButton = driver.findElement(By.id("password"));

		WebElement ConfirmPassword = driver.findElement(By.id("password-confirmation"));

		PasswordButton.sendKeys(Password);
		ConfirmPassword.sendKeys(Password);

		WebElement CreateAnAccountFinal = driver.findElement(By.cssSelector(".action.submit.primary"));
		CreateAnAccountFinal.click();
		Thread.sleep(3000);

		WebElement succesfullMsg = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));

		String ActualMsg = succesfullMsg.getText();

		Assert.assertEquals(ActualMsg, ExpectedMsg);

	}

}
