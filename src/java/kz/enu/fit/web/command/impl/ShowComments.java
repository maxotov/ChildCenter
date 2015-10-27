package kz.enu.fit.web.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.NewsDAO;
import kz.enu.fit.entities.Commentary;
import kz.enu.fit.web.command.ActionCommand;

public class ShowComments implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
      String page = null;
  
            NewsDAO dao = new NewsDAO();
           
            List<Commentary> otzivs = null;
            otzivs = dao.findAllCommentary();
           request.setAttribute("comments", otzivs);
            page = "/jsp/list_commentary.jsp";
       
        return page;   
    }
}
