package kz.enu.fit.web.command.impl;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.web.command.ActionCommand;

public class ChangeLocale implements ActionCommand {
    
    public static final String PARAM_NAME_LANGUAGE = "language";
    public static final String ATTRIBUTE_LANG ="javax.servlet.jsp.jstl.fmt.locale.session";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String lang = request.getParameter(PARAM_NAME_LANGUAGE);
        page = ConfigurationManager.getProperty("path.page.about");
        if (lang != null) {
            request.getSession().setAttribute(ATTRIBUTE_LANG, lang);
            Locale.setDefault(new Locale(lang));
        }
        return page;  
    }
}
