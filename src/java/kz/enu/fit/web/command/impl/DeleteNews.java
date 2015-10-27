package kz.enu.fit.web.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.logic.exceptions.EmptyOrderException;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.entities.News;
import kz.enu.fit.logic.NewsLogic;

public class DeleteNews implements ActionCommand {

    public static final String PARAM_NAME_ID = "id";
  
    @Override
    public String execute(HttpServletRequest request) {
        String page =null;
        String id = request.getParameter(PARAM_NAME_ID);
        try{
        int id_client = Integer.parseInt(id);
            NewsLogic logic = new NewsLogic();
            logic.deleteNews(id_client);
            List<News> otzivs = logic.list();
            request.setAttribute("data", otzivs);
            page = "/jsp/list_news.jsp";

        }catch(EmptyOrderException ex){
            request.setAttribute("error", "Қателік!");
            page = "/jsp/list_news.jsp";
        }
        
        return page;
   }
    
}
