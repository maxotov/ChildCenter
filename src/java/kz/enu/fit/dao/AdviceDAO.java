package kz.enu.fit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kz.enu.fit.pool.ConnectionPool;
import kz.enu.fit.entities.Advice;
import kz.enu.fit.log.Log;
import kz.enu.fit.messages.MessageManager;

public class AdviceDAO extends AbstractDAO<Advice> {

    private static Log log = new Log();
    public static final String FIND_ADVICE = "select * from advice where id=?";

    @Override
    public List<Advice> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Advice> comments = new ArrayList<Advice>();
        Advice comment = null;

        try {
            connection = ConnectionPool.getConnection();
            try {
                statement = connection.prepareStatement("select * from advice");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    comment = new Advice();
                    comment.setId(resultSet.getLong("advice.id"));
                    comment.setTitle(resultSet.getString("advice.title"));
                    String text = resultSet.getString("advice.text").substring(0, 100);
                    comment.setText(text + "...");
                    comment.setImg(resultSet.getString("advice.img"));
                    comments.add(comment);
                }
            } catch (SQLException ex) {
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
            log.error(ex.toString());
        }

        return comments;
    }

    private Advice buildComment(ResultSet resultSet) throws SQLException {
        Advice comment = new Advice();
        comment.setId(resultSet.getLong("advice.id"));
        comment.setTitle(resultSet.getString("advice.title"));
        comment.setText(resultSet.getString("advice.text"));
        comment.setImg(resultSet.getString("advice.img"));
        return comment;
    }

    public Advice findAdviceById(int id) {
        Advice advice = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionPool.getConnection();
            try {
                ps = con.prepareStatement(FIND_ADVICE);
                ps.setInt(1, id);
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    advice = buildComment(resultSet);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
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
                    log.error("error");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return advice;
    }
}
