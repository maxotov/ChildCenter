package kz.enu.fit.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.dao.CenterDAO;
import kz.enu.fit.entities.Center;
import kz.enu.fit.web.command.ActionCommand;

public class EditCenterCommand implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
        String page =null;
        String id_center = request.getParameter("id_center");
        CenterDAO centerDAO = new CenterDAO();
        Center center = centerDAO.findCurrentCenter(Integer.parseInt(id_center));
        request.setAttribute("center", center);
        page = "/jsp/edit_center.jsp";
        
        return page;
    }
    
}
