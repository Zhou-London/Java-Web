<%@ page import="uk.ac.ucl.model.Note" %>
<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Model" %>
<%@ page import="uk.ac.ucl.model.ModelFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Notes</title>
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
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #d9d2c9;
            border-radius: 5px;
            background-color: #fffcf7;
            font-family: 'Georgia', serif;
            color: #5a4d41;
            box-sizing: border-box;
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
        .note-row {
            display: flex;
            padding: 10px;
            border-bottom: 1px solid #d9d2c9;
            background-color: #e6e0d4;
        }
        .note-row a, .note-row span {
            display: inline-block;
            text-align: center;
        }
        .note-row a {
            color: #a67f59;
            text-decoration: none;
        }
        .note-row a:hover {
            text-decoration: underline;
        }
        .index { flex: 0 0 15%; }
        .label { flex: 1; }
        .text { flex: 0 0 25%; }
        .timestamp { flex: 0 0 30%; }
    </style>
</head>
<body>
<h1>Search for a Note</h1>
<form action="/searchNotes.html" method="post">
    <label for="keyword">Keyword:</label>
    <input type="text" id="keyword" name="keyword" placeholder="Enter a keyword to search">
    <input type="submit" value="Search">
</form>
<%
    Model model = ModelFactory.getModel();
    List<Note> originalNotes = model.getNoteList();
    List<Note> results = (List<Note>) request.getAttribute("results");
    if (results != null && !results.isEmpty()) {
        for (Note note : results) {
            String index = note.getIndex();
            String label = note.getLabel();
            String text = note.getText();
            String timestamp = note.getTimestamp();
            int originalIndex = originalNotes.indexOf(note);
            String href = "http://localhost:8080/viewNoteInfo.html?index=" + originalIndex;
%>
<div class="note-row">
    <a href="<%= href %>" class="index"><%= index %></a>
    <span class="label"><%= label.length() > 25 ? label.substring(0, 25) + "..." : label %></span>
    <span class="text"><%= text.length() > 25 ? text.substring(0, 25) + "..." : text %></span>
    <span class="timestamp"><%= timestamp.substring(0, 19) %></span>
</div>
<%
        }
    }
%>
<button onclick="window.location.href='http://localhost:8080/notesList.html'">Back to Notes List</button>
</body>
</html>