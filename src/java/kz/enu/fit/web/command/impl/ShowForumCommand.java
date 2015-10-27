package kz.enu.fit.web.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.ForumDAO;
import kz.enu.fit.entities.Forum;
import kz.enu.fit.entities.ForumAnswer;
import kz.enu.fit.web.command.ActionCommand;

public class ShowForumCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id_forum = request.getParameter("id_forum");
        int id_data = Integer.parseInt(id_forum);
        ForumDAO dao = new ForumDAO();
        Forum forum = dao.findForumById(id_data);
        List<ForumAnswer> answers = dao.findAnswersByForum(id_data);
        request.setAttribute("answers", answers);
        request.setAttribute("forum", forum);
        page = "/jsp/show_forum.jsp";
        return page;
    }
}
