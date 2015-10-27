package kz.enu.fit.managers;

import java.util.ResourceBundle;

public class DBConfigurationManager {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.dbconfig");
	// класс извлекает информацию из файла dbconfig.properties
	private DBConfigurationManager(){} 
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
