package kz.enu.fit.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.web.command.ActionCommand;

public class NoCommand implements ActionCommand {
        /**
         * Returns page
         * @param request
         * @return 
         */
        @Override
	public String execute(HttpServletRequest request) {
		/*
		 * in the event of an error or a direct appeal to the controller redirects to
		 * the login page
		 */
		String page ="index.jsp";
		return page;
	}
}
