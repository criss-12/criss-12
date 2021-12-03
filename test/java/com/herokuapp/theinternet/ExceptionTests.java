package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionTests {

	private WebDriver driver;

	// @Optional, adica daca paramterul la apelare nu este dat corect, nu va intra
	// pe niciunul din cazuri, si va executa
	// codul din default, adica va deschide chrome

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("chrome") String browser) { // putem sa ii dam parametru la @Optional si atunci va
																// executa
																// codul din case:"chrome" si nu default
		System.out.println("Starting loginTest");
		// create driver

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");// the path to the
																									// driver
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Do not know to start" + browser + "starting chrome instead");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");// the path to the
																									// driver
			driver = new ChromeDriver();
			break;
		}

		// create driver variable

		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();

		// implicit wait
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void notVisibleTest() {
		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);

		// find startButton locator
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		// find element text
		WebElement finishElement = driver.findElement(By.id("finish"));

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(finishElement));// asteapta pentru ca elementul sa fie vizibil
		String finishText = finishElement.getText(); // getText vede doar textul care nu este ascuns
														// daca este ascuns -returneaza empty string, de-asta esueaza
														// testul

		Assert.assertTrue(finishText.contains("Hello World!"), "Finish Text: " + finishText);

	}

	@Test(priority = 2)
	public void timeoutTest() {
		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);

		// find startButton locator
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		// find element text
		WebElement finishElement = driver.findElement(By.id("finish"));

		WebDriverWait wait = new WebDriverWait(driver, 2);
		
		
		try {
			wait.until(ExpectedConditions.visibilityOf(finishElement));
		} catch (TimeoutException exception) {
			System.out.println("Exception catched: " + exception.getMessage());
			sleep(3000);

		} // asteapta pentru ca elementul sa fie vizibil
		String finishText = finishElement.getText(); // getText vede doar textul care nu este ascuns
														// daca este ascuns -returneaza empty string, de-asta esueaza
														// testul

		Assert.assertTrue(finishText.contains("Hello World!"), "Finish Text: " + finishText);

	}

	@Test(priority = 3)
	public void noSuchElementTest() {
		String url = "http://the-internet.herokuapp.com/dynamic_loading/2";
		driver.get(url);

		// find startButton locator
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		Assert.assertTrue(
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"), "Hello World!")),
				"Could not verify expected text: 'Hello World!'");

		/*
		 * WebElement finishElement =
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));//
		 * asteapta pentru ca elementul sa fie vizibil
		 * 
		 * 
		 * // find element text
		 * 
		 * 
		 * String finishText = finishElement.getText(); // getText vede doar textul care
		 * nu este ascuns // daca este ascuns -returneaza empty string, de-asta esueaza
		 * // testul
		 * 
		 * Assert.assertTrue(finishText.contains("Hello World!"), "Finish Text: " +
		 * finishText);
		 */

	}

	@Test(priority=5)
	public void staleElementTest() {
		driver.get("https://the-internet.herokuapp.com/dynamic_controls");

		WebElement checkbox = driver.findElement(By.id("checkbox"));
		WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));

		WebDriverWait wait = new WebDriverWait(driver, 10);
		removeButton.click();
		// wait.until(ExpectedConditions.invisibilityOf(checkbox));

		// Assert.assertFalse(checkbox.isDisplayed());

		/*
		 * Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(checkbox)),
		 * "Checkbox is still visible but should not be!");
		 */

		Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkbox)), // this condition stalnessOf will wait
																				// for staleness
				"Checkbox is still visible, but shouldn't be");

		WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
		addButton.click();

		WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
		Assert.assertTrue(checkbox2.isDisplayed(), "Checkbox is not visible, but it should be");

	}

	@Test(priority=4)
	public void disabledElementTest() {
		driver.get("https://the-internet.herokuapp.com/dynamic_controls");

		WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(), 'Enable')]"));
		WebElement textField = driver.findElement(By.xpath("(//input)[2]"));

		enableButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(textField));

		textField.sendKeys("My name is Dmitry!");
		Assert.assertEquals(textField.getAttribute("value"), "My name is Dmitry!");// asteapta pana cand textboxul va fi
																					// enabled

	}

	private void sleep(long m) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// close browser
		driver.quit();
	}
}
