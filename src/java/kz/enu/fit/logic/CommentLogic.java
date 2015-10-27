package kz.enu.fit.logic;

import java.util.List;
import kz.enu.fit.dao.CommentDAO;
import kz.enu.fit.entities.Comment;
import kz.enu.fit.logic.exceptions.EmptyOrderException;
import kz.enu.fit.logic.exceptions.IncorrectLogin;

public class CommentLogic {

    private CommentDAO dao;

    public CommentLogic() {
        dao = new CommentDAO();
    }

    public List<Comment> list() {
        List<Comment> comments = dao.findAll();
        return comments;
    }

    public void accept(String name, String text, String path) throws EmptyOrderException, IncorrectLogin {
        if (name.isEmpty() || text.isEmpty()) {
            throw new EmptyOrderException();
        } else if((name.length()<4) || (text.length()<4)){
            throw new IncorrectLogin();
        }
        //dao.acceptComment(name, text , id_center);
    }

    public void deleteComment(int id) throws EmptyOrderException {
        if (id == 0) {
            throw new EmptyOrderException();
        }
        dao.deleteComment(id);
    }
}
