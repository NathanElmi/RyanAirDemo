package Webpages;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Homepage extends Page {

	private static String PAGE_URL = "https://www.ryanair.com/gb/en";

	// TODO User Credentials are below. Could possibly read them in from an external
	// file [inserted by Nathan, 17 Aug 2023]
	String username = "nathan_elmi@hotmail.co.uk";
	String password = ""; // password from personal account removed.
	String nameOfUser = "Nathan";

	// Selectors within the DOM.
	@FindBy(css = ".header-menu-item.ng-star-inserted[aria-label='Log In']")
	private WebElement loginBtn;

	@FindBy(css = ".cookie-popup-with-overlay__button")
	private WebElement acceptCookiesBtn;

	@FindBy(css = ".input-button__input[placeholder='Destination']")
	private WebElement toDestinationTextBox;

	@FindBy(css = ".flight-search-widget__start-search.ry-button--gradient-yellow")
	private WebElement searchBtn;

	// Not all selectors can be initialised at initial page load because they might
	// not visible. For those selectors, I have provided the By locators
	By usernameTextBox = By.cssSelector(".body-l-lg.body-l-sm.invisible-background[placeholder='email@email.com']");
	By passwordTextBox = By.cssSelector(".body-l-lg.body-l-sm.invisible-background[placeholder='Password']");
	By confirmLoginBtn = By.cssSelector(".auth-submit__button.ry-button--full.ry-button--flat-yellow");
	By nameOfLoggedInUser = By.cssSelector(".ry-header__user-name > span");
	By listofAirportsLocator = By.cssSelector("fsw-airport-item.ng-star-inserted");

	public Homepage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		pageName = "Edit Personal Details";
		PageFactory.initElements(driver, this);
	}

	public void clickLogin() {
		loginBtn.click();
	}

	public void enterCredentials() {

		wait.until(ExpectedConditions.elementToBeClickable(usernameTextBox));
		driver.findElement(usernameTextBox).sendKeys(username);
		driver.findElement(passwordTextBox).sendKeys(password);
		driver.findElement(confirmLoginBtn).click();
	}

	public boolean isUserLoggedIn() {
		if (driver.findElements(nameOfLoggedInUser).size() > 0
				&& nameOfUser.equals(driver.findElement(nameOfLoggedInUser).getText())) {
			return true;
		}
		return false;
	}

	public void acceptCookies() {
		acceptCookiesBtn.click();
	}

	public void enterDestinationCountry() {
		toDestinationTextBox.click();
		toDestinationTextBox.sendKeys("Spain");
	}

	// The below method stores all airports within a list and then iterates over the
	// list to find a specific airport (Barcelona)
	public void selectDestinationAirport() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(listofAirportsLocator));
		List<WebElement> listOfAirports = driver.findElements(listofAirportsLocator);
		for (WebElement element : listOfAirports) {
			if (element.getAccessibleName().equals("Barcelona")) {
				element.click();
				break;
			}
		}
	}

	/*
	 * Selecting the departure and return dates is tricky as the CSS is dynamic and
	 * the CSS selector will contain the actual date. An example would be
	 * ".calendar-body__cell[data-id='2023-08-25']" Therefore, I created a method,
	 * generateFutureDate(int x), which takes the current date and returns a date x
	 * amount of days in the future.
	 */

	public void selectDepartureDate() {
		int daysUntilDeparture = 2;
		String departureDateSelector = ".calendar-body__cell[data-id='" + generateFutureDate(daysUntilDeparture) + "']";

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(departureDateSelector)));
		driver.findElement(By.cssSelector(departureDateSelector)).click();
	}

	public void selectReturnDate() {
		int daysUntilReturn = 21; // Return date is 21 days in the future
		String returnDateSelector = ".calendar-body__cell[data-id='" + generateFutureDate(daysUntilReturn) + "']";

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(returnDateSelector)));
		driver.findElement(By.cssSelector(returnDateSelector)).click();
	}

	public void clickSearch() {
		searchBtn.click();
	}

}
