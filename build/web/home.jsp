<%-- 
    Document   : home
    Created on : Sep 29, 2021, 10:54:33 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Home Page</h1>
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
        <form action="homeAction">
            Search: <input type="search" name="txtSearch" value="${requestScope.SEARCH}"/>
            <input type="submit" name="btnAction" value="Search"/>
        </form>

        <c:set var="listActicle" value="${sessionScope.LIST}"/>
        <c:forEach var="acticle" items="${listActicle}">
            <div>
                <c:url var="articleDetail" value="articleDetailAction">
                    <c:param name="txtArticleId" value="${acticle.articleId}"/>
                </c:url>
                
                <a href="${articleDetail}"><h2> ${acticle.tittle} </h2></a>
                ${acticle.shortDescription} </br>
                Author: ${acticle.author} </br>
                Email: ${acticle.email} </br>
                Date: ${acticle.date} </br>
                ${acticle.content} </br>    
            </div>
        </c:forEach>
        
        <c:set var="pages" value="${requestScope.PAGES}" />
        <c:forEach var="i" begin="1" end="${pages}">
            <li <c:if test="${i == page}">active</c:if>>
                <c:url var="urlPaging" value="homeAction">
                    <c:param name="searchName" value="${param.txtSearch}"/>
                    <c:param name="btnAction" value="Search"/>
                    <c:param name="page" value="${i}"/>
                </c:url>
                <a href="${urlPaging}">${i}</a></li>
            </li>
        </c:forEach>
    </body>
</html>
