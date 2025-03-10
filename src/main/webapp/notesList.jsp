<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Note.Note" %>
<%@ page import="uk.ac.ucl.model.Model" %>
<%@ page import="uk.ac.ucl.model.ModelFactory" %>
<%@ page import="org.w3c.dom.Node" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="uk.ac.ucl.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Notes App</title>
  <style>
    body {
      max-width: 900px;
      margin: 0 auto;
      padding: 20px;
      font-family: 'Georgia', serif;
      background-color: #f4f1ea;
      color: #5a4d41;
    }
    h1 {
      text-align: center;
      color: #8b7d6b;
      padding-bottom: 10px;
      border-bottom: 2px solid #d9d2c9;
    }
    .controls {
      display: flex;
      justify-content: space-between;
      align-items: center;
      gap: 20px;
      margin: 20px 0;
      flex-wrap: wrap;
    }
    .filter-form, .sort-form {
      display: flex;
      align-items: center;
      gap: 10px;
    }
    .notes-table {
      border: 1px solid #d9d2c9;
      border-radius: 5px;
      overflow: hidden;
    }
    .header-row, .note-row {
      display: flex;
      padding: 12px;
      border-bottom: 1px solid #d9d2c9;
    }
    .header-row {
      font-weight: bold;
      background-color: #e6e0d4;
    }
    .note-row:last-child {
      border-bottom: none;
    }
    .note-row:hover {
      background-color: #fffcf7;
    }
    .key, .category, .label, .text, .timestamp {
      flex: 1;
      text-align: center;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    .key { flex: 0 0 10%; }
    .category { flex: 0 0 20%; }
    .label { flex: 0 0 25%; }
    .text { flex: 0 0 25%; }
    .timestamp { flex: 0 0 20%; }
    .note-row a {
      color: #a67f59;
      text-decoration: none;
    }
    .note-row a:hover {
      text-decoration: underline;
    }
    select, input[type="submit"], .back-button {
      padding: 8px 16px;
      background-color: #a67f59;
      color: #f4f1ea;
      border: none;
      border-radius: 5px;
      font-family: 'Georgia', serif;
      cursor: pointer;
      transition: background-color 0.3s;
    }
    select:hover, input[type="submit"]:hover, .back-button:hover {
      background-color: #8b7d6b;
    }
    label {
      color: #5a4d41;
      font-weight: bold;
    }
    .back-button {
      display: block;
      width: 200px;
      margin: 20px auto;
      text-attachment: center;
    }
    .no-notes {
      text-align: center;
      color: #a67f59;
      padding: 20px;
    }
  </style>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h1>Notes</h1>

<div class="controls">
  <form action="/notesList.html" method="get" class="filter-form">
    <label for="categoryFilter">Filter by Category:</label>
    <select id="categoryFilter" name="categoryFilter" onchange="this.form.submit()">
      <option value="">All Categories</option>
      <%
        String selectedCategory = request.getParameter("categoryFilter");
        for (Category category : Category.values()) {
          String displayName = category.getDisplayName();
          String selected = displayName.equals(selectedCategory) ? "selected" : "";
      %>
      <option value="<%= displayName %>" <%= selected %>><%= displayName %></option>
      <% } %>
    </select>
  </form>

  <form action="/notesList.html" method="get" class="sort-form">
    <label for="sortBy">Sort by:</label>
    <select id="sortBy" name="sortBy" onchange="this.form.submit()">
      <option value="key" <%= "key".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Key</option>
      <option value="timestamp" <%= "timestamp".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Timestamp</option>
      <option value="label" <%= "label".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Label</option>
      <option value="category" <%= "category".equals(request.getParameter("sortBy")) ? "selected" : "" %>>Category</option>
    </select>
    <input type="hidden" name="categoryFilter" value="<%= selectedCategory != null ? selectedCategory : "" %>">
  </form>
</div>

<div class="notes-table">
  <div class="header-row">
    <span class="key">Key</span>
    <span class="category">Category</span>
    <span class="label">Label</span>
    <span class="text">Text</span>
    <span class="timestamp">Time</span>
  </div>
  <%
    HashMap<Integer, Note> notes = (HashMap<Integer, Note>) request.getAttribute("notes");
    if (notes != null && !notes.isEmpty()) {
      for (Map.Entry<Integer, Note> entry : notes.entrySet()) {
        Note thisNote = entry.getValue();
        Integer thisKey = entry.getKey();
        String label = thisNote.getLabel();
        String text = thisNote.getText();
        String timestamp = thisNote.getTimestamp();
        Category category = thisNote.getCategory();
        String categoryDisplayName = category.getDisplayName();
        String href = "/viewNoteInfo.html?key=" + thisKey;
  %>
  <div class="note-row">
    <a href="<%= href %>" class="key"><%= thisKey %></a>
    <span class="category"><%= categoryDisplayName %></span>
    <span class="label"><%= label.length() > 25 ? label.substring(0, 25) + "..." : label %></span>
    <span class="text"><%= text.length() > 25 ? text.substring(0, 25) + "..." : text %></span>
    <span class="timestamp"><%= timestamp.substring(5, 16) %></span>
  </div>
  <%
    }
  } else {
  %>
  <div class="no-notes">No notes available.</div>
  <%
    }
  %>
</div>

<button class="back-button" onclick="window.location.href='http://localhost:8080'">Back to Home</button>
</body>
</html>