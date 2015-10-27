package kz.enu.fit.web.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.logic.exceptions.EmptyOrderException;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.entities.Comment;
import kz.enu.fit.logic.CommentLogic;

public class DeleteComment implements ActionCommand {

    public static final String PARAM_NAME_ID = "id";
  
    @Override
    public String execute(HttpServletRequest request) {
        String page =null;
        String id = request.getParameter(PARAM_NAME_ID);
        try{
            int id_client = Integer.parseInt(id);
            CommentLogic otzivlogic = new CommentLogic();
            otzivlogic.deleteComment(id_client);
            List<Comment> otzivs = otzivlogic.list();
            request.setAttribute("otzivs", otzivs);
            page = "/jsp/list_comment.jsp";

        }catch(EmptyOrderException ex){
            request.setAttribute("error", "Қателік!");
            page = "/jsp/list_comment.jsp";
        }
        
        return page;
   }
    
}
