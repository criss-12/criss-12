package dropdown.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class DropdownTests {
	
	private WebDriver driver;
	DropdownPage pageElem;
	
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("chrome") String browser) {
		
		System.out.println("Starting dropdownTest");
		
		switch(browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case "firefox":
			System.setProperty("webdriver.firefox.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		default:
			System.out.println("Do not know to start" + browser + "starting chrome instead");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
				
			
		}

		driver.manage().window().maximize();
		
	}
	
	
	
	public void openPage() {
		String url = "https://the-internet.herokuapp.com/dropdown";
		driver.get(url);
		
		System.out.println("Page is opened!");

	}
	


}
