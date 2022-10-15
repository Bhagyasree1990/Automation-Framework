package com.cucumber.stepdefinitions;

import org.testng.Assert;
import com.cucumber.objectrepository.LogoVerification;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoVerificationSteps extends LogoVerification {

	public LogoVerificationSteps() {}

	@Before
	public void setUp() {
		launchBrowser("Chrome");
	}

	@Given("User is on Google Home Page")
	public void launchGoogle() {
		
		launchUrl(URL);
		//Incase google shows up accept cookies window (yes in my case)
		//clickOnElement(acceptcookies);
		captureScreenshot("GoogleHomepage");
		Assert.assertEquals(getPageTitle(), "Google", "Home page Title");
	}
	

	@When("Enters keyword as {string}")
	public void enterKeyword(String keyword) {
		enterText(searchField, keyword);
	}
	
	@And("Click Search")
	public void clickSearch() {
		clickOnElement(searchButton);
	}
	
	@And("Click on First Search result")
	public void clickOnFirstSearchResult() {
		clickOnElement(firstSearchResult);
	}

	@Then("Verify the Logo on the first search result page")
	public void verifyLogo() {
		captureScreenshot("JPMorganHomepage");
		Assert.assertTrue(isDisplayed(jpmorganLogo), "J.P. Morgan Logo");
	}

	@After
	public void teardown() {
		getDriver().quit();
	}

}
