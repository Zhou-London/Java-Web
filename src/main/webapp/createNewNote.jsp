<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Note</title>
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
            margin: 20px auto;
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
        input[type="submit"], .back-button {
            padding: 10px;
            background-color: #a67f59;
            color: #f4f1ea;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-family: 'Georgia', serif;
            font-size: 1.1em;
            text-align: center;
        }
        input[type="submit"]:hover, .back-button:hover {
            background-color: #8b7d6b;
            text-decoration: underline;
        }
        .back-button {
            display: block;
            width: 200px;
            margin: 20px auto 0;
        }
    </style>
</head>
<body>
<h1>Create a New Note</h1>
<form action="/createNewNote.html" method="post">
    <label for="label">Label:</label>
    <input type="text" id="label" name="label" placeholder="Enter note label">
    <label for="text">Text:</label>
    <textarea id="text" name="text" placeholder="Enter note text"></textarea>
    <input type="submit" value="Create Note">
</form>
<button class="back-button" onclick="window.location.href='http://localhost:8080'">Back to Home</button>
</body>
</html>