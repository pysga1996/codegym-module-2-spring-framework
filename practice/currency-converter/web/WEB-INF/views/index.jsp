<%--
  Created by IntelliJ IDEA.
  User: pysga1996
  Date: 7/20/19
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency Converter</title>
    <style>
        fieldset {
            width: 250px;
            height: 150px;
        }
    </style>
</head>
<body>
<fieldset>
    <legend><h2>Currency Converter</h2></legend>
    <form action="<%= application.getContextPath() %>/result" method="post">
        <label for="usd">USD: </label><input type="number" name="usd" id="usd" value="${usd}" step="any"><br>
        <label for="rate">Rate</label><input type="number" name="rate" id="rate" value="${rate}" step="any"><br>
        <span>VND: </span><span>${vnd}</span><br>
        <input type="submit" value="Submit">
    </form>
</fieldset>
</body>
</html>
