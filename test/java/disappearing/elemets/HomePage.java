package disappearing.elemets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;
	private By disappearingElemets = By.linkText("Disappearing Elements");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public DisappearingElementsPage clickDisappearingElements(){
		driver.findElement(disappearingElemets).click();
		return new DisappearingElementsPage(driver);
	}
	

}
