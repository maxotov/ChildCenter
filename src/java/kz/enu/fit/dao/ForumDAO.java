package kz.enu.fit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kz.enu.fit.entities.Forum;
import kz.enu.fit.entities.ForumAnswer;
import kz.enu.fit.entities.User;
import kz.enu.fit.log.Log;
import kz.enu.fit.messages.MessageManager;
import kz.enu.fit.pool.ConnectionPool;

public class ForumDAO extends AbstractDAO {

    private static Log log = new Log();
    public static final String FIND_ALL_FORUM = "select * from forum order by id desc";
    public static final String FIND_LATEST_FORUMS = "select * from forum order by id desc limit 5";
    public static final String FIND_FORUM_BY_ID = "select * from forum where id=?";
    public static final String ADD_NEW_FORUM = "INSERT INTO `forum`(`id`, `id_user`, `title`, `is_open`, `date_create`) VALUES (null,?,?,1,?)";
    public static final String ADD_NEW_ANSWER = "INSERT INTO `forum_answer`(`id`, `id_user`, `id_forum`, `answer`, `date_add`) VALUES (null,?,?,?,?)";
    public static final String DELETE_NEWS = "delete from forum where id=? limit 1";
    public static final String FIND_ANSWERS_BY_FORUM = "select * from forum_answer where id_forum=?";
    public static final String UPDATE_FORUM_STATUS = "UPDATE `forum` SET `is_open`=? WHERE `id`=?";

    @Override
    public List<Forum> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Forum> forums = new ArrayList<Forum>();
        Forum data = null;
        try {
            connection = ConnectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_FORUM);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    data = buildForum(resultSet);
                    forums.add(data);
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
        return forums;
    }
    
    public boolean insertForum(int id_user, String title, String date) throws IOException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = ConnectionPool.getConnection();
            try{
            ps = con.prepareStatement(ADD_NEW_FORUM);
            ps.setInt(1, id_user);
            ps.setString(2, title);
            ps.setString(3, date);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            }           
            finally {
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
        return flag;
    }
    
    public boolean insertForumAnswer(int id_user, int id_forum, String answer) throws IOException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = ConnectionPool.getConnection();
            try{
            ps = con.prepareStatement(ADD_NEW_ANSWER);
            ps.setInt(1, id_user);
            ps.setInt(2, id_forum);
            ps.setString(3, answer);
            Timestamp currentDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ps.setTimestamp(4, currentDate);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            }           
            finally {
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
        return flag;
    }
    
    public Forum findForumById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Forum data = null;
        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_FORUM_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildForum(resultSet);                
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
           return data;
    }

    private Forum buildForum(ResultSet resultSet) throws SQLException {
        Forum data = new Forum();
        data.setId(resultSet.getLong("forum.id"));
        data.setId_user(resultSet.getInt("forum.id_user"));
        data.setTitle(resultSet.getString("forum.title"));
        data.setIsOpen(resultSet.getInt("forum.is_open"));
        data.setDate_create(resultSet.getString("forum.date_create"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserById(resultSet.getInt("forum.id_user"));
        data.setAuthor_name(user.getFisrtname() + " " + user.getLastname());
        List<ForumAnswer> answers = findAnswersByForum(resultSet.getInt("forum.id"));
        data.setAnswer_count(answers.size());
        return data;

    }

    private ForumAnswer buildForumAnswer(ResultSet resultSet) throws SQLException {
        ForumAnswer data = new ForumAnswer();
        data.setId(resultSet.getLong("forum_answer.id"));
        data.setId_user(resultSet.getInt("forum_answer.id_user"));
        data.setId_forum(resultSet.getInt("forum_answer.id_forum"));
        data.setAnswer(resultSet.getString("forum_answer.answer"));
        Timestamp date = resultSet.getTimestamp("forum_answer.date_add");
        String dateString = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
        data.setDateString(dateString);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserById(resultSet.getInt("forum_answer.id_user"));
        data.setUser_name(user.getFisrtname() + " " + user.getLastname());
        return data;
    }
    
    public List<ForumAnswer> findAnswersByForum(int forum_id) {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ForumAnswer> data_list = new ArrayList<ForumAnswer>();
        ForumAnswer data = null;

       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_ANSWERS_BY_FORUM);
            statement.setInt(1, forum_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildForumAnswer(resultSet);
                data_list.add(data);
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

                return data_list;

    }
    
    public boolean updateForumStatus(int forumStatus, int id){
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = ConnectionPool.getConnection();
            try{
            ps = con.prepareStatement(UPDATE_FORUM_STATUS);
            ps.setInt(1, forumStatus);
            ps.setInt(2, id);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            }           
            finally {
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
        return flag;
    }
    //----------------- forum pagination -----------------------------------
    private int nOfForumRecords;
    
    public List<Forum> viewAllForums(int offset, int noOfRecords){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Forum> data_list = new ArrayList<Forum>();
        Forum data = null;
       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement("select SQL_CALC_FOUND_ROWS * from forum limit " + offset + ", " + noOfRecords +"");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildForum(resultSet);
                data_list.add(data);
            }
            resultSet.close();
            resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
			if(resultSet.next())
				this.nOfForumRecords = resultSet.getInt(1);
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

                return data_list;
    }
    
    public int getNoOfRecords() {
		return nOfForumRecords;
    }
   // ------------------------ END FORUM PAGINATION -----------------------
    
    public List<Forum> findLatestForums() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Forum> data_list = new ArrayList<Forum>();
        Forum data = null;
        try {
            connection = ConnectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_LATEST_FORUMS);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    data = buildForum(resultSet);
                    data_list.add(data);
                }
            } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                close(resultSet, statement, connection);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return data_list;
    }

    private void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
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
}
