package com.salesforce.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CommonUtilities {
	public static String getProperty(String key)  {
		
		File file = new File(Constants.SALESFORCE_PROPERTIES_PATH);
		FileReader fr=null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties pf = new Properties();
		try {
			pf.load(fr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = pf.getProperty(key);
		return value;
		
	}
}
