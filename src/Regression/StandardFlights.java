package Regression;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Webpages.BagsPage;
import Webpages.ExtrasPage;
import Webpages.FlightsPage;
import Webpages.Homepage;
import Webpages.ReviewAndPayPage;
import Webpages.SeatsPage;

public class StandardFlights {

	ChromeOptions opt;
	WebDriver driver;
	WebDriverWait wait;

	/*
	 * The below method initialises the Web Driver, Explicit Wait, and Chrome
	 * Options.
	 * 
	 * The setExperimentalOption/addArguments methods allows us to run our tests on
	 * an existing browser, rather than creating a new browser each run. This is
	 * useful for debugging purposes as I can navigate to a specific page and test
	 * it immediately, rather than navigating through an e2e journey. Also useful
	 * for avoiding Captchas on production sites.
	 */
	@BeforeTest
	public void setUp() {

		opt = new ChromeOptions();
		opt.setExperimentalOption("debuggerAddress", "localhost:59042");
		opt.addArguments("--remote-allow-origins=*");

		System.setProperty("webdriver.chrome", "chromedriver.exe");
		driver = new ChromeDriver(opt);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Login to account (if not already logged in)
	@Test(priority = 1)
	private void loginToAccount() {

		Homepage homepage = new Homepage(driver, wait);

		if (!homepage.isUserLoggedIn()) {
			homepage.clickLogin();
			homepage.enterCredentials();
		}
	}

	// The below test books a standard return flight to Barcelona for one adult.
	
	// TODO The location & number of passengers is currently hard coded, ideally it
	// will be randomised [inserted by Nathan, 17 Aug 2023].
	@Test(priority = 2)
	private void bookStandardReturnflight() {

		Homepage homepage = new Homepage(driver, wait);
		// homepage.acceptCookies();
		homepage.enterDestinationCountry();
		homepage.selectDestinationAirport();
		homepage.selectDepartureDate();
		homepage.selectReturnDate();
		homepage.clickSearch();

		FlightsPage flightsPage = new FlightsPage(driver, wait);
		flightsPage.selectDepartureFlight();
		flightsPage.selectReturnFlight();
		flightsPage.selectFareType();
		flightsPage.confirmBasicFare();
		flightsPage.populatePassengerNames();

		SeatsPage seatsPage = new SeatsPage(driver, wait);
		seatsPage.selectSeatForDeparture();
		seatsPage.declineSameSeatForReturn();
		seatsPage.selectSeatForReturn();
		seatsPage.skipFastTrack();

		BagsPage bagsPage = new BagsPage(driver, wait);
		bagsPage.selectSmallBag();

		ExtrasPage extrasPage = new ExtrasPage(driver, wait);
		// extrasPage.declineInsuranceBtn();
		extrasPage.continueBtn();

		ReviewAndPayPage reviewAndPayPage = new ReviewAndPayPage(driver, wait);
		reviewAndPayPage.populateContactDetails();
		reviewAndPayPage.populatePaymentDetails();

	}

}
