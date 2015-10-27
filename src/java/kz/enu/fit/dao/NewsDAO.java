package kz.enu.fit.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kz.enu.fit.log.Log;
import kz.enu.fit.messages.MessageManager;
import kz.enu.fit.entities.News;
import kz.enu.fit.pool.ConnectionPool;
import kz.enu.fit.entities.Commentary;

public class NewsDAO extends AbstractDAO {
    private static Log log = new Log();
    public static final String FIND_ALL_NEWS = "select * from data";
    public static final String FIND_LATEST_NEWS = "select * from data order by id desc limit 5";
    public static final String FIND_CURRENT_NEWS = "select * from data where id=?";
    public static final String FIND_COMMENTS_BY_DATA = "select * from comment where data_id=? and checking=1";
    public static final String FIND_COMMENTS = "select * from comment";
    public static final String ADD_NEW_NEWS = "INSERT INTO `data`(`id`, `description`, `date`, `title`, `text`, `author`, `image`, `view`, `like`) VALUES (null,?,?,?,?,?,?,?,?)";
    public static final String ADD_NEW_COMMENTARY = "INSERT INTO `comment`(`id`, `data_id`, `name`, `email`, `text`, `checking`) VALUES (null,?,?,?,?,?)";
    public static final String DELETE_NEWS = "delete from data where id=?";
    public static final String DELETE_COMMENTARY = "delete from comment where id=?";
    public static final String UPDATE_NEWS = "update data set title=?, description=?, text=?, date=? where id=?";
    public static final String UPDATE_COMMENTARY = "UPDATE `comment` SET `checking`=? WHERE `id`=?";
    
    @Override
    public List<News> findAll() {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<News> data_list = new ArrayList<News>();
        News data = null;

       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_ALL_NEWS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildData(resultSet);
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
    
    public List<News> findLatestNews() {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<News> data_list = new ArrayList<News>();
        News data = null;
       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_LATEST_NEWS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildData(resultSet);
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
    
    private int nOfRecords;
    
    public List<News> viewAllNews(int offset, int noOfRecords){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<News> data_list = new ArrayList<News>();
        News data = null;

       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement("select SQL_CALC_FOUND_ROWS * from data order by id desc limit " + offset + ", " + noOfRecords +"");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildData(resultSet);
                data_list.add(data);
            }
            resultSet.close();
            resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
			if(resultSet.next())
				this.nOfRecords = resultSet.getInt(1);
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
		return nOfRecords;
    }
    
    public News findCurrentNews(int id) {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        News data = null;

        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_CURRENT_NEWS);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildData(resultSet);
                
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
    
    private News buildData(ResultSet resultSet) throws SQLException{
                News data = new News();
                data.setId(resultSet.getLong("data.id"));
                data.setDescription(resultSet.getString("data.description"));
                data.setText(resultSet.getString("data.text"));
                data.setDate(resultSet.getString("data.date"));
                data.setTitle(resultSet.getString("data.title"));
                data.setAuthor(resultSet.getString("data.author"));
                Blob blob = resultSet.getBlob("data.image");
                data.setImage(blob.getBytes(1, (int) blob.length()));
                data.setView(resultSet.getInt("data.view"));
                data.setLike(resultSet.getInt("data.like"));
                return data;
        
    }
    
    private Commentary buildComment(ResultSet resultSet) throws SQLException{
                Commentary data = new Commentary();
                data.setId(resultSet.getLong("comment.id"));
                data.setData_id(resultSet.getInt("comment.data_id"));
                data.setName(resultSet.getString("comment.name"));
                data.setEmail(resultSet.getString("comment.email"));
                data.setText(resultSet.getString("comment.text"));
                data.setChecking(resultSet.getInt("comment.checking"));
                return data;
        
    }
    
    public boolean acceptData(String description, String text, String date, String title, String author, 
            InputStream inputStream) throws IOException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = ConnectionPool.getConnection();
            try{
            ps = con.prepareStatement(ADD_NEW_NEWS);
            ps.setString(1, description);
            ps.setString(2, date);
            ps.setString(3, title);
            ps.setString(4, text);
            ps.setString(5, author);
             if (inputStream != null) {
                ps.setBlob(6, inputStream);
            }
             ps.setInt(7, 0);
             ps.setInt(8, 0);
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
    
    public void updateData(String description, String text, String date, String title, int id) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = ConnectionPool.getConnection();
            try {
            ps = con.prepareStatement(UPDATE_NEWS);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, text);
            ps.setString(4, date);
            ps.setInt(5, id);
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
                    log.error("error");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        
    }
    
    public void deleteNews(int id){
        Connection con = null;
        PreparedStatement ps = null;
        
         try {
            con = ConnectionPool.getConnection();
            try {
            ps = con.prepareStatement(DELETE_NEWS);
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
                    log.error("error");
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        
    }
    
    public int catchViewOfNews(int id) {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int view = 0;
        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement("select data.view from data where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                view = resultSet.getInt("data.view");
                
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

                return view;

    }
    
    public boolean updateViewOfNews(int id, int view) {
         Connection connection = null;
        PreparedStatement statement = null;
        boolean flag = false;
        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement("update data set view=? where id=?");
            statement.setInt(1, view);
            statement.setInt(2, id);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
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

                return flag;

    }
    
    public int catchLikeOfNews(int id) {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int view = 0;
        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement("select data.like from data where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                view = resultSet.getInt("data.like");
                
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

                return view;

    }
    
    public boolean updateLikeOfNews(int id, int view) {
         Connection connection = null;
        PreparedStatement statement = null;
        boolean flag = false;
        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement("UPDATE `data` SET `like`=? WHERE `id`=?");
            statement.setInt(1, view);
            statement.setInt(2, id);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
                log.warn(MessageManager.getProperty("message.SQLException"));
                throw new SQLException(MessageManager.getProperty("message.SQLException"));
            } finally {
                try {
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

                return flag;

    }
    
    public boolean addCommentary(String name, String email, String text, int data_id){
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = ConnectionPool.getConnection();
            try{
            ps = con.prepareStatement(ADD_NEW_COMMENTARY);
            ps.setInt(1, data_id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, text);
            ps.setInt(5, 0);
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
    
    public List<Commentary> findAllCommentary() {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Commentary> data_list = new ArrayList<Commentary>();
        Commentary data = null;

       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_COMMENTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildComment(resultSet);
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
    
    public List<Commentary> findCommentsByNews(int news_id) {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Commentary> data_list = new ArrayList<Commentary>();
        Commentary data = null;

       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_COMMENTS_BY_DATA);
            statement.setInt(1, news_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildComment(resultSet);
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
    
    public boolean deleteCommentary(int data_id){
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = ConnectionPool.getConnection();
            try{
            ps = con.prepareStatement(DELETE_COMMENTARY);
            ps.setInt(1, data_id);
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
    
    public boolean publishCommentary(int data_id){
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = ConnectionPool.getConnection();
            try{
            ps = con.prepareStatement(UPDATE_COMMENTARY);
            ps.setInt(1, 1);
            ps.setInt(2, data_id);
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
    
}
