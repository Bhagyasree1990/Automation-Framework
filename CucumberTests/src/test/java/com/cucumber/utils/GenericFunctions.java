package com.cucumber.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

public class GenericFunctions extends InitiateDriver {
	public static Logger logger = Logger.getLogger(GenericFunctions.class);
	
	/**
	 * Method to click on WebElement
	 * 
	 * @param element
	 */
	public void clickOnElement(WebElement element) {
		element.click();
		logger.info("Clicked on "+element);
		waitForPageLoad();
	}
	
	/**
	 * Method to enter text in the given WebElement
	 * 
	 * @param element
	 * @param text
	 */
	public void enterText(WebElement element, String text) {
		element.sendKeys(text);
		logger.info("Entered text: "+text);
	}
	
	
	/**
	 * Method to click on By element
	 * 
	 * @param locator
	 */
	public void clickOnElement(By locator) {
		getDriver().findElement(locator).click();
		logger.info("Clicked on "+locator);
	}
	
	/**
	 * Method to enter text in the given By element
	 * 
	 * @param locator
	 * @param text
	 */
	public void enterText(By locator, String text) {
		getDriver().findElement(locator).sendKeys(text);
		logger.info("Entered text: "+text);
	}

	/**
	 * Method to launch Url
	 * 
	 * @param Url
	 */
	public void launchUrl(String Url) {
		getDriver().get(Url);
		logger.info("Navigated to URL: "+Url);
	}
	
	/**
	 * Method to read page title
	 * 
	 * @return
	 */
	public String getPageTitle() {
		return getDriver().getTitle();
	}
	
	/**
	 * Method to read current url
	 * 
	 * @return
	 */
	public String getCurrentUrl() {
		String currentUrl = getDriver().getCurrentUrl();
		logger.info("Current Url: "+currentUrl);
		return currentUrl;
	}
	
	/**
	 * Method to wait for a WebElement until it is visible with in the specified timeout
	 * 
	 * @param xpathExpression
	 */
	public void waitForElementVisibility(By xpathExpression) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(cr.getGlobalTimeout()));
			wait.until(ExpectedConditions.presenceOfElementLocated(xpathExpression));
		} catch (TimeoutException e) {
			logger.debug(e.getMessage());
		}
	}
	
	/**
	 * Method to wait for page load with in the specified timeout
	 * 
	 */
	public void waitForPageLoad() {
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(cr.getpageLoadTimeout()));
	}
	
	/**
	 * Method returns true if the element is located else returns false
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isDisplayed(By locator) {
		try {
			waitForElementVisibility(locator);
			if (getDriver().findElement(locator).isDisplayed()) {
				logger.info(locator.toString()+" is displayed.");
				return true;
			} else {
				logger.info(locator.toString()+" is NOT displayed.");
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * This function will take screenshot
	 * 
	 * @param webdriver
	 * @param fileWithPath
	 * @throws Exception
	 */
	public static void captureScreenshot(String filename){
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
			File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File destFile = new File(System.getProperty("user.dir")+"\\Screenshots\\"+filename+".png");
			FileUtils.copyFile(srcFile, destFile);
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
