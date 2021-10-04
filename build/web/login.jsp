<%-- 
    Document   : login
    Created on : Sep 29, 2021, 3:09:37 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN PAGE</title>
    </head>
    <body>
        <h2>Login page</h2>
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
        <form action="loginAction" method="POST">
            Email: <input type="text" name ="txtEmail" value=""/><br/>
            Password: <input type="password" name="txtPassword" value=""/><br/>
            <input type="submit" name="btnAction" value="Login"/><br/>
        </form>
        <br/><a href="registerPage">Register account</a><br/>  
        <c:if test="${not empty requestScope.MSGERROR}">
            ${requestScope.MSGERROR}
        </c:if>
    </body>
</html>
