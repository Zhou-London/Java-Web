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
      background-color: #f4f1ea;
      color: #5a4d41;
    }
    h1 {
      text-align: center;
      color: #8b7d6b;
    }
    .header-row, .note-row {
      display: flex;
      padding: 10px;
      border-bottom: 1px solid #d9d2c9;
    }
    .header-row {
      font-weight: bold;
      background-color: #e6e0d4;
    }
    .note-row a, .note-row span, .header-row span {
      display: inline-block;
      text-align: center;
    }
    .index {
      flex: 0 0 15%;
    }
    .label {
      flex: 1;
    }
    .text {
      flex: 0 0 25%;
    }
    .timestamp {
      flex: 0 0 30%;
    }
    .note-row a {
      color: #a67f59;
      text-decoration: none;
    }
    .note-row a:hover {
      text-decoration: underline;
    }
    .back-button, select, input[type="submit"] {
      display: block;
      width: 200px;
      margin: 10px auto;
      padding: 10px;
      background-color: #a67f59;
      color: #f4f1ea;
      border: none;
      border-radius: 5px;
      font-family: 'Georgia', serif;
      font-size: 1.1em;
      text-align: center;
      cursor: pointer;
    }
    .back-button:hover, select:hover, input[type="submit"]:hover {
      background-color: #8b7d6b;
      text-decoration: underline;
    }
    .sort-form {
      display: flex;
      justify-content: center;
      gap: 10px;
    }
  </style>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h1>Notes</h1>
<form class="sort-form" action="/notesList.html" method="get">
  <select name="sortBy">
    <option value="index" <%= "index".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Sort by Index</option>
    <option value="time_newest" <%= "time_newest".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Sort by Time (Newest First)</option>
    <option value="time_oldest" <%= "time_oldest".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Sort by Time (Oldest First)</option>
  </select>
  <input type="submit" value="Sort">
</form>
<div>
  <div class="header-row">
    <span class="index">Index</span>
    <span class="label">Label</span>
    <span class="text">Text</span>
    <span class="timestamp">Time</span>
  </div>
  <%
    Model model = (Model) request.getAttribute("model");
    if (model != null && model.getNoteList() != null) {
      List<Note> originalNotes = model.getNoteList(); // 原始列表
      List<Note> notes = new java.util.ArrayList<>(originalNotes); // 创建副本用于排序
      String sortBy = request.getParameter("sortBy");

      // 只在首次加载（sortBy == null）时分配 index
      if (sortBy == null) {
        for (int i = 0; i < notes.size(); i++) {
          model.setNoteIndex(i, Integer.toString(i + 1));
        }
      }

      // 根据用户选择排序副本列表
      if ("index".equals(sortBy)) {
        notes.sort((n1, n2) -> Integer.parseInt(n1.getIndex()) - Integer.parseInt(n2.getIndex()));
      } else if ("time_newest".equals(sortBy)) {
        notes.sort((n1, n2) -> n2.getTimestamp().compareTo(n1.getTimestamp()));
      } else if ("time_oldest".equals(sortBy)) {
        notes.sort((n1, n2) -> n1.getTimestamp().compareTo(n2.getTimestamp()));
      }

      // 显示排序后的列表，使用原始索引查找对应位置
      for (int i = 0; i < notes.size(); i++) {
        Note currentNote = notes.get(i);
        String index = currentNote.getIndex();
        String label = currentNote.getLabel();
        String text = currentNote.getText();
        String timestamp = currentNote.getTimestamp().substring(0, 19);
        // 查找当前 Note 在原始列表中的位置
        int originalIndex = originalNotes.indexOf(currentNote);
        String href = "viewNoteInfo.html?index=" + originalIndex;
  %>
  <div class="note-row">
    <a href="<%= href %>" class="index"><%= index %></a>
    <span class="label"><%= label.length() > 25 ? label.substring(0, 25) + "..." : label %></span>
    <span class="text"><%= text.length() > 25 ? text.substring(0, 25) + "..." : text %></span>
    <span class="timestamp"><%= timestamp %></span>
  </div>
  <%
      }
    } else {
      out.println("<p style='text-align: center; color: #a67f59;'>No notes available.</p>");
    }
  %>
</div>
<button class="back-button" onclick="window.location.href='http://localhost:8080'">Back to Home</button>
</body>
</html>