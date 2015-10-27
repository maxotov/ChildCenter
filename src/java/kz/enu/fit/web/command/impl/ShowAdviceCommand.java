package kz.enu.fit.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.AdviceDAO;
import kz.enu.fit.entities.Advice;
import kz.enu.fit.web.command.ActionCommand;

public class ShowAdviceCommand implements ActionCommand {

    public static final String ID_ADVICE = "id";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id_news = request.getParameter(ID_ADVICE);
        int id_data = Integer.parseInt(id_news);
        AdviceDAO dao = new AdviceDAO();
        Advice advice = dao.findAdviceById(id_data);
        request.setAttribute("advice", advice);
        page = "/jsp/show_advice.jsp";
        return page;
    }
}
