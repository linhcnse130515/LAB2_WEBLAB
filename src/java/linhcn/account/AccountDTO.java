/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.account;

import java.io.Serializable;

/**
 *
 * @author natton
 */
public class AccountDTO implements Serializable {

    private String email;
    private String password;
    private String name;
    private String role;
    private String status;

    public AccountDTO() {
    }

    public AccountDTO(String email, String password, String fullname, String role, String status) {
        this.email = email;
        this.password = password;
        this.name = fullname;
        this.role = role;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
