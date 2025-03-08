<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Note Info</title>
</head>
<body>
<h1>Note Info</h1>
<%
  String label = (String) request.getAttribute("label");
  String text = (String) request.getAttribute("text");
  String index = (String) request.getAttribute("index");
%>
<p><%= index %></p>
<p><%= label %></p>
<p><%= text %></p>
</body>
</html>