package com.iod.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

	public final String propertyFilePath = ".\\resources\\config.properties";
	public static Properties prop;

	public PropertyFileReader() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(propertyFilePath));
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}
