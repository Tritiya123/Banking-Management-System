<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transaction Status</title>

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

    <!-- Prevent direct access -->
    <%
        if(request.getAttribute("msg") == null){
            response.sendRedirect("Login.html");
            return;
        }
    %>

    <!-- Display message -->
    <h2 class="${msg.contains('Success') ? 'success' : 'error'}">
        ${msg}
    </h2>

    <hr>

    <!-- Navigation buttons -->
    <a href="LoginSuccess.jsp">Back to Dashboard</a>

</div>

</body>
</html>
