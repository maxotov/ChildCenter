package kz.enu.fit.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.NewsDAO;
import kz.enu.fit.entities.News;
import kz.enu.fit.logic.exceptions.EmptyOrderException;
import kz.enu.fit.web.command.ActionCommand;
import kz.enu.fit.logic.NewsLogic;

public class SaveNews implements ActionCommand {
    
    private static final String PARAM_NAME_ID = "id";
    private static final String PARAM_NAME_TITLE = "title";
    private static final String PARAM_NAME_DESCRIPTION = "description";
    private static final String PARAM_NAME_TEXT = "text";
    private static final String PARAM_NAME_DATE = "date";
    
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
		String id = req.getParameter(PARAM_NAME_ID);
		String title = req.getParameter(PARAM_NAME_TITLE);
		String desc = req.getParameter(PARAM_NAME_DESCRIPTION);
                String text = req.getParameter(PARAM_NAME_TEXT);
                String date = req.getParameter(PARAM_NAME_DATE);
                
                int id_news = Integer.parseInt(id);
                NewsDAO dao = new NewsDAO();
                News news = null;
		try{
                        NewsLogic data = new NewsLogic();
                        data.update(desc, text, date, title, id_news);
                        news = dao.findCurrentNews(id_news);
			req.setAttribute("error", "Сәтті өзгертілді!");
                        req.setAttribute("data", news);
			page = "/jsp/cur_news.jsp";
		} catch(EmptyOrderException ex) {
			req.setAttribute("error", "Қателік");
                        news = dao.findCurrentNews(id_news);
                        req.setAttribute("data", news);
			page = "/jsp/cur_news.jsp";
		}
 		return page;
    }
    
}
