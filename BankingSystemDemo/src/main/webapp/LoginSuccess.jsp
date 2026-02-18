<%@ page contentType="text/html;charset=UTF-8" %>

<%
    // Prevent direct access
    if(request.getAttribute("fname") == null){
        response.sendRedirect("Login.html");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Secure Bank - Dashboard</title>
</head>

<body>

<!-- HEADER -->
<table width="100%" bgcolor="#1976d2" cellpadding="15">
    <tr>
        <td>
            <font color="white" size="5"><b>Secure Bank</b></font>
        </td>
        <td align="right">
            <a href="LogoutServlet">
                <font color="white">Logout</font>
            </a>
        </td>
    </tr>
</table>

<!-- USER WELCOME -->
<p align="right"><b>Welcome : ${empty fname ? "User" : fname}</b></p>

<!-- SUCCESS MESSAGE -->
<center>
    <h2 style="color:green;">${msg}</h2>
</center>

<br><br>

<!-- DASHBOARD SECTION -->
<center>
    <h2>Welcome to Your Bank Account</h2>
    <p>Please select an operation</p>

    <table cellpadding="20" cellspacing="20">

        <tr>
            <td align="center" bgcolor="#e3f2fd">
                <h3>Deposit</h3>
                <a href="deposit.html">Proceed</a>
            </td>

            <td align="center" bgcolor="#e3f2fd">
                <h3>Withdraw</h3>
                <a href="withdraw.html">Proceed</a>
            </td>

            <td align="center" bgcolor="#e3f2fd">
                <h3>Fund Transfer</h3>
                <a href="transfer.html">Proceed</a>
            </td>
        </tr>

        <tr>
            <td align="center" bgcolor="#e3f2fd">
                <h3>Check Balance</h3>
                <a href="balance.html">Proceed</a>
            </td>

            <td align="center" bgcolor="#e3f2fd">
                <h3>Mini Statement</h3>
                <a href="ministatement.html">Proceed</a>
            </td>

            <td></td>
        </tr>

    </table>
</center>

<br><br>

<center>
    <font color="gray">
        © 2026 Secure Bank | Banking Management System
    </font>
</center>

</body>
</html>
