package com.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", 
features = { "src/test/resources/FeatureFiles/LogoVerfication.feature" }, 
glue = {"com.cucumber.stepdefinitions" }, 
plugin = { "pretty","html:target/cucumber-reports.html"},
monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests {

}
