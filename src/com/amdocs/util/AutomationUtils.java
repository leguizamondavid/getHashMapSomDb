package com.amdocs.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AutomationUtils {
	
    public static Properties loadProperty(String propertyFilePath){
	String mopProperties = FileHandler.getFilePath(propertyFilePath);
	Properties prop = new Properties();
	InputStream input;
	try {
		input = new FileInputStream(mopProperties);
	    prop.load(input);
	    return prop;
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
    }
}
