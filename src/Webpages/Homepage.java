package Webpages;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

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

	String username = "nathan_elmi@hotmail.co.uk";
	String nameOfUser = "Nathan";
	String password = "Nat@ryanair1";

	// Selectors within the DOM.
	// Not all selectors can be initialised at initial page load because they are
	// not visible.

	@FindBy(css = ".header-menu-item.ng-star-inserted[aria-label='Log In']")
	private WebElement loginBtn;

	@FindBy(css = ".cookie-popup-with-overlay__button")
	private WebElement acceptCookiesBtn;

	@FindBy(css = ".input-button__input[placeholder='Destination']")
	private WebElement toDestinationTextBox;

	@FindBy(css = ".flight-search-widget__start-search.ry-button--gradient-yellow")
	private WebElement searchBtn;

	By usernameTextBox = By.cssSelector(".body-l-lg.body-l-sm.invisible-background[placeholder='email@email.com']");
	By passwordTextBox = By.cssSelector(".body-l-lg.body-l-sm.invisible-background[placeholder='Password']");
	By confirmLoginBtn = By.cssSelector(".auth-submit__button.ry-button--full.ry-button--flat-yellow");
	By nameOfLoggedInUser = By.cssSelector(".ry-header__user-name > span");
	By listofAirportsLocator = By.cssSelector("fsw-airport-item.ng-star-inserted");

	public Homepage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		pageName = "Edit Personal Details";
		//driver.get(PAGE_URL);
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
		if (driver.findElements(nameOfLoggedInUser).size()>0&&nameOfUser.equals(driver.findElement(nameOfLoggedInUser).getText())) {
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

	public void selectDestinationAirport() {
		List<WebElement> listOfAirports = driver.findElements(listofAirportsLocator);
		for (WebElement element : listOfAirports) {
			// System.out.println(e.getAccessibleName());
			if (element.getAccessibleName().equals("Barcelona")) {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				break;
			}
		}
	}

	public void selectDepartureDate() {
		int daysUntilDeparture = 2; // Departing in 2 days time.
		String departureDateSelector = ".calendar-body__cell[data-id='" + generateFutureDate(daysUntilDeparture) + "']";

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(departureDateSelector)));
		driver.findElement(By.cssSelector(departureDateSelector)).click();
	}

	// Selecting the departure and return dates is tricky as the CSS
	// is dynamic and the selector will contain the specific date.
	// Rather than hardcoding the dates, the generateFutureDate(int x) method will
	// return a date x amount of days in the future. This is then combined with the
	// CSS to find the locator.
	public void selectReturnDate() {
		int daysUntilReturn = 21; // Return date is 21 days in the future
		String returnDateSelector = ".calendar-body__cell[data-id='" + generateFutureDate(daysUntilReturn) + "']";

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(returnDateSelector)));
		driver.findElement(By.cssSelector(returnDateSelector)).click();
	}

	public void clickSearchBtn() {
		searchBtn.click();
	}

}
