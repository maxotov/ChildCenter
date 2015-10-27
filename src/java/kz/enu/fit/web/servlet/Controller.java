package kz.enu.fit.web.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.web.command.ActionFactory;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.messages.MessageManager;
import static kz.enu.fit.web.command.Constants.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

//@WebServlet(name = "controller", urlPatterns = {"/Contr"})
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String page = null;

        // command definition that came from the JSP
        ActionCommand command = ActionFactory.getCommand(request);

        /*
         * call to implement the execute () and parameter passing
         * class-specific command handler
         */
        page = command.execute(request);

        // method returns the response page
        //page = null;
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            //call to respond to the request page
            dispatcher.forward(request, response);
        } else {
            //Set the page c Error messages
            page = ConfigurationManager.getProperty("path.page.index");
            getServletContext().setAttribute(NULL_PAGE, MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);//Create a new query, 
            //all parameters stored in the old deleted			

        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String log4jLocation = config.getInitParameter("log4j-properties-location");

        ServletContext sc = config.getServletContext();

        if (log4jLocation == null) {
            System.err.println("No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        } else {
            String webAppPath = sc.getRealPath("/");
            String log4jProp = webAppPath + log4jLocation;
            File file = new File(log4jProp);
            if (file.exists()) {
                System.out.println("Initializing log4j with: " + log4jProp);
                PropertyConfigurator.configure(log4jProp);
            } else {
                System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
                BasicConfigurator.configure();
            }
        }
        super.init(config);
    }
}
