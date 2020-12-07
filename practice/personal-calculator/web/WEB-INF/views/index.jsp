<%--
  Created by IntelliJ IDEA.
  User: pysga1996
  Date: 7/21/19
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Calculator</title>
    <style>
      * {
        margin: 0;
      }
      fieldset {
        position: relative;
        width: 380px;
        height: 120px;
        left: 20px;
        top: 20px;
      }
      #div1 {
        position: absolute;
        width: 150px;
        top: 35px;
        left: 15px;
      }
      #div2 {
        width: 150px;
        position: absolute;
        top: 35px;
        right: 52px;
      }
      #div3 {
        position: absolute;
        top: 80px;
        left: 15px;
      }
    </style>
  </head>
  <body>
  <fieldset>
    <legend><h2>Calculator</h2></legend>
    <form action="${pageContext.request.contextPath}/result" method="post">
      <div id="div1">
        <label for="first_operand">First Operand</label><br>
        <input type="number" step="any" name="first_operand" id="first_operand" value="${first_operand}">
      </div>
      <div id="div2">
        <label for="second_operand" id="label2">Second Operand</label><br>
        <input type="number" step="any" name="second_operand" id="second_operand" value="${second_operand}">
      </div>
      <div id="div3">
        <input name="action" type="submit" value="Addition(+)">
        <input name="action" type="submit" value="Subtraction(-)">
        <input name="action" type="submit" value="Multiplication(*)">
        <input name="action" type="submit" value="Division(/)"><br>
        <span>Result </span><span>${operator}: </span><span>${result}</span>
      </div>
    </form>
  </fieldset>
  </body>
</html>
