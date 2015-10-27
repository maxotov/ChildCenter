package kz.enu.fit.actions;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import kz.enu.fit.dao.CenterDAO;
import kz.enu.fit.dao.ForumDAO;
import kz.enu.fit.dao.NewsDAO;
import kz.enu.fit.entities.Center;
import kz.enu.fit.entities.Comment;
import kz.enu.fit.entities.Commentary;
import kz.enu.fit.entities.Forum;
import kz.enu.fit.entities.News;
import kz.enu.fit.logic.CommentLogic;
import kz.enu.fit.logic.exceptions.EmptyOrderException;
import kz.enu.fit.logic.exceptions.IncorrectLogin;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.messages.MessageManager;
import org.apache.catalina.util.Base64;

//@WebServlet("/Control")
@MultipartConfig(maxFileSize = 16177215)
public class Control extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action.equals("viewnews")){
            viewNews(request, response);
        }
        if(action.equals("viewcenter")){
            viewCenter(request, response);
        }
        if(action.equals("incrementlike")){
            incrementLike(request, response);
        }
        if(action.equals("decrementlike")){
            decrementLike(request, response);
        }
        if(action.equals("otziv")){
            addCommentCommand(request, response);
        }
        if(action.equals("comment")){
            addCommentaryNews(request, response);
        }
        if(action.equals("delcomment")){
            deleteCommentaryNews(request, response);
        }
        if(action.equals("publish")){
            publishCommentaryNews(request, response);
        }
        if(action.equals("init_centers")){
            initCenters(request, response);
        }
        if(action.equals("scrollpagination")){
            scrollPagination(request, response);
        }
        if(action.equals("filter")){
            filterCenters(request, response);
        }
        if(action.equals("vote")){
            ratingVote(request, response);
        }
        if(action.equals("editcenter")){
            editCenterAction(request, response);
        }
        if(action.equals("latest_news")){
            latestNews(request, response);
        }
        if(action.equals("latest_forums")){
            latestForums(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void viewNews(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        NewsDAO dao = new NewsDAO();
        int oldView = dao.catchViewOfNews(Integer.parseInt(id));
        int newView = oldView + 1;
        if(dao.updateViewOfNews(Integer.parseInt(id), newView)){
            PrintWriter out = response.getWriter();
            out.print(newView);
            out.close();
        }
        
    }
    
    private void viewCenter(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        CenterDAO dao = new CenterDAO();
        int oldView = dao.catchViewOfCenter(Integer.parseInt(id));
        int newView = oldView + 1;
        if(dao.updateViewOfCenter(Integer.parseInt(id), newView)){
            PrintWriter out = response.getWriter();
            out.print(newView);
            out.close();
        }
        
    }
    
    private void incrementLike(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        NewsDAO dao = new NewsDAO();
        int oldView = dao.catchLikeOfNews(Integer.parseInt(id));
        int newView = oldView + 1;
        if(dao.updateLikeOfNews(Integer.parseInt(id), newView)){
            PrintWriter out = response.getWriter();
            out.print(newView);
            out.close();
        }
        
    }
    
    private void decrementLike(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        NewsDAO dao = new NewsDAO();
        int oldView = dao.catchLikeOfNews(Integer.parseInt(id));
       
        int newView = oldView - 1;
        if(dao.updateLikeOfNews(Integer.parseInt(id), newView)){
            PrintWriter out = response.getWriter();
            out.print(newView);
            out.close();
        }
        
    }
    
    private void addCommentCommand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        String name = request.getParameter("name");
        String text = request.getParameter("text");
        String path = "";
        String page = "";
        String imgPage ="";
        Part filePart = request.getPart("filename");
        if(filePart != null && filePart.getSize()!=0){
            String projectDir = getServletContext().getRealPath(File.separator);
            projectDir = projectDir.replace("web", "images");
            projectDir = projectDir.replace("build", "web");
            System.out.println(projectDir);
            path = projectDir + File.separator + extractFileName(filePart);
            filePart.write(path);
            int start = path.indexOf("images");
            path = path.substring(start);
            String[] parts = path.split("\\\\");
            imgPage = parts[0] + "/" + parts[2];
            
        }
        CommentLogic otzivLogic = new CommentLogic();
        List<Comment> otzivs = null;
        try {
            otzivLogic.accept(name, text, imgPage);
            otzivs = otzivLogic.list();
            request.setAttribute("otzivs", otzivs);
            page = ConfigurationManager.getProperty("path.page.otziv");
        } catch(EmptyOrderException ex){
           request.setAttribute("errorCommentMessage", MessageManager.getProperty("message.registererror"));
           otzivs = otzivLogic.list();
           request.setAttribute("otzivs", otzivs);
           page = ConfigurationManager.getProperty("path.page.otziv");
        } catch(IncorrectLogin ex){
           request.setAttribute("errorCommentMessage", MessageManager.getProperty("message.symbol"));
           otzivs = otzivLogic.list();
           request.setAttribute("otzivs", otzivs);
           page = ConfigurationManager.getProperty("path.page.otziv");
        }
            
        RequestDispatcher dis = getServletContext().getRequestDispatcher(page);
        dis.forward(request, response);
        
    }
    
     private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
     
     private void addCommentaryNews(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String data_id = request.getParameter("data_id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String text = request.getParameter("text");
        NewsDAO dao = new NewsDAO();
        List<Commentary> otzivs = new ArrayList<Commentary>();
        if(!name.isEmpty() && !email.isEmpty() && checkEmail(email) && !text.isEmpty()){
            dao.addCommentary(name, email, text, Integer.parseInt(data_id));
            News news = dao.findCurrentNews(Integer.parseInt(data_id));
            String encodedImage = Base64.encode(news.getImage());
            news.setEncodedImage(encodedImage);
            List<Commentary> comments = dao.findCommentsByNews(Integer.parseInt(data_id));
            if(!comments.isEmpty()){
                request.setAttribute("comments", comments);
            }
            request.setAttribute("data", news);
            request.setAttribute("errorComment", "Пікір үшін рахмет! Сіздің пікір тексерілуде!");
        } else {
            request.setAttribute("errorComment", "Қателік!");
            News news = dao.findCurrentNews(Integer.parseInt(data_id));
            String encodedImage = Base64.encode(news.getImage());
            news.setEncodedImage(encodedImage);
            List<Commentary> comments = dao.findCommentsByNews(Integer.parseInt(data_id));
            if(!comments.isEmpty()){
                request.setAttribute("comments", comments);
            }
            request.setAttribute("data", news);
        }
            
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/jsp/show_news.jsp");
        dis.forward(request, response);
        
    }     
     
     private void deleteCommentaryNews(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String id = request.getParameter("id");
        
        NewsDAO dao = new NewsDAO();
           if(dao.deleteCommentary(Integer.parseInt(id))){
               request.setAttribute("error", "Жойылды!");
           }
            List<Commentary> otzivs = null;
            otzivs = dao.findAllCommentary();
           request.setAttribute("comments", otzivs);
            String page = "/jsp/list_commentary.jsp";
            
        RequestDispatcher dis = getServletContext().getRequestDispatcher(page);
        dis.forward(request, response);
        
    }
     
     private void publishCommentaryNews(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String id = request.getParameter("id");
        
        NewsDAO dao = new NewsDAO();
           if(dao.publishCommentary(Integer.parseInt(id))){
               request.setAttribute("error", "Жарияланды!");
           }
            List<Commentary> otzivs = null;
            otzivs = dao.findAllCommentary();
           request.setAttribute("comments", otzivs);
            String page = "/jsp/list_commentary.jsp";
            
        RequestDispatcher dis = getServletContext().getRequestDispatcher(page);
        dis.forward(request, response);
        
    }
     
     public boolean checkEmail(String email) {
        boolean flag = false;
         Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matchEmail = patternEmail.matcher(email);
        if(matchEmail.matches()){
            flag = true;
        }
        return flag;
    }

    private void initCenters(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html"); 
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        CenterDAO centerDAO = new CenterDAO();
        List<Center> centers = centerDAO.findAll();
        JsonElement contactObj = gson.toJsonTree(centers);
        if(centers.isEmpty()){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("centers", contactObj);
        out.println(myObj.toString()); 
        out.close();         
    }
    
    private void scrollPagination(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String number = request.getParameter("number");
        String offs = request.getParameter("offset");
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        CenterDAO centerDAO = new CenterDAO();
        List<Center> centers = centerDAO.scrollPag(Integer.parseInt(offs), Integer.parseInt(number));
        JsonElement contactObj = gson.toJsonTree(centers);
        if(centers.isEmpty()){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("centers", contactObj);
        out.println(myObj.toString()); 
        out.close();         
    }
    
    private void filterCenters(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String items = request.getParameter("items");
        String address = request.getParameter("address");
        String custom_query = "select * from centers where (1=1)";
        if(!isEmpty(title)){
            custom_query += " and name like '%"+title+"%'";
        }
        if(!isEmpty(from) || !isEmpty(to)){
            custom_query += " and (price >= "+ from +") and (price <= "+to+")";
        }
        if(!isEmpty(items)){
            custom_query += " and items like '%"+items+"%'";
        }
        if(!isEmpty(address)){
            custom_query += " and address like '%"+address+"%'";
        }
        custom_query += " and isCheck=0 order by id desc";
        PrintWriter out = response.getWriter();
        response.setContentType("text/html"); 
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        CenterDAO centerDAO = new CenterDAO();
        List<Center> centers = centerDAO.filterCenters(custom_query);
        JsonElement contactObj = gson.toJsonTree(centers);
        if(centers.isEmpty()){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("centers", contactObj);
        out.println(myObj.toString()); 
        out.close();         
    }
    
    public boolean isEmpty(String s){
        boolean flag = false;
        if(s.equals("") || s.isEmpty()){
            flag = true;
        }
        return flag;
    }
    
    private void ratingVote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String id = request.getParameter("id_center");
        String score = request.getParameter("score");
        CenterDAO dao = new CenterDAO();
        Center center = dao.findCurrentCenter(Integer.parseInt(id));
        int newRating = center.getRating() + Integer.parseInt(score);
        int newVote = center.getVote() + 1;
        if(dao.updateRatingOfCenter(Integer.parseInt(id), newRating, newVote)){
            center = dao.findCurrentCenter(Integer.parseInt(id));
        }
        float temp = center.getRating()/center.getVote();
        int rat = Math.round(temp);
        List<Comment> comments = dao.findCommentsCenter(Integer.parseInt(id));
            if(!comments.isEmpty()){
                request.setAttribute("comments", comments); 
            }
            request.setAttribute("center", center);
            request.setAttribute("r", rat);
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/jsp/show_center.jsp");
        dis.forward(request, response);
        
    }
    
    public void editCenterAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String items = request.getParameter("items");
        String price = request.getParameter("price");
        String site = request.getParameter("site");
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        String title = request.getParameter("title");
        String id = request.getParameter("id_center");
        if(lat.equals("") && lat.isEmpty()){
            lat = "51.160523";
        }
        if(lng.equals("") && lng.isEmpty()){
            lng = "71.470356";
        }
        System.out.println("lat------" + lat);
        System.out.println("lng------" + lng);
        CenterDAO centerDAO = new CenterDAO();
        InputStream inputStream = null; // input stream of the upload file
        Part filePart = request.getPart("filename");
        if(title != null && address != null && phone != null && price!=null && items != null){
            if(title.isEmpty() || address.isEmpty() || phone.isEmpty() || price.isEmpty() || items.isEmpty()){
                request.setAttribute("message", "Міндетті өрістер толтырылмаған!");
            } else {
                if (filePart != null && filePart.getSize()!=0) {
                    System.out.println(filePart.getName());
                    System.out.println(filePart.getSize());
                    System.out.println(filePart.getContentType());
                    inputStream = filePart.getInputStream();
                } else {
                    Center c = centerDAO.findCurrentCenter(Integer.parseInt(id));
                    byte[] imageByte = c.getLogo();
                    inputStream = new ByteArrayInputStream(imageByte);
                }
                
                if (centerDAO.editCenter(title, address, phone, Integer.parseInt(price), items, site, inputStream, lat, lng, Integer.parseInt(id))) {
                    request.setAttribute("message", "Сәтті өзгертілді!");
                }
            }
        }
        Center c = centerDAO.findCurrentCenter(Integer.parseInt(id));
        request.setAttribute("center", c);
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/jsp/edit_center.jsp");
        dis.forward(request, response);
    }
    
    private void latestNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html"); 
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        NewsDAO centerDAO = new NewsDAO();
        List<News> centers = centerDAO.findLatestNews();
        JsonElement contactObj = gson.toJsonTree(centers);
        if(centers.isEmpty()){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("news", contactObj);
        out.println(myObj.toString()); 
        out.close();         
    }
    
    private void latestForums(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html"); 
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        ForumDAO dao = new ForumDAO();
        List<Forum> forums = dao.findLatestForums();
        JsonElement contactObj = gson.toJsonTree(forums);
        if(forums.isEmpty()){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("forums", contactObj);
        out.println(myObj.toString()); 
        out.close();         
    }
    
}
