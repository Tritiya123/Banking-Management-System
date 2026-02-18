<%@ page import="java.util.List, com.test.Transaction" %>

<%
    List<Transaction> list = (List<Transaction>) request.getAttribute("list");
%>

<h2>Mini Statement</h2>

<table border="1" cellpadding="10">
<tr>
    <th>Type</th>
    <th>Amount</th>
    <th>Date</th>
</tr>

<%
    for(Transaction t : list){
%>
<tr>
    <td><%= t.getType() %></td>
    <td><%= t.getAmount() %></td>
    <td><%= t.getDate() %></td>
</tr>
<%
    }
%>

</table>

<br>
<a href="LoginSuccess.jsp">Back to Dashboard</a>
