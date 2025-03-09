<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Note Info</title>
  <style>
    body {
      max-width: 800px;
      margin: 0 auto;
      padding: 20px;
      font-family: 'Georgia', serif;
      background-color: #f4f1ea; /* 淡黄色背景 */
      color: #5a4d41; /* 深棕色文字 */
    }
    h1 {
      text-align: center;
      color: #8b7d6b; /* 棕色标题 */
      padding-bottom: 10px;
      border-bottom: 1px solid #d9d2c9; /* 浅棕色边框 */
    }
    h2 {
      color: #8b7d6b;
      margin-top: 30px;
      padding-bottom: 5px;
      border-bottom: 1px dashed #d9d2c9; /* 虚线边框 */
    }
    p {
      background-color: #e6e0d4; /* 更深的淡黄色背景 */
      padding: 10px;
      margin: 10px 0;
      border: 1px solid #d9d2c9;
      border-radius: 5px; /* 圆角 */
    }
    form {
      display: flex;
      flex-direction: column;
      gap: 15px;
      max-width: 500px;
      margin: 0 auto;
    }
    label {
      color: #5a4d41;
      font-weight: bold;
    }
    input[type="text"], textarea {
      width: 100%;
      padding: 8px;
      border: 1px solid #d9d2c9;
      border-radius: 5px;
      background-color: #fffcf7; /* 浅羊皮色输入框 */
      font-family: 'Georgia', serif;
      color: #5a4d41;
      box-sizing: border-box; /* 确保宽度包含边框 */
    }
    textarea {
      height: 100px;
      resize: vertical; /* 允许垂直调整大小 */
    }
    input[type="submit"], button {
      padding: 10px;
      background-color: #a67f59; /* 棕色按钮 */
      color: #f4f1ea; /* 淡黄色文字 */
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-family: 'Georgia', serif;
      font-size: 1.1em;
    }
    input[type="submit"]:hover, button:hover {
      background-color: #8b7d6b; /* 悬停时稍深 */
      text-decoration: underline;
    }
    button {
      margin-top: 20px;
      width: 200px;
      display: block;
      margin-left: auto;
      margin-right: auto;
    }
  </style>
</head>
<body>
<h1>Note Info</h1>
<%
  Note note =(Note) request.getAttribute("note");
  String label = note.getLabel();
  String text = note.getText();
  String index = note.getIndex(); //index is rowIndex + 1. Calculated already.
  String rowIndex = request.getParameter("index"); // Start from 0
%>
<p><%= index %></p>
<p><%= label %></p>
<p><%= text %></p>

<h2>Edit this note</h2>
<form action="/viewNoteInfo.html" method="post">
  <input type="hidden" name="rowIndex" value="<%= rowIndex %>">
  <label for="label">Label:</label>
  <input type="text" id="label" name="label" value="<%= label %>">
  <label for="text">Text:</label>
  <textarea id="text" name="text"><%= text %></textarea>
  <input type="submit" value="Save Changes">
</form>

<button onclick="window.location.href='http://localhost:8080/notesList.html'">Back to Notes List</button>
</body>
</html>