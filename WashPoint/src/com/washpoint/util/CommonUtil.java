package com.washpoint.util;
import java.io.IOException;
import java.util.Properties;

/**
 * This is the common utility class to load all property details at once when it
 * is initializing .
 * 
 * @author W.G. YASIRU RANDIKA IT19131184
 */
public class CommonUtil {

	public static final Properties properties = new Properties();

	static {
		try {	
			// Read the property only once when load the class
			properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
