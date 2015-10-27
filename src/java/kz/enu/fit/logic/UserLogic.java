package kz.enu.fit.logic;

import java.util.List;
import kz.enu.fit.entities.User;
import kz.enu.fit.logic.exceptions.IncorrectPasswordException;
import kz.enu.fit.logic.exceptions.NoSuchUserException;
import kz.enu.fit.dao.UserDAO;
import kz.enu.fit.logic.exceptions.EmptyListException;
import org.apache.log4j.Logger;

public class UserLogic {

    public static Logger log = Logger.getLogger(UserLogic.class);
    private UserDAO dao;

    public UserLogic() {
        dao = new UserDAO();
    }
    /**
     * receives a request from the user and password
     * @param enterLogin
     * @param enterPass
     * @return user
     * @throws IncorrectPasswordException
     * @throws NoSuchUserException 
     */
    public User checkLogin(String enterEmail, String enterPass) throws IncorrectPasswordException, NoSuchUserException {

        User user = dao.findUser(enterEmail);
        if(user == null){
            throw new NoSuchUserException();
        }
        if (!user.getPassword().equals(RegisterLogic.getHashMD5(enterPass))) {
          throw new IncorrectPasswordException();
        }

        return user;
    }

    public List<User> list() throws EmptyListException {
        
        List<User> clients = dao.findAll();
        if(clients != null && clients.isEmpty()){
            throw new EmptyListException();
        }
        
        return clients;
        
    }
    
    public User getClientInfo(User user){
        
        User client = dao.findClient(user);
        
        return client;
    }
    
    public User getInfoUser(String enterLogin) throws NoSuchUserException {

        User user = dao.findUser(enterLogin);
        if(user == null){
            throw new NoSuchUserException();
        }
        return user;
    }
    
    public User getInfoUserByEmail(String enterLogin) throws NoSuchUserException {

        User user = dao.findUser(enterLogin);
        if(user == null){
            throw new NoSuchUserException();
        }
        return user;
    }

}
