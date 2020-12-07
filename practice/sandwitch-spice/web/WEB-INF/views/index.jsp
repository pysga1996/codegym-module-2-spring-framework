<%--
  Created by IntelliJ IDEA.
  User: pysga1996
  Date: 7/21/19
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>Sandwich Condiments</title>
  </head>
  <body>
  <fieldset>
    <legend><h2>Sandwich Condiments</h2></legend>
    <form action="save" method="post">
      <input type="checkbox" name="condiments" id="lettuce" value="Lettuce"><label for="lettuce">Lettuce</label>
      <input type="checkbox" name="condiments" id="tomato" value="Tomato"><label for="tomato">Tomato</label>
      <input type="checkbox" name="condiments" id="mustard" value="Mustard"><label for="mustard">Mustard</label>
      <input type="checkbox" name="condiments" id="sprouts" value="Sprouts"><label for="sprouts">Sprouts</label><br>
      <input type="submit" value="Save">
    </form>
  </fieldset>
  </body>
</html>
