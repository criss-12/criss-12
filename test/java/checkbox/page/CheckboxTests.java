package checkbox.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckboxTests {

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
	public void checkboxTest() {
		System.out.println("CheckBoxTest");

		// open test page
		String url = "https://the-internet.herokuapp.com/checkboxes";
		driver.get(url);
		System.out.println("Page is opened.");

		sleep(2000);

		// create the checkBox to check
		WebElement checkBox = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));

		// click on it to check it
		checkBox.click();

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
