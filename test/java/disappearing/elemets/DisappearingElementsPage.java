package disappearing.elemets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DisappearingElementsPage {
	private WebDriver driver;
	private By homeButton = By.cssSelector("li:nth-of-type(1) > a");
	private By aboutButton = By.cssSelector("li:nth-of-type(2) > a");
	private By contactusButton = By.cssSelector("li:nth-of-type(3) > a");
	private By portofolioButton = By.cssSelector("li:nth-of-type(4) > a");
	private By galleryButton = By.cssSelector("li:nth-of-type(5) > a");
	
	
	public DisappearingElementsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public PageNotFound clickAboutButton() {
		driver.findElement(aboutButton).click();
		return new PageNotFound(driver);
	}
	
	public PageNotFound clickContactusButton() {
		driver.findElement(contactusButton).click();
		return new PageNotFound(driver);
	}
	
	public PageNotFound clickportofolioButton() {
		driver.findElement(portofolioButton).click();
		return new PageNotFound(driver);
	}
	
	public PageNotFound clickgalleryButton() {
		driver.findElement(galleryButton).click();
		return new PageNotFound(driver);
	}
	
	public HomePage clickHomeButton() {
		driver.findElement(homeButton).click();
		return new HomePage(driver);
	}
	

}
