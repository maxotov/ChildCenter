package kz.enu.fit.web.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.entities.News;
import kz.enu.fit.logic.NewsLogic;

public class ShowListNews implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
      String page = null;
  
            NewsLogic logic = new NewsLogic();
           
            List<News> datas = null;
            datas = logic.list();
            request.setAttribute("data", datas);
            page = "/jsp/list_news.jsp";
       
        return page;   
    }
}
