package Webpages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeatsPage extends Page {

	// Selectors within the DOM.
	By standardSeatBtn = By.cssSelector(".seatmap__seat.seatmap__seat--standard.ng-star-inserted");
	By nextFlightBtn = By.cssSelector(".passenger-carousel__button-next");
	By dissmissFastTrackBtn = By
			.cssSelector(".enhanced-takeover-beta__product-dismiss-cta.ry-button--anchor-blue.ry-button--anchor");
	By declineSameSeatBtn = By
			.cssSelector(".seats-offer__cta.body-xl-lg.body-l-sm.ry-button--anchor-blue.ry-button--anchor");

	public SeatsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		pageName = "Edit Personal Details";
		PageFactory.initElements(driver, this);
	}

	public void selectSeatForDeparture() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(standardSeatBtn));
		driver.findElements(standardSeatBtn).get(0).click();
		wait.until(ExpectedConditions.elementToBeClickable(nextFlightBtn));
		driver.findElement(nextFlightBtn).click();
	}

	// Once the seat for departure is selected, Ryanair will suggest the same seat
	// for the return leg assuming it's available and the user can then decline it.
	// Hence, the declineSameSeatBtn may or may not be displayed. Therefore, I've
	// used a fluentwait here, which will give up if the button isn't available.
	public void declineSameSeatForReturn() {
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(2))
				.pollingEvery(Duration.ofMillis(250));
		try {
			fluentWait.until(ExpectedConditions.elementToBeClickable(declineSameSeatBtn));
			driver.findElement(declineSameSeatBtn).click();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void selectSeatForReturn() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(standardSeatBtn));
		driver.findElements(standardSeatBtn).get(0).click();
		wait.until(ExpectedConditions.elementToBeClickable(nextFlightBtn));
		driver.findElement(nextFlightBtn).click();
	}

	public void skipFastTrack() {
		wait.until(ExpectedConditions.elementToBeClickable(dissmissFastTrackBtn));
		driver.findElement(dissmissFastTrackBtn).click();
	}

}
