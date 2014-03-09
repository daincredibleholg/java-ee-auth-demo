<%--
  Created by IntelliJ IDEA.
  User: hsteinhauer
  Date: 08.03.14
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form id="loginForm" name="loginForm" method="post" action="j_security_check">
        User Name : <input id="username" type="text" name="j_username" class="textbox"></input>
        Password : <input id="password" type="password" name="j_password" class="textbox"></input>
        <input name="login" type="submit" value="LOGIN" id="submit" class="button blue">
    </form>
</body>
</html>
