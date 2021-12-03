package dropdown.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;
	private By dropdown = By.linkText("Dropdown");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public DropdownPage clickDisappearingElements(){
		driver.findElement(dropdown).click();
		return new DropdownPage(driver);
	}
	

}
