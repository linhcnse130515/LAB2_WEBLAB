/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.article;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author nguye
 */
public class ArticleDTO implements Serializable {
    private Integer articleId;
    private String tittle;
    private String shortDescription;
    private String author;
    private Date date;
    private String status;
    private String email;
    private String content;

    public ArticleDTO() {
    }

    public ArticleDTO(Integer articleId, String tittle, String shortDescription, String author, Date date, String status, String email, String content) {
        this.articleId = articleId;
        this.tittle = tittle;
        this.shortDescription = shortDescription;
        this.author = author;
        this.date = date;
        this.status = status;
        this.email = email;
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
