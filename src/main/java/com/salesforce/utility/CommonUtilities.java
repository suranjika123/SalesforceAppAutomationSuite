package com.salesforce.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.salesforce.utility.Constants.APPLICATION_PROPERTIES_PATH;

public class CommonUtilities {

	public static String getApplicationProperty(String Key) {

		Properties properties = new Properties();
		String filePath = APPLICATION_PROPERTIES_PATH;

		FileInputStream inputfile = null;
		try {
			inputfile = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String value = null;

		try {
			try {
				properties.load(inputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			value = properties.getProperty(Key);
		} finally {
			try {
				inputfile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
}
