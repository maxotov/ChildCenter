package kz.enu.fit.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.logic.exceptions.EmptyOrderException;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.messages.MessageManager;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.logic.NewsLogic;

public class AddNews implements ActionCommand {

    private static final String PARAM_NAME_TITLE = "title";
    private static final String PARAM_NAME_DESCRIPTION = "description";
    private static final String PARAM_NAME_TEXT = "text";
    private static final String PARAM_NAME_DATE = "date";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        String title = req.getParameter(PARAM_NAME_TITLE);
        String desc = req.getParameter(PARAM_NAME_DESCRIPTION);
        String text = req.getParameter(PARAM_NAME_TEXT);
        String date = req.getParameter(PARAM_NAME_DATE);

//        try {
//            NewsLogic data = new NewsLogic();
//            data.accept(desc, text, date, title);
//
//            req.setAttribute("success", "Успешно добавлено!");
//            page = ConfigurationManager.getProperty("path.page.addnews");
//        } catch (EmptyOrderException ex) {
//            req.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.registererror"));
//            page = ConfigurationManager.getProperty("path.page.registration");
//        }
        return page;
    }
}
