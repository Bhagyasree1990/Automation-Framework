package com.cucumber.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitiateDriver {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	ConfigReader cr;
	Properties props = new Properties();
	
	public InitiateDriver(){
		cr = new ConfigReader();
		try {
			props.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Log4j.properties"));
			PropertyConfigurator.configure(props);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver getDriver() {
		return (WebDriver) driver.get();
	}

	public static void setDriver(WebDriver driver) {
		InitiateDriver.driver.set(driver);
	}

	public void launchBrowser(String browser) {
		
		switch (browser) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver.exe");
			InitiateDriver.setDriver(new ChromeDriver());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-popup-blocking");
			break;
			
		case "Firefox":
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\geckodriver.exe");
			InitiateDriver.setDriver(new FirefoxDriver());
			break;
			
		default:
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
			InitiateDriver.setDriver(new ChromeDriver());
			break;
		}
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(cr.getImplicitlyWait()));
		getDriver().manage().window().maximize();

	}

}
