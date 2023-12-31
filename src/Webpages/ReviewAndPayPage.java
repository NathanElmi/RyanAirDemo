package Webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewAndPayPage extends Page {
	
	// Selectors within the DOM.
	By phoneNumberTextBox = By.cssSelector(".body-l-lg.body-l-sm[name='phone-number']");
	By addressLine1TextBox = By.cssSelector(".body-l-lg[name='address-line1']");
	By addressLine2TextBox = By.cssSelector(".body-l-lg[name='address-line2']");
	By cityTextBox = By.cssSelector(".body-l-lg[name='city']");
	By countryDropDown = By.cssSelector(".body-l-lg[name='country']");
	By countryDropDown_GB = By.cssSelector(".reactive-complete__option[data-ref='country-code-GB']");
	By expiryDateTextBox = By.cssSelector(".body-l-lg[name='cc-exp']");
	By securityCodeTextBox = By.cssSelector(".body-l-lg[name='cvc']");
	By cardholderNameTextBox = By.cssSelector(".body-l-lg[name='ccname']");
	By cardNumberTextBox = By.cssSelector(".body-l-lg[name='cardNumber']");
	By cardNumberFrame = By.cssSelector(".modal__credit-card-iframe.ng-star-inserted");
	By postCodeTextBox = By.cssSelector(".body-l-lg[name='postcode']");

	public ReviewAndPayPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		pageName = "Review and Pay";
		PageFactory.initElements(driver, this);
	}

	public void populateContactDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(phoneNumberTextBox));
		driver.findElement(phoneNumberTextBox).sendKeys("0783700000");
	}

	public void populatePaymentDetails() {

		// The card number text box is located within its own frame. Hence, we have to
		// switch to that frame before entering it
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cardNumberFrame));
		driver.switchTo().frame(driver.findElement(cardNumberFrame));
		driver.findElement(cardNumberTextBox).sendKeys("123456789");
		driver.switchTo().defaultContent();

		driver.findElement(expiryDateTextBox).sendKeys("0126");
		driver.findElement(securityCodeTextBox).sendKeys("123");
		driver.findElement(cardholderNameTextBox).sendKeys("Nathan Elmi");

		driver.findElement(addressLine1TextBox).sendKeys("Kensington Gardens");
		driver.findElement(addressLine2TextBox).sendKeys("Ilford");
		driver.findElement(cityTextBox).sendKeys("London");
		driver.findElement(countryDropDown).click();
		driver.findElement(countryDropDown).sendKeys("United Kingdom");
		wait.until(ExpectedConditions.elementToBeClickable(countryDropDown_GB));
		driver.findElement(countryDropDown_GB).click();
		wait.until(ExpectedConditions.elementToBeClickable(postCodeTextBox));
		driver.findElement(postCodeTextBox).sendKeys("IG13EL");

	}

}
