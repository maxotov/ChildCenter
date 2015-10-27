package kz.enu.fit.web.command.impl;

import org.apache.catalina.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.NewsDAO;
import kz.enu.fit.entities.Commentary;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.entities.News;
import kz.enu.fit.logic.NewsLogic;

public class ShowNews implements ActionCommand {

    public static final String ID_NEWS = "id_news";
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id_news = request.getParameter(ID_NEWS);
        int id_data = Integer.parseInt(id_news);
            NewsDAO dao = new NewsDAO();
            NewsLogic data = new NewsLogic();
            News cur_data = data.findNews(id_data);
            String encodedImage = Base64.encode(cur_data.getImage());
            cur_data.setEncodedImage(encodedImage);
            List<Commentary> comments = dao.findCommentsByNews(id_data);
            if(!comments.isEmpty()){
                request.setAttribute("comments", comments);
            }
            request.setAttribute("data", cur_data);
            page = ConfigurationManager.getProperty("path.page.data");
        
        return page;
    }
    
}
