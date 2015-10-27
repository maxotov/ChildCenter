package kz.enu.fit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kz.enu.fit.entities.Law;
import kz.enu.fit.log.Log;
import kz.enu.fit.messages.MessageManager;
import kz.enu.fit.pool.ConnectionPool;

public class LawDAO extends AbstractDAO<Law> {

    private static Log log = new Log();
    public static final String FIND_LAW = "select * from law where id=?";

    @Override
    public List<Law> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Law> comments = new ArrayList<Law>();
        Law comment = null;
        try {
            connection = ConnectionPool.getConnection();
            try {
                statement = connection.prepareStatement("select * from law");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    comment = new Law();
                    comment.setId(resultSet.getLong("law.id"));
                    comment.setTitle(resultSet.getString("law.title"));                   
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

    private Law buildLaw(ResultSet resultSet) throws SQLException {
        Law comment = new Law();
        comment.setId(resultSet.getLong("law.id"));
        comment.setTitle(resultSet.getString("law.title"));
        comment.setText(resultSet.getString("law.text"));
        return comment;
    }

    public Law findLawById(int id) {
        Law advice = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionPool.getConnection();
            try {
                ps = con.prepareStatement(FIND_LAW);
                ps.setInt(1, id);
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    advice = buildLaw(resultSet);
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
