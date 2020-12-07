<%--
  Created by IntelliJ IDEA.
  User: pysga1996
  Date: 7/20/19
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple Dictionary</title>
    <style>
        fieldset {
            width: 250px;
            height: 150px;
        }
    </style>
</head>
<body>
<fieldset>
    <legend><h2>Simple Dictionary</h2></legend>
    <form action="${pageContext.request.contextPath}/search" method="post">
        <label for="word"></label><input type="text" id="word" name="word" value="${word}"><br>
        <span>Meaning: </span><span>${meaning}</span><br>
        <input type="submit" value="Search">
    </form>
</fieldset>
</body>
</html>
