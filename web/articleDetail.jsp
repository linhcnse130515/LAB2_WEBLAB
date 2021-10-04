<%-- 
    Document   : articleDetail
    Created on : Oct 3, 2021, 4:03:02 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Article Page</title>
    </head>
    <body>
        <c:url var="home" value="homeAction">
            <c:param name="btnAction" value="Home"></c:param>
        </c:url>
        <a href="${home}">Home</a>
        <c:if test="${empty sessionScope.USER}">
            <a href="login.html" a>LOGIN</a>
        </c:if>
        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${sessionScope.USER.role == 'admin'}">
                <c:url var="user" value="searchAction">             
                </c:url>
                <a href="${user}">Manage Article</a>
            </c:if>
            <c:if test="${sessionScope.USER.role == 'member'}">
                <c:url var="user" value="createArticlePage">             
                </c:url>
                <a href="${user}">Post New Article</a>
            </c:if>
            <c:url var="logout" value="logoutAction">
                <c:param name="btnAction" value="Logout"></c:param>
            </c:url>
            <a href="${logout}">Logout</a>
        </c:if>
        <c:set var="acticle" value="${requestScope.ARTICLE}"/>
        <h2> ${acticle.tittle} </h2>
        ${acticle.shortDescription} </br>
        Author: ${acticle.author} </br>
        Email: ${acticle.email} </br>
        Date: ${acticle.date} </br>
        ${acticle.content} </br>
        </br>
        <form action="commentAction">
            Comment: <input type="text" name="txtComment" value=""/>
            <input type="hidden" name="txtArticleId" value="${acticle.articleId}"/>
            <input type="submit" name="btnAction" value="comment"/>
        </form>

        <h2> Comment </h2>
        <c:set var="listComment" value="${requestScope.COMMNENTS}"/>
        <c:forEach var="comment" items="${listComment}">
            <div>            
                Email: ${comment.email} </br>
                Date: ${comment.date} </br>
                Content: ${comment.content} </br>    
            </div>
            <br>
        </c:forEach> 
    </body>
</html>
