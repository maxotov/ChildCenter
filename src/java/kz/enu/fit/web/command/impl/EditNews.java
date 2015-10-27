package kz.enu.fit.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.entities.News;
import kz.enu.fit.logic.NewsLogic;

public class EditNews implements ActionCommand {
    
    public static final String PARAM_NAME_ID = "id";

    @Override
    public String execute(HttpServletRequest request) {
        String page =null;
        String id = request.getParameter(PARAM_NAME_ID);
        
        int id_news = Integer.parseInt(id);
            NewsLogic logic = new NewsLogic();
            News news = logic.findNews(id_news);
            request.setAttribute("data", news);
            page = "/jsp/cur_news.jsp";

        
        return page;
    }
    
}
