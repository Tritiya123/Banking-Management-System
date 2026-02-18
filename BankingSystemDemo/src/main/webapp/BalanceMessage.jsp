<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transfer Result</title>

    <style>
        body{
            font-family: Arial, sans-serif;
            text-align: center;
            padding-top: 100px;
            background: #f2f2f2;
        }

        .box{
            background: white;
            padding: 30px;
            width: 400px;
            margin: auto;
            border-radius: 10px;
            box-shadow: 0px 0px 10px gray;
        }

        .success{ color: green; }
        .error{ color: red; }

        a{
            text-decoration: none;
            padding: 8px 15px;
            background: #007BFF;
            color: white;
            border-radius: 5px;
        }
    </style>
</head>

<body>

<div class="box">

    <!-- prevent direct access -->
    <%
        if(request.getAttribute("msg") == null){
            response.sendRedirect("Login.html");
            return;
        }
    %>

    <!-- show message -->
    <h2 class="${msg.contains('Success') ? 'success' : 'error'}">
        ${msg}
    </h2>

    <br><br>

    <a href="LoginSuccess.jsp">Back to Dashboard</a>

</div>

</body>
</html>
