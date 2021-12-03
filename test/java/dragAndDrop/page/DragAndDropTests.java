package dragAndDrop.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DragAndDropTests {
	
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


	@Test
	public void DragAndDropTests() {
		System.out.println("DragAndDropTests");


		// open test page
		String url = "https://the-internet.herokuapp.com/drag_and_drop";
		driver.get(url);
		System.out.println("Page is opened.");

		sleep(2000);
		
		Actions builder = new Actions(driver);
		WebElement from = driver.findElement(By.id("column-a"));
		WebElement to = driver.findElement(By.id("column-b"));
		
		sleep(1000);
		
		builder.dragAndDrop(from, to).perform();
		

		sleep(2000);

		// verfications:
		// new url
		String expectedUrl = "https://the-internet.herokuapp.com/drag_and_drop";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");
		
		// close driver
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