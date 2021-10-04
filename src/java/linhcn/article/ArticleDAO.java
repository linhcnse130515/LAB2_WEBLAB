/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.article;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import linhcn.utils.DBHelper;

/**
 *
 * @author nguye
 */
public class ArticleDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
     public static final int ROW_PER_PAGE = 20;
     
     private final String UPDATE = "UPDATE article SET status = ? "
            + "WHERE articleId = ? ";

    public List<ArticleDTO> getAllActicleActive(String txtSearch, int pageNum) throws SQLException, NamingException {

        con = null;
        stm = null;
        rs = null;
        List<ArticleDTO> result = new ArrayList();

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String query = "SELECT articleId, tittle, shortDescription, author, date, content, status, email "
                        + "FROM Article "
                        + "WHERE status = 'active' "
                        + "AND content LIKE ? "
                        + "ORDER BY date DESC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(query);
                stm.setString(1, "%" + txtSearch + "%");
                stm.setInt(2, (pageNum - 1) * ROW_PER_PAGE);
                stm.setInt(3, ROW_PER_PAGE);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ArticleDTO dto = new ArticleDTO(rs.getInt("articleId"),
                            rs.getString("tittle"),
                            rs.getString("shortDescription"),
                            rs.getString("author"),
                            rs.getDate("date"),
                            rs.getString("status"),
                            rs.getString("email"),
                            rs.getString("content"));
                    result.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
    
    public int getTotalPageSearch(String txtSearch) throws SQLException, NamingException {
        int count = 0;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String query = "SELECT COUNT(*) as num "
                        + "FROM Article "
                        + "WHERE status = 'active' "
                        + "AND content LIKE ? ";
                stm = con.prepareStatement(query);
                stm.setString(1, "%" + txtSearch + "%");
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("num");
                }
                return (int) Math.ceil((double) count / ROW_PER_PAGE);
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public List<ArticleDTO> getAllActicle(String txtTittle, String txtStatus, int pageNum) throws SQLException, NamingException {

        con = null;
        stm = null;
        rs = null;
        List<ArticleDTO> result = new ArrayList();

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String query = "SELECT articleId, tittle, shortDescription, author, date, content, status, email "
                        + "FROM Article "
                        + "WHERE tittle LIKE ? "
                        + "AND status LIKE ? "
                        + "ORDER BY date DESC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(query);
                stm.setString(1, "%" + txtTittle + "%");
                stm.setString(2, "%" + txtStatus + "%");
                stm.setInt(3, (pageNum - 1) * ROW_PER_PAGE);
                stm.setInt(4, ROW_PER_PAGE);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ArticleDTO dto = new ArticleDTO(rs.getInt("articleId"),
                            rs.getString("tittle"),
                            rs.getString("shortDescription"),
                            rs.getString("author"),
                            rs.getDate("date"),
                            rs.getString("status"),
                            rs.getString("email"),
                            rs.getString("content"));
                    result.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
    
    public int getTotalPageSearchAndStatus(String txtTitle, String txtStatus) throws SQLException, NamingException {
        int count = 0;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String query = "SELECT COUNT(*) as num "
                        + "FROM Article "
                        + "WHERE tittle LIKE ? "
                        + "AND status LIKE ? ";
                stm = con.prepareStatement(query);
                stm.setString(1, "%" + txtTitle + "%");
                stm.setString(2, "%" + txtStatus + "%");
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("num");
                }
                return (int) Math.ceil((double) count / ROW_PER_PAGE);
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public ArticleDTO getById(int articleId) throws SQLException{
        ArticleDTO result = null;
        try {
            con = DBHelper.getConnection();
            String query = "SELECT tittle, shortDescription, author, date, status, email, content FROM Article WHERE articleId = ? AND status = 'active' ";
            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = new ArticleDTO(articleId, 
                        rs.getString("tittle"), 
                        rs.getString("shortDescription"), 
                        rs.getString("author"), 
                        rs.getDate("date"), 
                        rs.getString("status"), 
                        rs.getString("email"), 
                        rs.getString("content"));
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public boolean createActicle(ArticleDTO newActicle)
            throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String query = "INSERT INTO Article "
                        + "(tittle, shortDescription, author, date, content, status, email) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(query);
                stm.setString(1, newActicle.getTittle());
                stm.setString(2, newActicle.getShortDescription());
                stm.setString(3, newActicle.getAuthor());
                stm.setDate(4, newActicle.getDate());
                stm.setString(5, newActicle.getContent());
                stm.setString(6, newActicle.getStatus());
                stm.setString(7, newActicle.getEmail());

                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch(SQLException e){
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean update(int articleId, String status) throws SQLException {

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = UPDATE;
                stm = con.prepareStatement(sql);
                stm.setString(1, status);
                stm.setInt(2, articleId);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
