/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import linhcn.article.ArticleDTO;
import linhcn.utils.DBHelper;

/**
 *
 * @author nguye
 */
public class CommentDAO {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    public List<CommentDTO> getAllByArticleId(int articleId) throws SQLException {
        List<CommentDTO> result = new ArrayList<>();
        try {
            con = DBHelper.getConnection();
            String query = "SELECT id, content, email, date FROM Comments WHERE articleId = ? ";
            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            rs = stm.executeQuery();
            while (rs.next()) {
                CommentDTO comment = new CommentDTO(rs.getInt("id"), rs.getString("content"), rs.getString("email"), rs.getDate("date"), articleId);
                result.add(comment);
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
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
    
    public boolean createActicle(CommentDTO comment)
            throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String query = "INSERT INTO Comments "
                        + "(content, email, date, articleId) "
                        + "VALUES(?, ?, ?, ?) ";
                stm = con.prepareStatement(query);
                stm.setString(1, comment.getContent());
                stm.setString(2, comment.getEmail());
                stm.setDate(3, comment.getDate());
                stm.setInt(4, comment.getArticleId());

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
}
