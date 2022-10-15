package com.cucumber.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties properties;
	private final String propertyFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\config.properties";
	
	public ConfigReader() {
		BufferedReader reader1;
		try {
			reader1 = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader1);
				reader1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else if(System.getenv().get("implicitWait") != null)
			return Long.parseLong(System.getenv().get("implicitWait"));
		else
			throw new RuntimeException("implicitlyWait not specified in the config.properties file or in environment variables.");
	}

	public long getGlobalTimeout() {
		String globalTimeOut = properties.getProperty("globalTimeOut");
		if (globalTimeOut != null)
			return Long.parseLong(globalTimeOut);
		else if(System.getenv().get("globalTimeOut") != null)
			return Long.parseLong(System.getenv().get("globalTimeOut"));
		else
			throw new RuntimeException("globalTimeOut not specified in the config.properties file or in environment variables.");
	}
	
	public long getpageLoadTimeout() {
		String globalTimeOut = properties.getProperty("pageLoadTimeOut");
		if (globalTimeOut != null)
			return Long.parseLong(globalTimeOut);
		else if(System.getenv().get("pageLoadTimeOut") != null)
			return Long.parseLong(System.getenv().get("pageLoadTimeOut"));
		else
			throw new RuntimeException("pageLoadTimeOut not specified in the config.properties file or in environment variables.");
	}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else if(System.getenv().get("url") != null)
			return System.getenv().get("url");
		else
			throw new RuntimeException("Url not specified in the config.properties file.");
	}

}
