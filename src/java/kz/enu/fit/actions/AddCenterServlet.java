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
import kz.enu.fit.dao.CenterDAO;

@MultipartConfig(maxFileSize = 16177215)
public class AddCenterServlet extends HttpServlet {

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
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String items = request.getParameter("items");
        String price = request.getParameter("price");
        String site = request.getParameter("site");
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        String title = request.getParameter("title");
        String id_user = request.getParameter("id_user");
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
        if(title != null && address != null && phone != null && price!=null && items != null && id_user != null){
            if(title.isEmpty() || address.isEmpty() || phone.isEmpty() || price.isEmpty() || items.isEmpty()){
                request.setAttribute("message", "Міндетті өрістер толтырылмаған!");
            } else {
                if (filePart != null) {
                    System.out.println(filePart.getName());
                    System.out.println(filePart.getSize());
                    System.out.println(filePart.getContentType());
                    inputStream = filePart.getInputStream();
                }
                
                if (centerDAO.addCenter(title, address, phone, Integer.parseInt(price), items, site, inputStream, lat, lng, Integer.parseInt(id_user))) {
                    request.setAttribute("message", "сәтті қосылды!");
                }
            }
        }
        
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/jsp/add_center.jsp");
        dis.forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
