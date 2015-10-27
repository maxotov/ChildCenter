package kz.enu.fit.web.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.ForumDAO;
import kz.enu.fit.entities.Forum;
import kz.enu.fit.entities.ForumAnswer;
import kz.enu.fit.web.command.ActionCommand;

public class UpdateForumStatusCommand implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id_forum = request.getParameter("id_forum");
        String status = request.getParameter("status");
        int statusInt = Integer.parseInt(status);
        ForumDAO forumDAO = new ForumDAO();
        if(forumDAO.updateForumStatus(statusInt, Integer.parseInt(id_forum))) {
            if(statusInt==0) {request.setAttribute("message", "Тақырып жабылды!");}
            else { request.setAttribute("message", "Тақырып ашылды!");}
        }
        Forum forum = forumDAO.findForumById(Integer.parseInt(id_forum));
        List<ForumAnswer> answers = forumDAO.findAnswersByForum(Integer.parseInt(id_forum));
        request.setAttribute("answers", answers);
        request.setAttribute("forum", forum);
        page = "/jsp/show_forum.jsp";
        return page;    
    }
}
