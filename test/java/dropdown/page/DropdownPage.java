package dropdown.page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropdownPage {

	private WebDriver driver;
	private String dropdownHeader = "/html//select[@id='dropdown']";
	private String dropdownField = "dropdown";
	private String dropdownContent = "content";

	public DropdownPage(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForVisibilityByXpath(WebDriver driver, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			} catch (TimeoutException exception) {
			System.out.println("Exception catched: " + exception.getMessage());
	}
	}
	
	public void waitForVisibilityById(WebDriver driver, String id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		} catch (TimeoutException exception) {
			System.out.println("Exception catched: " + exception.getMessage());
		}

	}

	public void clickDropdownTab(WebDriver driver) {
		waitForVisibilityByXpath(driver, dropdownHeader);
		WebElement optionsButton = driver.findElement(By.xpath(dropdownHeader));
		optionsButton.click();
		
	}
	
	public void clickOption1(WebDriver driver) {
		WebElement elementList = driver.findElement(By.id(dropdownField));
		waitForVisibilityById(driver, dropdownField);
		elementList.isSelected();
		
	}
	
	public void clickOption2(WebDriver driver) {
		WebElement elementList = driver.findElement(By.id(dropdownField));
		waitForVisibilityById(driver, dropdownField);
		elementList.isSelected();
		
	}



}
