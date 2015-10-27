package kz.enu.fit.web.command.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.ForumDAO;
import kz.enu.fit.entities.Forum;
import kz.enu.fit.entities.ForumAnswer;
import kz.enu.fit.web.command.ActionCommand;

public class AddForumAnswerCommand implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id_user = request.getParameter("id_user");
        String id_forum = request.getParameter("id_forum");
        String answer = request.getParameter("answer");
        ForumDAO forumDAO = new ForumDAO();
        if(answer.trim()!=null){
            if(answer.trim().isEmpty()){
                request.setAttribute("message", "Сіз мәнді қате бердіңіз!");
            }else{                
                try {
                    if(forumDAO.insertForumAnswer(Integer.parseInt(id_user), Integer.parseInt(id_forum), answer)){
                        request.setAttribute("message", "Сіздің жауап сәтті қосылды!");
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AddForumCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        Forum forum = forumDAO.findForumById(Integer.parseInt(id_forum));
        List<ForumAnswer> answers = forumDAO.findAnswersByForum(Integer.parseInt(id_forum));
        request.setAttribute("answers", answers);
        request.setAttribute("forum", forum);
        page = "/jsp/show_forum.jsp";
        return page;    
    }
}
