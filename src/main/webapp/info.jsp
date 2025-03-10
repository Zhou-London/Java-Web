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
      background-color: #f4f1ea;
      color: #5a4d41;
    }
    h1 {
      text-align: center;
      color: #8b7d6b;
      padding-bottom: 10px;
      border-bottom: 1px solid #d9d2c9;
    }
    h2 {
      color: #8b7d6b;
      margin-top: 30px;
      padding-bottom: 5px;
      border-bottom: 1px dashed #d9d2c9;
    }
    p {
      background-color: #e6e0d4;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid #d9d2c9;
      border-radius: 5px;
      display: flex;
      justify-content: space-between;
    }
    p span.label {
      font-weight: bold;
      color: #8b7d6b;
      flex: 0 0 100px;
    }
    p span.value {
      flex: 1;
      text-align: left;
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
      background-color: #fffcf7;
      font-family: 'Georgia', serif;
      color: #5a4d41;
      box-sizing: border-box;
    }
    textarea {
      height: 100px;
      resize: vertical;
    }
    input[type="submit"], button {
      padding: 10px;
      background-color: #a67f59;
      color: #f4f1ea;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-family: 'Georgia', serif;
      font-size: 1.1em;
    }
    input[type="submit"]:hover, button:hover {
      background-color: #8b7d6b;
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
  Note note = (Note) request.getAttribute("note");
  String label = note.getLabel();
  String text = note.getText();
  String index = note.getIndex();
  String rowIndex = request.getParameter("index");
  String timestamp = note.getTimestamp();
%>
<p><span class="label">Index:</span><span class="value"><%= index %></span></p>
<p><span class="label">Label:</span><span class="value"><%= label %></span></p>
<p><span class="label">Text:</span><span class="value"><%= text %></span></p>
<p><span class="label">Timestamp:</span><span class="value"><%= timestamp %></span></p>

<h2>Edit this note</h2>
<form action="/viewNoteInfo.html" method="post">
  <input type="hidden" name="rowIndex" value="<%= rowIndex %>">
  <label for="label">Label:</label>
  <input type="text" id="label" name="label" value="<%= label %>">
  <label for="text">Text:</label>
  <textarea id="text" name="text"><%= text %></textarea>
  <input type="submit" value="Save Changes">
</form>

<form action="/deleteNote.html" method="post">
  <input type="hidden" name="rowIndex" value="<%= rowIndex %>">
  <input type="submit" value="Delete Note">
</form>

<button onclick="window.location.href='http://localhost:8080/notesList.html'">Back to Notes List</button>
</body>
</html>