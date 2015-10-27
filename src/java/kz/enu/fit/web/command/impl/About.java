package kz.enu.fit.web.command.impl;

import org.apache.catalina.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.enu.fit.dao.AdviceDAO;
import kz.enu.fit.dao.CenterDAO;
import kz.enu.fit.dao.ForumDAO;
import kz.enu.fit.dao.LawDAO;
import kz.enu.fit.dao.NewsDAO;
import kz.enu.fit.dao.UserDAO;
import kz.enu.fit.entities.Advice;
import kz.enu.fit.entities.Center;
import kz.enu.fit.entities.Comment;
import kz.enu.fit.entities.Forum;
import kz.enu.fit.entities.Law;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.entities.News;
import kz.enu.fit.entities.User;

public class About implements ActionCommand {

    public static final String PARAM_NAME_ABOUT = "cur_page";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        // extraction of login and password
        String cur_page = request.getParameter(PARAM_NAME_ABOUT);
        if (cur_page.equals("register")) {
            page = ConfigurationManager.getProperty("path.page.registration");
        }
        if (cur_page.equals("home")) {
            page = ConfigurationManager.getProperty("path.page.login");
        }
        if (cur_page.equals("addnews")) {
            page = ConfigurationManager.getProperty("path.page.addnews");
        }
        if (cur_page.equals("about")) {

            page = ConfigurationManager.getProperty("path.page.about");
        }
        if (cur_page.equals("product")) {
            //request.setAttribute("product", "Дополнительные услуги");
            page = ConfigurationManager.getProperty("path.page.product");
        }
        if (cur_page.equals("news")) {
            int p = 1;
            int recordsPerPage = 5;
            NewsDAO dao = new NewsDAO();
            List<News> list = dao.viewAllNews((p - 1) * recordsPerPage, recordsPerPage);
            for (News n : list) {
                String encodedImage = Base64.encode(n.getImage());
                n.setEncodedImage(encodedImage);
            }
            int noOfRecords = dao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("data", list);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", p);
            page = ConfigurationManager.getProperty("path.page.news");
        }
        if (cur_page.equals("advert")) {
            page = ConfigurationManager.getProperty("path.page.advert");
        }
        if (cur_page.equals("contact")) {
            page = ConfigurationManager.getProperty("path.page.contact");
        }
        if (cur_page.equals("add_center")) {
            page = "/jsp/add_center.jsp";
        }
        if (cur_page.equals("cabinet")) {
            HttpSession session = request.getSession();
            String id_user = session.getAttribute("id").toString();
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findUserById(Integer.parseInt(id_user));
            if (user.getStatus().toString().equals("CLIENT")) {
                CenterDAO centerDAO = new CenterDAO();
                List<Center> centersOfUser = centerDAO.findCentersUser(Integer.parseInt(id_user));
                for (Center c : centersOfUser) {
                    List<Comment> commentsOfCenter = centerDAO.findCommentsCenter(c.getId().intValue());
                    c.setComments(commentsOfCenter);
                }
                request.setAttribute("centers", centersOfUser);
                page = "/jsp/cabinet.jsp";
            } else {
                CenterDAO centerDAO = new CenterDAO();
                List<Center> centersOfUser = centerDAO.findCentersUser(Integer.parseInt(id_user));
                for (Center c : centersOfUser) {
                    List<Comment> commentsOfCenter = centerDAO.findCommentsCenter(c.getId().intValue());
                    c.setComments(commentsOfCenter);
                }
                request.setAttribute("centers", centersOfUser);
                List<User> clients = userDAO.findAll();
                request.setAttribute("clients", clients);
                page = "/jsp/clients.jsp";
            }

        }
        if (cur_page.equals("forum")) {
            int p = 1;
            int recordsPerPage = 5;
            ForumDAO forumDAO = new ForumDAO();
            List<Forum> forums = forumDAO.viewAllForums((p - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = forumDAO.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("forums", forums);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", p);
            page = "/jsp/forum.jsp";
        }
        if (cur_page.equals("quiz")) {
            page = "/jsp/quiz.jsp";
        }
        if (cur_page.equals("advice")) {
            AdviceDAO adviceDAO = new AdviceDAO();
            List<Advice> advices = adviceDAO.findAll();
            request.setAttribute("advices", advices);
            page = "/jsp/advice.jsp";
        }
        if (cur_page.equals("law")) {
            LawDAO adviceDAO = new LawDAO();
            List<Law> advices = adviceDAO.findAll();
            request.setAttribute("laws", advices);
            page = "/jsp/law.jsp";
        }
        return page;
    }
}
