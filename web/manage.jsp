<%-- 
    Document   : manage
    Created on : Oct 3, 2021, 9:32:30 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Page</title>
    </head>
    <body>
        <h1>Manage Page</h1>
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
        <form action="searchAction">
            Search: <input type="search" name="txtSearch" value="${requestScope.SEARCH}"/>
            <select name="slStatus"> 
                <option value=""
                        <c:if test="${'' eq param.slStatus}">selected</c:if>>
                    all
                </option>
                <option value="new"
                        <c:if test="${'new' eq param.slStatus}">selected</c:if>>
                    new
                </option>
                <option value="active"
                        <c:if test="${'active' eq param.slStatus}">selected</c:if>>
                    active
                </option>
                <option value="delete"
                        <c:if test="${'delete' eq param.slStatus}">selected</c:if>>
                    delete
                </option>
            </select>
            <input type="submit" name="btnAction" value="Search"/>
        </form>
        ${requestScope.MESSAGE}    
                    
        <c:set var="listActicle" value="${requestScope.LISTALL}"/>
        <c:forEach var="acticle" items="${listActicle}">
            <div>
                <h2>TITLE: ${acticle.tittle} </h2>
                ${acticle.shortDescription} </br>
                Author ${acticle.author} </br>
                Post Date: ${acticle.date} </br>
                Status: ${acticle.status} </br>
                Email: ${acticle.email} </br>
                Content: ${acticle.content} </br> 

                <c:url var="active" value="updateStatusAction">
                    <c:param name="txtSearch" value="${requestScope.SEARCH}"/>
                    <c:param name="slStatus" value="${requestScope.STATUS}"/>
                    <c:param name="txtArticleId" value="${acticle.articleId}"/>
                    <c:param name="txtStatus" value="active"/>
                </c:url>
                <c:url var="delete" value="updateStatusAction">
                    <c:param name="txtSearch" value="${requestScope.SEARCH}"/>
                    <c:param name="slStatus" value="${requestScope.STATUS}"/>
                    <c:param name="txtArticleId" value="${acticle.articleId}"/>
                    <c:param name="txtStatus" value="delete"/>
                </c:url>
                <c:if test="${acticle.status == 'active'}">                 
                    <a href="${delete}"><h4>Delete</h4></a>
                </c:if>

                <c:if test="${acticle.status == 'new'}">
                    <a href="${active}"><h4>Active </h4></a>
                    <a href="${delete}"><h4>Delete</h4></a>
                </c:if>
                
                <c:if test="${acticle.status == 'delete'}">
                    <a href="${active}"><h4>Active </h4></a>
                </c:if>
            </div>
        </c:forEach>
        
        <c:set var="pages" value="${requestScope.PAGES}" />
        <c:forEach var="i" begin="1" end="${pages}">
            <li <c:if test="${i == page}">active</c:if>>
                <c:url var="urlPaging" value="searchAction">
                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                    <c:param name="slStatus" value="${param.slStatus}"/>
                    <c:param name="btnAction" value="Search"/>
                    <c:param name="page" value="${i}"/>
                </c:url>
                <a href="${urlPaging}">${i}</a></li>
            </li>
        </c:forEach>
    </body>
</html>
