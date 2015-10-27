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
import kz.enu.fit.pool.ConnectionPool;
import kz.enu.fit.entities.Center;
import kz.enu.fit.entities.Comment;
import kz.enu.fit.log.Log;
import kz.enu.fit.messages.MessageManager;
import org.apache.catalina.util.Base64;

public class CenterDAO extends AbstractDAO<Center> {

    private static Log log = new Log();
    public static final String ADD_CENTER = "INSERT INTO centers (id, name, address, phone, price, items, site,"
            + " logo, lat, lng, id_user, view, rating, isCheck, vote) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0, 1)";
    public static final String EDIT_CENTER = "UPDATE centers SET name =?, address =?, phone =?, price =?, items =?, site =?, logo =?, lat =?, lng =? WHERE id=?";
    public static final String FIND_ALL_CENTERS = "select * from centers where isCheck=0";
    public static final String FIND_ALL_CENTERS_SCROLL = "select * from centers where isCheck=0 order by id desc limit ? offset ?";
    public static final String FIND_CENTER_BY_ID = "select * from centers where id=?";
    public static final String FIND_CENTER_BY_USER = "select * from centers where id_user=?";
    public static final String FIND_COMMENTS_CENTER = "select * from otziv where id_center=?";
    public static final String UPDATE_RATING = "UPDATE centers SET rating =?, vote =?  WHERE id=?";
    
    @Override
    public List<Center> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Center> data_list = new ArrayList<Center>();
        Center data = null;

       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_ALL_CENTERS);
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
    
    public List<Center> scrollPag(int offs, int postnumbers) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Center> data_list = new ArrayList<Center>();
        Center data = null;

       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_ALL_CENTERS_SCROLL);
            statement.setInt(1, postnumbers);
            statement.setInt(2, offs);
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
    
    public List<Center> filterCenters(String custom_query) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Center> data_list = new ArrayList<Center>();
        Center data = null;

       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(custom_query);
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
    
    public int catchViewOfCenter(int id) {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int view = 0;
        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement("select centers.view from centers where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                view = resultSet.getInt("centers.view");
                
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
    
    public boolean updateViewOfCenter(int id, int view) {
         Connection connection = null;
        PreparedStatement statement = null;
        boolean flag = false;
        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement("update centers set view=? where id=?");
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
    
    public boolean updateRatingOfCenter(int id, int rating, int vote) {
         Connection connection = null;
        PreparedStatement statement = null;
        boolean flag = false;
        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement("update centers set rating=?, vote=? where id=?");
            statement.setInt(1, rating);
            statement.setInt(2, vote);
            statement.setInt(3, id);
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
    
    public Center findCurrentCenter(int id) {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Center data = null;

        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_CENTER_BY_ID);
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
    
    public List<Center> findCentersUser(int id_user) {
        List<Center> centers = new ArrayList<Center>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Center data = null;

        try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_CENTER_BY_USER);
            statement.setInt(1, id_user);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data = buildData(resultSet);
                centers.add(data);
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
                return centers;
    }
    
    public boolean addCenter(String name, String address, String phone, int price, String items, String site, 
            InputStream inputStream, String lat, String lng, int id_user) throws IOException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = ConnectionPool.getConnection();
            try{
            ps = con.prepareStatement(ADD_CENTER);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setInt(4, price);
            ps.setString(5, items);
            ps.setString(6, site);
             if (inputStream != null) {
                ps.setBlob(7, inputStream);
            }
             ps.setString(8, lat);
             ps.setString(9, lng);
             ps.setInt(10, id_user);
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
    
    public boolean editCenter(String name, String address, String phone, int price, String items, String site, 
            InputStream inputStream, String lat, String lng, int id) throws IOException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = ConnectionPool.getConnection();
            try{
            ps = con.prepareStatement(EDIT_CENTER);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setInt(4, price);
            ps.setString(5, items);
            ps.setString(6, site);
             if (inputStream != null) {
                ps.setBlob(7, inputStream);
            }
             ps.setString(8, lat);
             ps.setString(9, lng);
             ps.setInt(10, id);
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
    
    
    private Center buildData(ResultSet resultSet) throws SQLException{
                Center data = new Center();
                data.setId(resultSet.getLong("centers.id"));
                data.setName(resultSet.getString("centers.name"));
                data.setAddress(resultSet.getString("centers.address"));
                data.setPhone(resultSet.getString("centers.phone"));
                data.setPrice(resultSet.getInt("centers.price"));
                data.setItems(resultSet.getString("centers.items"));
                data.setSite(resultSet.getString("centers.site"));
                Blob blob = resultSet.getBlob("centers.logo");
                data.setLogo(blob.getBytes(1, (int) blob.length()));
                String encodedImage = Base64.encode(data.getLogo());
                data.setEncodedImage(encodedImage);
                data.setLat(resultSet.getFloat("centers.lat"));
                data.setLng(resultSet.getFloat("centers.lng"));
                data.setId_user(resultSet.getInt("centers.id_user"));
                data.setView(resultSet.getInt("centers.view"));
                data.setRating(resultSet.getInt("centers.rating"));
                data.setIsCheck(resultSet.getInt("centers.isCheck"));
                data.setVote(resultSet.getInt("centers.vote"));
                return data;
    }
    
    private Comment buildComment(ResultSet resultSet) throws SQLException{
                Comment data = new Comment();
                data.setId(resultSet.getLong("otziv.id"));
                data.setId_center(resultSet.getInt("otziv.id_center"));
                data.setName(resultSet.getString("otziv.name"));
                data.setText(resultSet.getString("otziv.text"));
                data.setCheck(resultSet.getInt("otziv.check"));
                data.setEmail(resultSet.getString("otziv.email"));
                return data;
        
    }
    
    public List<Comment> findCommentsCenter(int center_id) {
         Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Comment> data_list = new ArrayList<Comment>();
        Comment data = null;

       try {
            connection = ConnectionPool.getConnection();
            try {
            statement = connection.prepareStatement(FIND_COMMENTS_CENTER);
            statement.setInt(1, center_id);
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
    
    public static int countCenter() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = ConnectionPool.getConnection();
            try {
                ps = conn.prepareStatement("SELECT COUNT(*) FROM centers");
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
