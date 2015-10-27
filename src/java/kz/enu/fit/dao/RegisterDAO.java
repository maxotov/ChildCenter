package kz.enu.fit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import kz.enu.fit.entities.Entity;
import kz.enu.fit.log.Log;
import kz.enu.fit.messages.MessageManager;
import static kz.enu.fit.web.command.Constants.*;
import kz.enu.fit.pool.ConnectionPool;

public class RegisterDAO extends AbstractDAO {

    private static Log logger = new Log();
    public static final String ADD_USER = "INSERT INTO `users`(`id`, `firstname`, `password`, `status`, `email`, `lastname`, `phone`) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        
    public void Register(String firstname, String lastname, String pass, String phone, String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement(ADD_USER);
                ps.setString(1, firstname);
                ps.setString(2, pass);
                ps.setString(3, CLIENT);
                ps.setString(4, email);
                ps.setString(5, lastname);
                ps.setString(6, phone);
                ps.executeUpdate();                
                
            } catch (SQLException ex) {
                logger.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    logger.error("");
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        
        
    }
    
    @Override
    public List<Entity> findAll() {
        throw new UnsupportedOperationException(ERROR);
    }
}
