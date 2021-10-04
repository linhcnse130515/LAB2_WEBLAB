/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.account;

import linhcn.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author nguye
 */
public class AccountDAO {
    
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
        
    public AccountDTO getAccountByUsernameAndPassword(String email, String password) 
            throws SQLException, NamingException {
        
        AccountDTO dto = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT name, role, status FROM Account WHERE email = ? AND password = ? AND status = 'Active' ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    dto = new AccountDTO(email, password, name, role, status);
                }
            }
        } catch(SQLException | NamingException e) {
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
        return dto;
    }

    public boolean createAccount(AccountDTO account) throws NamingException, SQLException {
        con = DBHelper.getConnection();
        try {
            if (con != null) {
                String query = "INSERT INTO Account(email, password, name, status, role) "
                        + "VALUES(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(query);
                stm.setString(1, account.getEmail());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getName());
                stm.setString(4, account.getStatus());
                stm.setString(5, account.getRole());
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }

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
