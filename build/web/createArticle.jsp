<%-- 
    Document   : createArticle
    Created on : Oct 3, 2021, 4:56:58 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Article Page</title>
    </head>
    <body>
        <h1>Create Article</h1>
        
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
        <form action="createArticleAction">
            Title: <input type="text" name="txtTitle" value="" ><br>
            Short Description: <input type="text" name="txtDescription" value="" ><br>
            Content: <input type="text" name="txtContent" value="" ><br>
            <input type="submit" name="btnAction" value="New Article" >
            <input type="reset" value="Reset" ><br>
        </form>
        ${requestScope.MESSAGE}
    </body>
</html>
