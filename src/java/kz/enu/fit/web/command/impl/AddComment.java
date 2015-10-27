package kz.enu.fit.web.command.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.CenterDAO;
import kz.enu.fit.dao.CommentDAO;
import kz.enu.fit.entities.Center;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.entities.Comment;

public class AddComment implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String center_id = request.getParameter("center_id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String text = request.getParameter("text");
        
        if(name!=null && email!=null && text != null){
            if(name.isEmpty() || email.isEmpty() || text.isEmpty() || !checkEmail(email)){
                request.setAttribute("errorComment", "Сіз мәнді қате бердіңіз!");
            } else {
                CommentDAO commentDAO = new CommentDAO();        
                commentDAO.acceptComment(name, text, Integer.parseInt(center_id), email);
                request.setAttribute("errorComment", "Сіздің пікір сәтті қосылды!");
            }
        }        
        CenterDAO dao = new CenterDAO();
        Center center = dao.findCurrentCenter(Integer.parseInt(center_id));
        float temp = center.getRating() / center.getVote();
        int rat = Math.round(temp);
        List<Comment> comments = dao.findCommentsCenter(Integer.parseInt(center_id));
        if (!comments.isEmpty()) {
            request.setAttribute("comments", comments);
        }
        request.setAttribute("center", center);
        request.setAttribute("r", rat);
        page = "/jsp/show_center.jsp";
        return page;
    }
    
    private boolean checkEmail(String email) {
        boolean flag = false;
         Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matchEmail = patternEmail.matcher(email);
        if(matchEmail.matches()){
            flag = true;
        }
        return flag;
    }
    
}
