package Webpages;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	WebDriver driver;
	WebDriverWait wait;
	String pageName;

	// Values that can be used as input for forms
	String[] maleFirstNames = { "maleTestFirstName1", "maleTestFirstName2", "maleTestFirstName3" };
	String[] femaleFirstNames = { "femaleTestFirstName1", "femaleTestFirstName2", "femaleTestFirstName3" };
	String[] LastNames = { "testLastName1", "testLastName2", "testLastName3" };
	String postCode = "IG8 0SG";

	public Page(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public int getRandomNumber(int maxNumber) {

		// Obtain a number between 0 - (maxNumber-1).
		// Used for randomizing input data.
		Random rand = new Random();
		int n = rand.nextInt(maxNumber);
		return n;
	}

	// This method generates a future date by taking the current date and adding x amount of days to it.
	// It then returns a string in the format YYYY-MM-DD.
	protected String generateFutureDate(int lengthOfTrip) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, lengthOfTrip);
		LocalDate localDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		// The return date must be in the format YYYY-MM-DD. So, if the Month or Day is
		// <10, we need to add a 0 before it.
		int year = localDate.getYear();
		String month = localDate.getMonthValue() < 10 ? "0" + localDate.getMonthValue()
				: localDate.getMonthValue() + "";
		String day = localDate.getDayOfMonth() < 10 ? "0" + localDate.getDayOfMonth() : localDate.getDayOfMonth() + "";

		return year + "-" + month + "-" + day;
	}
}
