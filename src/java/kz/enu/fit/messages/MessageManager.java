package kz.enu.fit.messages;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * class retrieves the information from the file messages. properties
 * @author Aibol
 */
public class MessageManager {
	private MessageManager() { }
	public static String getProperty(String key) {
            ResourceBundle resourceBundle =
                ResourceBundle.getBundle("resources.messages", Locale.getDefault());
		return resourceBundle.getString(key);
	}
}
