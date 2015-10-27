package kz.enu.fit.web.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.entities.Comment;
import kz.enu.fit.logic.CommentLogic;

public class ShowListComment implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        CommentLogic otziv = new CommentLogic();
        List<Comment> otzivs = null;
        otzivs = otziv.list();
        request.setAttribute("otzivs", otzivs);
        page = "/jsp/list_comment.jsp";
        return page;
    }
}
