package kz.enu.fit.web.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
	public String execute(HttpServletRequest request);
}
