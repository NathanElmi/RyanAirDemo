package Regression;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Webpages.BagsPage;
import Webpages.ExtrasPage;
import Webpages.FlightsPage;
import Webpages.Homepage;
import Webpages.ReviewAndPayPage;
import Webpages.SeatsPage;

public class Main {

	ChromeOptions opt;
	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void setUp() {
		/*
		 * ChromeOptions allows tests to be run on an existing browser, rather than
		 * creating a new browser each run.
		 * 
		 * This is useful for debugging purposes, as I can navigate to a specific page
		 * within a journey and test it immediately, rather than navigating through the
		 * whole e2e journey
		 */
		opt = new ChromeOptions();
		opt.setExperimentalOption("debuggerAddress", "localhost:56426");
		opt.addArguments("--remote-allow-origins=*");

		// Creation of webdriver and explicit wait
		System.setProperty("webdriver.chrome", "chromedriver.exe");
		driver = new ChromeDriver(opt);
		// driver.manage().deleteAllCookies();

		// driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@Test(priority = 1)
	private void loginToAccount() {
		Homepage homepage = new Homepage(driver, wait);
		//driver.manage().deleteAllCookies();
		
		//homepage.acceptCookies();

		if (!homepage.isUserLoggedIn()) {
			homepage.clickLogin();
			homepage.enterCredentials();
		}
	}

	@Test(priority = 2)
	private void bookStandardReturnflight() {
		/*
		 * Homepage homepage = new Homepage(driver, wait); // homepage.acceptCookies();
		 * homepage.enterDestinationCountry(); homepage.selectDestinationAirport();
		 * homepage.selectDepartureDate(); homepage.selectReturnDate();
		 * homepage.clickSearchBtn();
		 * 
		 * FlightsPage flightsPage = new FlightsPage(driver, wait);
		 * flightsPage.selectDepartureFlight(); flightsPage.selectReturnFlight();
		 * flightsPage.selectFareType(); flightsPage.confirmBasicFareBtn();
		 * flightsPage.populatePassengerNames();
		 * 
		 * SeatsPage seatsPage = new SeatsPage(driver, wait);
		 * seatsPage.selectSeatForDeparture(); seatsPage.declineSameSeatForReturn();
		 * seatsPage.selectSeatForReturn(); seatsPage.skipFastTrack();
		 * 
		 * BagsPage bagsPage = new BagsPage(driver, wait); bagsPage.selectSmallBag();
		 * 
		 * ExtrasPage extrasPage = new ExtrasPage(driver, wait);
		 * extrasPage.declineInsuranceBtn(); extrasPage.continueBtn();
		 */

		ReviewAndPayPage reviewAndPayPage = new ReviewAndPayPage(driver, wait);
		reviewAndPayPage.populateContactDetails();
		reviewAndPayPage.populatePaymentDetails();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// if(result.equals("e"))
		// driver.quit();
		// driver.quit();

	}

}
