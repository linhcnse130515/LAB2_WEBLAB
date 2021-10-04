<%-- 
    Document   : register
    Created on : Sep 29, 2021, 3:15:50 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function checkValidate() {
                var email = document.getElementById("txtEmail").value;
                var password = document.getElementById("txtPassword").value;
                var confirmPassword = document.getElementById("txtConfirm").value;
                var name = document.getElementById("txtName").value;
                if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
                    alert("You have entered an invalid email address!");
                    document.getElementById("txtEmail").focus();
                    return (false);
                }
                if (password.length < 6) {

                    alert("Password must be more than 6 characters");
                    document.getElementById("txtPassword").focus();
                    return false;
                }
                if (password !== confirmPassword) {
                    alert("Passwords do not match.");
                    document.getElementById("txtConfirm").focus();
                    return false;
                }
                if (!/^[a-zA-Z ]+$/.test(name) || name.length < 4) {
                    alert("You have entered an invalid name!");
                    document.getElementById("txtName").focus();
                    return (false);
                }
                return true;
            }
        </script> 
    </head>
    <body>    
        <h1>Register Page</h1>
        <form action="registerAction" method="POST">
            Email*: <input type="text" name="txtEmail" value="" ><br>
            Password*: <input type="password" name="txtPassword" value="" ><br>
            Confirm* : <input type="password" name="txtConfirm" value="" ><br>
            Name*: <input type="text" name="txtName" value="" ><br>
            <input type="submit" name="btnAction" value="Register" onclick="return checkValidate()">
            <input type="reset" value="Reset" ><br>
        </form>
        <c:if test="${not empty requestScope.MSGSUCCESS}">
            ${requestScope.MSGSUCCESS}
        </c:if>
    </body>
</html>
