package kz.enu.fit.actions;

import org.apache.catalina.util.Base64;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.enu.fit.dao.NewsDAO;
import kz.enu.fit.entities.News;

public class Pagination extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                int page = 1;
		int recordsPerPage = 5;
		if(request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		NewsDAO dao = new NewsDAO();
		List<News> list = dao.viewAllNews((page-1)*recordsPerPage, recordsPerPage);
                for(News n: list){
                    String encodedImage = Base64.encode(n.getImage());
                    n.setEncodedImage(encodedImage);
                }
		int noOfRecords = dao.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		request.setAttribute("data", list);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		RequestDispatcher view = request.getRequestDispatcher("/jsp/news.jsp");
		view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
