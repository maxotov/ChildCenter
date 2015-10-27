package kz.enu.fit.web.command.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.ForumDAO;
import kz.enu.fit.entities.Forum;
import kz.enu.fit.web.command.ActionCommand;

public class AddForumCommand implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id_user = request.getParameter("id_user");
        String title = request.getParameter("title");
        String date_create = request.getParameter("date_create");
        ForumDAO forumDAO = new ForumDAO();
        if(title!=null && checkDate(date_create)){
            if(title.isEmpty()){
                request.setAttribute("message", "Сіз қате жаздыңыз!");
            }else{                
                try {
                    if(forumDAO.insertForum(Integer.parseInt(id_user), title, date_create)){
                        request.setAttribute("message", "Сіздің тақырып сәтті қосылды!");
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AddForumCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        int p = 1;
        int recordsPerPage = 5;
        List<Forum> forums = forumDAO.viewAllForums((p - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = forumDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("forums", forums);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", p);
        page = "/jsp/forum.jsp";
        return page;    
    }
    
    private boolean checkDate(String text) {
        boolean flag = false;
        Pattern patternNumber = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        Matcher matchArr = patternNumber.matcher(text);
        if(matchArr.matches()){
            flag = true;
        }
        return flag;
    }
    
}
