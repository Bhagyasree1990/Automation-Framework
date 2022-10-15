package com.cucumber.objectrepository;

import org.openqa.selenium.By;
import com.cucumber.utils.GenericFunctions;

public class LogoVerification extends GenericFunctions {

	public static final String URL = "https://www.google.com";
	public static By searchField =   By.xpath("//input[@aria-label='Search']");
	public static By searchButton =   By.xpath("(//input[@aria-label='Google Search'])[2]");
	public static By firstSearchResult =   By.xpath("(//div[@class='g']//a)[1]");
	public static By jpmorganLogo =   By.xpath("(//img[@class='first-logo'])[2]");
	public static By acceptcookies=By.xpath("//div[@class='QS5gu sy4vM']");
}