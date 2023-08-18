package Webpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightsPage extends Page {
	
	// Selectors within the DOM.
	@FindBy(css = ".fare-card__button.fare-card__price.ry-button--outline-dark-blue")
	private WebElement continueWithBasicFareBtn;

	By selectFlightBtnLocators = By.cssSelector(".flight-card-summary__select-btn");
	By titleDropDown = By.cssSelector(".dropdown__toggle.body-l-lg.body-l-sm");
	By mrTitle = By.cssSelector(".dropdown-item__link");
	By firstNameTextBox = By.cssSelector(
			".body-l-lg.body-l-sm.date-placeholder.invisible-background[name='form.passengers.ADT-0.name']");
	By lastNameTextBox = By.cssSelector(
			".body-l-lg.body-l-sm.date-placeholder.invisible-background[name='form.passengers.ADT-0.surname']");
	By continueBtn = By.cssSelector(".continue-flow__button.ry-button--gradient-yellow");
	By confirmBasicFareBtn = By
			.cssSelector(".fare-upgrade-footer-continue_button.ry-button--outline-light-blue.ry-button--full");

	public FlightsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		pageName = "Edit Personal Details";
		PageFactory.initElements(driver, this);
	}

	// The following two methods assume that there are outbound and inbound flights
	// available for the selected dates. It picks the first available inbound and
	// outbound flight.
	// TODO if there are no available flights, select the next date. This is tricky
	// to do without knowing the underlying logic Ryanair use for CSS.
	public void selectDepartureFlight() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectFlightBtnLocators));
		List<WebElement> selectDepartureFlightBtn = driver.findElements(selectFlightBtnLocators);
		if (selectDepartureFlightBtn.size() > 0) {
			selectDepartureFlightBtn.get(0).click();
		}

	}

	public void selectReturnFlight() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectFlightBtnLocators));
		List<WebElement> selectReturnFlightBtn = driver.findElements(selectFlightBtnLocators);
		if (selectReturnFlightBtn.size() > 0) {
			selectReturnFlightBtn.get(0).click();
		}
	}

	public void selectFareType() {
		wait.until(ExpectedConditions.elementToBeClickable(continueWithBasicFareBtn));
		continueWithBasicFareBtn.click();
	}

	public void confirmBasicFare() {
		wait.until(ExpectedConditions.elementToBeClickable(confirmBasicFareBtn));
		driver.findElement(confirmBasicFareBtn).click();
	}

	public void populatePassengerNames() {

		wait.until(ExpectedConditions.elementToBeClickable(titleDropDown));
		driver.findElement(titleDropDown).click();
		driver.findElement(mrTitle).click();
		driver.findElement(firstNameTextBox).sendKeys("Nathan");
		driver.findElement(lastNameTextBox).sendKeys("Elmi");
		driver.findElement(continueBtn).click();

	}

}