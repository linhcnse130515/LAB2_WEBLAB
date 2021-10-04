/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.comment;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author nguye
 */
public class CommentDTO implements Serializable {
    private Integer id;
    private String content;
    private String email;
    private Date date;
    private int articleId;

    public CommentDTO(Integer id, String content, String email, Date date, int articleId) {
        this.id = id;
        this.content = content;
        this.email = email;
        this.date = date;
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
    
    
}
