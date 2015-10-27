package kz.enu.fit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kz.enu.fit.log.Log;
import kz.enu.fit.messages.MessageManager;
import kz.enu.fit.entities.Comment;
import kz.enu.fit.pool.ConnectionPool;

public class CommentDAO extends AbstractDAO {

    private static Log log = new Log();
    public static final String FIND_ALL_COMMENT = "select * from otziv order by id desc";
    public static final String ADD_NEW_COMMENT = "INSERT INTO `otziv`(`id`, `name`, `text`, `id_center`, `check`, `email`) VALUES(null,?,?,?,0,?)";
    public static final String DELETE_COMMENT = "delete from otziv where id=?";

    @Override
    public List<Comment> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Comment> comments = new ArrayList<Comment>();
        Comment comment = null;

        try {
            connection = ConnectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_COMMENT);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    comment = buildComment(resultSet);
                    comments.add(comment);
                }
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
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
            log.error(ex.toString());
        }

        return comments;

    }

    private Comment buildComment(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getLong("otziv.id"));
        comment.setId_center(resultSet.getInt("otziv.id_center"));
        comment.setName(resultSet.getString("otziv.name"));
        comment.setText(resultSet.getString("otziv.text"));
        comment.setCheck(resultSet.getInt("otziv.check"));
        comment.setEmail(resultSet.getString("otziv.email"));
        return comment;

    }

    public void acceptComment(String name, String text, int id_center, String email) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectionPool.getConnection();
            try {
                ps = con.prepareStatement(ADD_NEW_COMMENT);
                ps.setString(1, name);
                ps.setString(2, text);
                ps.setInt(3, id_center);
                ps.setString(4, email);
                ps.executeUpdate();

            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.toString());
        }

    }

    public void deleteComment(int id) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectionPool.getConnection();
            try {
                ps = con.prepareStatement(DELETE_COMMENT);
                ps.setInt(1, id);
                ps.executeUpdate();

            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    log.error("");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.toString());
        }

    }
    
    public static int countComment() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement("SELECT COUNT(*) FROM otziv");
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
