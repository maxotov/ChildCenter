package kz.enu.fit.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.log.Log;
import kz.enu.fit.logic.RegisterLogic;
import kz.enu.fit.logic.exceptions.IncorrectEmailException;
import kz.enu.fit.logic.exceptions.IncorrectInfoClient;
import kz.enu.fit.logic.exceptions.IncorrectLogin;
import kz.enu.fit.logic.exceptions.IncorrectLoginException;
import kz.enu.fit.logic.exceptions.IncorrectRegister;
import kz.enu.fit.messages.MessageManager;
import kz.enu.fit.web.command.ActionCommand;

public class Registration implements ActionCommand {
    private static Log logger = new Log();
    /**
     * receives from request returns page
     * @param request
     * @return 
     */
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
		// extraction of login and password
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String pass = req.getParameter("password");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                // check your login and password
                
                logger.info(firstname);
		try{
                        RegisterLogic register = new RegisterLogic();
                        register.checkLoginRegister(email);
                        register.checkLoginPassword(firstname, pass);
                        register.checkEmail(phone, email);
                        register.registerClient(firstname, lastname, pass, phone, email);
                        req.setAttribute("success", "Сіз сәтті тіркелдіңіз! Сіздің электронды адреске хат жолданды.");
			page = ConfigurationManager.getProperty("path.page.registration");
		} catch(IncorrectInfoClient ex) {
			req.setAttribute("error", MessageManager.getProperty("message.registererror"));
			page = ConfigurationManager.getProperty("path.page.registration");
		}catch(IncorrectRegister ex){
                        req.setAttribute("error", MessageManager.getProperty("message.register"));
			page = ConfigurationManager.getProperty("path.page.registration");
                }catch(IncorrectLoginException ex){
                        req.setAttribute("error", MessageManager.getProperty("message.symbol"));
			page = ConfigurationManager.getProperty("path.page.registration");
                }catch(IncorrectLogin ex){
                        req.setAttribute("error", MessageManager.getProperty("message.isletter"));
			page = ConfigurationManager.getProperty("path.page.registration");
                }catch(IncorrectEmailException ex){
                        req.setAttribute("error", MessageManager.getProperty("message.emailIncorrect"));
			page = ConfigurationManager.getProperty("path.page.registration");
                }
		return page;
    }
}
