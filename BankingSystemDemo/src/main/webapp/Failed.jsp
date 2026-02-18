<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String msg = (String)request.getAttribute("msg"); %> 
<center>
	<h1> <%=msg %></h1>
	<jsp:include page="Homepage.html"></jsp:include>
</center>

</body>
</html>