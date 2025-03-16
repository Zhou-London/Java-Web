<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Note.Note" %>
<%@ page import="uk.ac.ucl.model.NoteType" %>
<%@ page import="uk.ac.ucl.model.Note.LinkNote" %>
<%@ page import="uk.ac.ucl.model.Note.ImageNote" %>
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
    .note-info {
      background-color: #e6e0d4;
      padding: 15px;
      margin: 10px 0;
      border: 1px solid #d9d2c9;
      border-radius: 5px;
    }
    p {
      margin: 10px 0;
      display: flex;
      justify-content: space-between;
      align-items: center;
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
    /* 图片特定样式 */
    .image-container {
      max-width: 100%;
      overflow: hidden;
    }
    .note-image {
      max-width: 100%;
      max-height: 400px; /* 设置最大高度 */
      width: auto;
      height: auto;
      object-fit: contain; /* 保持图片比例 */
      border-radius: 5px;
      display: block;
      margin: 0 auto;
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
      transition: background-color 0.3s;
    }
    input[type="submit"]:hover, button:hover {
      background-color: #8b7d6b;
      text-decoration: underline;
    }
    .button-container {
      display: flex;
      justify-content: center;
      gap: 20px;
      margin-top: 20px;
    }
    .delete-form {
      margin-top: 20px;
    }
    button.back-button {
      width: 200px;
      margin: 30px auto 0;
      display: block;
    }
    a {
      color: #a67f59;
      text-decoration: none;
    }
    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<h1>Note Info</h1>
<div class="note-info">
  <%
    Note note = (Note) request.getAttribute("note");
    String label = note.getLabel();
    String text = note.getText();
    Integer key = note.getID();
    String timestamp = note.getTimestamp();

    // Link Note
    if (note.getNoteType() == NoteType.LINK) {
      String eLink = ((LinkNote)note).getLink();
  %>
  <p><span class="label">Key:</span><span class="value"><%= key %></span></p>
  <p><span class="label">Label:</span><span class="value"><%= label %></span></p>
  <p><span class="label">Text:</span><span class="value"><%= text %></span></p>
  <p><span class="label">Link:</span><span class="value"><a href="<%= eLink %>" target="_blank"><%= eLink %></a></span></p>
  <p><span class="label">Timestamp:</span><span class="value"><%= timestamp %></span></p>
  <%
    // Image Note
  } else if (note.getNoteType() == NoteType.IMAGE) {
    String imageUrl = ((ImageNote)note).getImage();
  %>
  <p><span class="label">Key:</span><span class="value"><%= key %></span></p>
  <p><span class="label">Label:</span><span class="value"><%= label %></span></p>
  <p><span class="label">Picture:</span>
    <span class="value image-container">
                <img src="<%= imageUrl %>" alt="<%= label %>" class="note-image">
            </span>
  </p>
  <p><span class="label">Text:</span><span class="value"><%= text %></span></p>
  <p><span class="label">Timestamp:</span><span class="value"><%= timestamp %></span></p>
  <%
  } else { // Default Note
  %>
  <p><span class="label">Key:</span><span class="value"><%= key %></span></p>
  <p><span class="label">Label:</span><span class="value"><%= label %></span></p>
  <p><span class="label">Text:</span><span class="value"><%= text %></span></p>
  <p><span class="label">Timestamp:</span><span class="value"><%= timestamp %></span></p>
  <%
    }
  %>
</div>

<h2>Edit this note</h2>
<form action="/viewNoteInfo.html" method="post">
  <input type="hidden" name="key" value="<%= key %>">
  <label for="label">Label:</label>
  <input type="text" id="label" name="label" value="<%= label %>">
  <label for="text">Text:</label>
  <textarea id="text" name="text"><%= text %></textarea>
  <div class="button-container">
    <input type="submit" value="Save Changes">
  </div>
</form>

<form action="/deleteNote.html" method="post" class="delete-form">
  <input type="hidden" name="key" value="<%= key %>">
  <div class="button-container">
    <input type="submit" value="Delete Note">
  </div>
</form>

<button class="back-button" onclick="window.location.href='http://localhost:8080/notesList.html'">Back to Notes List</button>
</body>
</html>