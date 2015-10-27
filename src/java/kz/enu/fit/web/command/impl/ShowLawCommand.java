package kz.enu.fit.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.LawDAO;
import kz.enu.fit.entities.Law;
import kz.enu.fit.web.command.ActionCommand;

public class ShowLawCommand implements ActionCommand {

    public static final String ID_ADVICE = "id";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id_news = request.getParameter(ID_ADVICE);
        int id_data = Integer.parseInt(id_news);
        LawDAO dao = new LawDAO();
        Law advice = dao.findLawById(id_data);
        request.setAttribute("law", advice);
        page = "/jsp/show_law.jsp";
        return page;
    }
}
