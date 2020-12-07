<%--
  Created by IntelliJ IDEA.
  User: pysga1996
  Date: 7/21/19
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Sandwich Condiments</title>
</head>
<body>
<fieldset>
    <legend><h2>Sandwich Condiments</h2></legend>
    <ul>
        <c:forEach var="condiment" items="${condimentsList}">
            <li><c:out value = "${condiment.name}"/></li>
        </c:forEach>
    </ul>

</fieldset>
</body>
</html>

