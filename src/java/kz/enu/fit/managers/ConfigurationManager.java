package kz.enu.fit.managers;

import java.util.ResourceBundle;
/**
 * class retrieves the information from the file config.properties
 * @author Aibol
 */
public class ConfigurationManager {
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.config");
	private ConfigurationManager(){} 
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
