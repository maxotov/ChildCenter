package kz.enu.fit.web.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.CenterDAO;
import kz.enu.fit.entities.Center;
import kz.enu.fit.entities.Comment;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.web.command.ActionCommand;

public class ShowCenter implements ActionCommand {

    public static final String ID_NEWS = "id_center";
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id_news = request.getParameter(ID_NEWS);
        int id_data = Integer.parseInt(id_news);
            CenterDAO dao = new CenterDAO();
            Center center = dao.findCurrentCenter(id_data);
            float temp = center.getRating()/center.getVote();
            int rat = Math.round(temp);
            List<Comment> comments = dao.findCommentsCenter(id_data);
            if(!comments.isEmpty()){
                request.setAttribute("comments", comments);
            }
            request.setAttribute("center", center);
            request.setAttribute("r", rat);
            page = "/jsp/show_center.jsp";
        
        return page;
    }
    
}
