package kz.enu.fit.actions;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import kz.enu.fit.dao.NewsDAO;

//@WebServlet("/AddNewsServlet")
@MultipartConfig(maxFileSize = 16177215)
public class AddNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String desc = request.getParameter("description");
        String text = request.getParameter("text");
        String author = request.getParameter("author");
        NewsDAO dao = new NewsDAO();
        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("filename");
        if (!title.equals("") && !date.equals("") && !desc.equals("") && !text.equals("") && !author.equals("")) {
            if (filePart != null) {
                // prints out some information for debugging
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());

                // obtains input stream of the upload file
                inputStream = filePart.getInputStream();
            }
            if (dao.acceptData(desc, text, date, title, author, inputStream)) {
                request.setAttribute("success", "сәтті қосылды!");
            }
        } else {
            request.setAttribute("success", "қателік!");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/add_news.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
