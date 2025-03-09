<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Note" %>
<%@ page import="uk.ac.ucl.model.Model" %>
<%@ page import="uk.ac.ucl.model.ModelFactory" %>
<%@ page import="org.w3c.dom.Node" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Notes App</title>
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
    }
    .header-row, .note-row {
      display: flex;
      padding: 10px;
      border-bottom: 1px solid #d9d2c9; /* 浅棕色边框 */
    }
    .header-row {
      font-weight: bold;
      background-color: #e6e0d4; /* 更深的淡黄色 */
    }
    .note-row a, .note-row span, .header-row span {
      display: inline-block;
    }
    .index {
      flex: 0 0 20%;
      text-align: left;
    }
    .label {
      flex: 1;
      text-align: center;
    }
    .text {
      flex: 0 0 20%;
      text-align: right;
    }
    .note-row a {
      color: #a67f59; /* 棕色链接 */
      text-decoration: none;
    }
    .note-row a:hover {
      text-decoration: underline;
    }
    .back-button {
      display: block;
      width: 200px;
      margin: 20px auto 0; /* 居中并与上方内容间隔 */
      padding: 10px;
      background-color: #a67f59; /* 棕色按钮 */
      color: #f4f1ea; /* 淡黄色文字 */
      border: none;
      border-radius: 5px; /* 圆角 */
      font-family: 'Georgia', serif;
      font-size: 1.1em;
      text-align: center;
      cursor: pointer;
    }
    .back-button:hover {
      background-color: #8b7d6b; /* 悬停时稍深 */
      text-decoration: underline;
    }
  </style>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h1>Notes</h1>
<div>
  <div class="header-row">
    <span class="index">Index</span>
    <span class="label">Label</span>
    <span class="text">Text</span>
  </div>
  <%
    //List<Note> notes = (List<Note>) request.getAttribute("notes");
    Model model = (Model) request.getAttribute("model");
    for (int i = 0; i < model.getNoteList().size(); i++) {
      String index = model.getNoteList().get(i).getIndex();
      String label = model.getNoteList().get(i).getLabel();
      String text = model.getNoteList().get(i).getText();
      String href = "viewNoteInfo.html?index=" + i;
      model.setNoteIndex(i, Integer.toString(i+1));
  %>
  <div class="note-row">
    <a href="<%= href %>" class="index"><%= i+1 %></a>
    <span class="label"><%= label.length() > 25 ? label.substring(0,25) + "..." : label %></span>
    <span class="text"><%= text.length() > 25 ? text.substring(0,25) + "..." : text %></span>
  </div>
  <% } %>
</div>
<button class="back-button" onclick="window.location.href='http://localhost:8080'">Back to Home</button>
<jsp:include page="/footer.jsp"/>
</body>
</html>