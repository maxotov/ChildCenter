package kz.enu.fit.web.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.enu.fit.entities.User;
import kz.enu.fit.log.Log;
import kz.enu.fit.logic.UserLogic;
import kz.enu.fit.logic.exceptions.EmptyListException;
import kz.enu.fit.logic.exceptions.IncorrectPasswordException;
import kz.enu.fit.logic.exceptions.NoSuchUserException;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.messages.MessageManager;
import kz.enu.fit.web.command.ActionCommand;
import static kz.enu.fit.web.command.Constants.*;

public class LoginCommand implements ActionCommand {
    private static Log log = new Log();
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    
    /**
     * Returns page
     * @param request
     * @return 
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        // extraction of login and password
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        // check your login and password
        HttpSession session = request.getSession();
        log.info("login = " + login);
        log.info("password = " + pass);
        try { 
            UserLogic userLogic = new UserLogic();
            User user = userLogic.checkLogin(login, pass);
            log.info("status = " + user.getStatus().toString());
                switch (user.getStatus()) {
                    case ADMIN:
                        session.setAttribute(USER, user.getFisrtname());
                        session.setAttribute(ID, user.getId());
                        session.setAttribute("email", user.getEmail());
                        page = ConfigurationManager.getProperty("path.page.about");
                        break;
                    case CLIENT:
                        session.setAttribute(USER, user.getFisrtname());
                        session.setAttribute(ID, user.getId());
                        session.setAttribute("email", user.getEmail());
                        page = ConfigurationManager.getProperty("path.page.about");
                        break;
                }
        } catch (NoSuchUserException ex) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginuser"));
            log.error("User no!");
            page = ConfigurationManager.getProperty("path.page.about");
        } catch (IncorrectPasswordException ex) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            log.error("Password incorrect");
            page = ConfigurationManager.getProperty("path.page.about");
        }
        return page;
    }
}
