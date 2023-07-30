package Webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BagsPage extends Page {

	public BagsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		pageName = "Edit Personal Details";
		PageFactory.initElements(driver, this);
	}

	By smallBagRadioBtn = By.cssSelector(".ry-radio-circle-button__label");
	By continueBtn = By.cssSelector(".ry-button--gradient-yellow");

	public void selectSmallBag() {
		wait.until(ExpectedConditions.presenceOfElementLocated(smallBagRadioBtn));
		driver.findElement(smallBagRadioBtn).click();
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		driver.findElement(continueBtn).click();
	}

}
