package Webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtrasPage extends Page {

	// Selectors within the DOM.
	By airAndFlightContinueBtn = By.cssSelector(".airport-and-flight__cta.ry-button--gradient-yellow.ry-button--large");
	By transportContinueBtn = By.cssSelector(".transport__cta.ry-button--gradient-yellow.ry-button--large");
	By declineInsuranceBtn = By.cssSelector(
			".enhanced-takeover__product-dismiss-cta.ry-button--anchor-blue.ry-button--anchor.ng-star-inserted");

	public ExtrasPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		pageName = "Edit Personal Details";
		PageFactory.initElements(driver, this);
	}

	public void declineInsuranceBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(airAndFlightContinueBtn));
		driver.findElement(declineInsuranceBtn).click();

	}

	public void continueBtn() {	
		wait.until(ExpectedConditions.elementToBeClickable(airAndFlightContinueBtn));
		driver.findElement(airAndFlightContinueBtn).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(transportContinueBtn));
		driver.findElement(transportContinueBtn).click();
	}
}
