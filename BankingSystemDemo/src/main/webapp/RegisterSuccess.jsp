<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // 🔐 Prevent direct access (no servlet call)
    if(request.getAttribute("msg") == null){
        response.sendRedirect("register.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Status</title>

<style>
    body{
        font-family: Arial, sans-serif;
        background: linear-gradient(to right, #4facfe, #00f2fe);
        text-align: center;
        padding-top: 100px;
    }

    .box{
        background: white;
        padding: 30px;
        width: 400px;
        margin: auto;
        border-radius: 10px;
        box-shadow: 0px 0px 10px gray;
    }

    .success{
        color: green;
    }

    .error{
        color: red;
    }

    a{
        text-decoration: none;
        display: inline-block;
        margin-top: 15px;
        padding: 8px 15px;
        background: #007BFF;
        color: white;
        border-radius: 5px;
    }
</style>
</head>

<body>

<div class="box">

    <!-- Display message -->
    <h2 class="success">${msg}</h2>

    <hr>

    <!-- Include login form -->
    <jsp:include page="Login.html"></jsp:include>

    <br>
    <a href="register.jsp">Go Back to Registration</a>

</div>

</body>
</html>
