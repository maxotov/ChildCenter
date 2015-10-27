package kz.enu.fit.web.command;

import java.io.UnsupportedEncodingException;
import kz.enu.fit.web.command.impl.NoCommand;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.messages.MessageManager;
import static kz.enu.fit.web.command.Constants.*;

public class ActionFactory {
      /**
       * receives from the client request and returns the command
       * @return command
       */
	public static ActionCommand getCommand(HttpServletRequest request) throws UnsupportedEncodingException {
		ActionCommand current = new NoCommand();		
		request.setCharacterEncoding("UTF-8");
                String action = request.getParameter(COMMAND);
		if (action == null || action.isEmpty()) {
			
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute(WRONG_ACTION, action + MessageManager.getProperty("message.wrongaction"));
		}		
		return current;		
	}
        
}
