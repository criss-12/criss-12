package geolocation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GeoLocationTests {
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

	@Test
	public void GeoLocationTests() {
		System.out.println("GeoLocationTests");

		

		// open test page
		String url = "https://the-internet.herokuapp.com/geolocation";
		driver.get(url);
		System.out.println("Page is opened.");
		
		sleep(2000);
		
		//create geoLButton
		WebElement geoLocationButton = driver.findElement(By.xpath("//div[@id='content']//button[.='Where am I?']"));
		
		//click on geoLButton 
		geoLocationButton.click();		
				
		
		sleep(2000);
				
		//verfications:
		//new url 
		String expectedUrl = "https://the-internet.herokuapp.com/geolocation";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");
		
		//add element button is visible
		WebElement geoLButton = driver.findElement(By.xpath("//div[@id='content']//button[.='Where am I?']"));
		Assert.assertTrue(geoLButton.isDisplayed(), "Add Element Button is not vissible");

		//close browser
		driver.quit();

	}
	
	private void sleep(long m) {
		try {
			Thread.sleep(m);
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