package kz.enu.fit.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.web.command.ActionCommand;
import static kz.enu.fit.web.command.Constants.*;

public class LogoutCommand implements ActionCommand {
    /**
     * Returns page
     * @param request
     * @return 
     */
    @Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		
		request.getSession().removeAttribute(USER);
                request.getSession().removeAttribute(ID);
                request.getSession().removeAttribute("email");
		return page;
	}
}
