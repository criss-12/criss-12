package com.herokuapp.theinternet;

import org.openqa.selenium.By;
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

public class LoginTests {
	
	private WebDriver driver;
	
	//@Optional, adica daca paramterul la apelare nu este dat corect, nu va intra pe niciunul din cazuri, si va executa
	//codul din default, adica va deschide chrome
	
	@Parameters({ "browser"})
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("chrome") String browser) { //putem sa ii dam parametru la @Optional si atunci va executa 
															//codul din case:"chrome" si nu default
		System.out.println("Starting loginTest");
		// create driver
		
		
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");// the path to the driver
			driver = new ChromeDriver();
			break;
			
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Do not know to start" + browser + "starting chrome instead");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");// the path to the driver
			driver = new ChromeDriver();
			break;
		}
		
		
		
		

		
		// create driver variable
		

		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();
		
		
		//implicit wait
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })

	public void positiveLoginTest() {

		// open test page
		String url = "https://the-internet.herokuapp.com/secure";
		driver.get(url);

		System.out.println("Page is opened");

		sleep(2000);
		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		
		
		//wait for the dates to be entered,and then click the login button
		WebDriverWait wait = new WebDriverWait(driver, 10);

		// click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();

		// verifications:
		// new url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl(); // ia URL ul folosit ca valoare
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

		// logout button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		// WebElement logOutButton =driver.findElement(By.xpath("//a[@class='button
		// secondary radius broken']"));

		// button is visible

		Assert.assertTrue(logOutButton.isDisplayed(), "LogOutButton is not visible."); // isDisplayed e folosita pentru
																						// a vedea ca e vizibil butonul
																						// -returneaza boolean

		// succseful login message
		// WebElement succesMessage =driver.findElement(By.cssSelector("div#flash"));

		// we can t acces if the element has two classes, separated by space as
		// className, we need just one,
		// but it s not recommendet to use byclassName because we can have more elements
		// in a class with the same name
		// it will take the first element in the class, if it s the first, than it s ok,
		// otherwise not
		WebElement succesMessage = driver.findElement(By.xpath("//div[@id='flash']"));

		String expectedMessage = "You logged into a secure area!";
		String actualMessage = succesMessage.getText();
		// nu merge asa deoarece metoda getText ia si subelemente, si va lua si textul
		// acelor sub elemente
		// Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not
		// the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expectedMessage.\nActual Message: " + actualMessage
						+ "\n Expected Message: " + expectedMessage); // actualMessage contine expectedMessage + textul
																		// subelementelor

		

	}



	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

		// open test page
		String url = "https://the-internet.herokuapp.com/secure";
		driver.get(url);

		System.out.println("Page is opened");

		sleep(2000);

		// enter username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		// username.sendKeys("tomsmith");

		// enter password
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys(password);

		// click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

		// Verifications
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String actualErrorMessage = errorMessage.getText();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected. \nActual: " + actualErrorMessage + "\nExpected: "
						+ expectedErrorMessage);

		

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
