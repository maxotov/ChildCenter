package kz.enu.fit.logic;

import java.util.List;
import kz.enu.fit.dao.NewsDAO;
import kz.enu.fit.entities.News;
import kz.enu.fit.logic.exceptions.EmptyOrderException;

public class NewsLogic {
    private NewsDAO dao;
    
    public NewsLogic(){
        dao = new NewsDAO();
    }
    
    public List<News> list(){
        List<News> data = dao.findAll();
        return data;
    }
    
    public News findNews(int id){
        News data = dao.findCurrentNews(id);
        return data;
    }
    
//    public void accept(String description, String text, String date, String title) throws EmptyOrderException{
//        if(description.isEmpty() || text.isEmpty() || title.isEmpty()){
//            throw new EmptyOrderException();
//        }
//        dao.acceptData(description, text, date, title);
//    }
    
    public void update(String description, String text, String date, String title, int id) throws EmptyOrderException{
        if(description.isEmpty() || text.isEmpty() || title.isEmpty()){
            throw new EmptyOrderException();
        }
        dao.updateData(description, text, date, title, id);
    }
    
    public void deleteNews(int id) throws EmptyOrderException{
        if(id == 0){
            throw new EmptyOrderException();
        }
        dao.deleteNews(id);
    }
}
