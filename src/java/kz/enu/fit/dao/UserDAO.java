package kz.enu.fit.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kz.enu.fit.entities.User;
import kz.enu.fit.enums.Status;
import kz.enu.fit.log.Log;
import kz.enu.fit.messages.MessageManager;
import kz.enu.fit.pool.ConnectionPool;

public class UserDAO extends AbstractDAO<User> {

    private static Log log = new Log();
    public static final String FIND_USER = "select * from users where email=?";
    public static final String FIND_USER_BY_ID = "select * from users where id=?";
    public static final String FIND_CLIENT = "SELECT users.id, users.login, users.password, users.email, client.phone FROM users JOIN client ON client.id_user=users.id where ID_USER = ?";
    public static final String FIND_CLIENTS = "SELECT * FROM users where status='client'";

    public User findUser(String email) {

        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement(FIND_USER);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    user = buildUser(rs);
                }
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
                    if(rs != null){
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return user;

    }

    public User findUserById(int id) {

        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement(FIND_USER_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    user = buildUser(rs);
                }
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
                    if(rs != null){
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return user;

    }

    public User findClient(User user) {

        User client = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement(FIND_CLIENT);
                ps.setLong(1, user.getId());
                rs = ps.executeQuery();
                if (rs.next()) {
                    client = buildUser(rs);
                }
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
                    if(rs != null){
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return client;

    }

    @Override
    public List<User> findAll() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> clients = new ArrayList<User>();
        User client = null;
        try {
            connection = ConnectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_CLIENTS);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    client = buildUser(resultSet);
                    clients.add(client);

                }
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    log.error("error");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }

        return clients;

    }

    private User buildUser(ResultSet resultSet) throws SQLException {

        User user = new User();
        user.setId(resultSet.getLong("users.id"));
        user.setFisrtname(resultSet.getString("users.firstname"));
        user.setLastname(resultSet.getString("users.lastname"));
        user.setPhone(resultSet.getString("users.phone"));
        user.setPassword(resultSet.getString("users.password"));
        user.setEmail(resultSet.getString("users.email"));
        user.setStatus(Status.valueOf(resultSet.getString("users.status").toUpperCase()));
        return user;
    }
    
    public static void deleteOldSession() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement("DELETE FROM online WHERE UNIX_TIMESTAMP() - UNIX_TIMESTAMP(time) > 300");
                ps.executeUpdate();
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
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
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
    }
    
    public static String checkIsExist(String ip) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String tmp = "";
        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement("SELECT online.ip FROM online WHERE ip=?");
                ps.setString(1, ip);
                rs = ps.executeQuery();
                if(rs.next()){
                    tmp = rs.getString("online.ip");
                }
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return tmp;
    }
    
    public static void updateSession(String ip) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement("UPDATE online SET time=NOW() WHERE ip=?");
                ps.setString(1, ip);
                ps.executeUpdate();
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
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
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
    }
    
    public static void insertSession(String ip) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement("INSERT INTO online (ip,time) VALUES (?,NOW())");
                ps.setString(1, ip);
                ps.executeUpdate();
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
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
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
    }
    
    public static int countSession() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement("SELECT COUNT(*) FROM online");
                rs = ps.executeQuery();
                if(rs.next()){
                    count = rs.getInt("COUNT(*)");
                }
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return count;
    }
}
