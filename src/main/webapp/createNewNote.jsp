<%@ page import="uk.ac.ucl.model.Category" %>
<!DOCTYPE html>
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
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
            max-width: 500px;
            margin: 20px auto;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], textarea, select {
            width: 100%;
            padding: 8px;
            border: 1px solid #d9d2c9;
            border-radius: 5px;
            background-color: #fffcf7;
            color: #5a4d41;
            box-sizing: border-box;
            font-family: 'Georgia', serif;
        }
        textarea {
            height: 100px;
            resize: vertical;
        }
        select {
            appearance: none;
            background-image: url('data:image/svg+xml;charset=UTF-8,<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="%235a4d41"><polygon points="0,0 20,0 10,10"/></svg>');
            background-repeat: no-repeat;
            background-position: right 10px top 50%;
            background-size: 12px;
            cursor: pointer;
        }
        input[type="submit"], .back-button {
            padding: 10px;
            background-color: #a67f59;
            color: #f4f1ea;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }
        input[type="submit"]:hover, .back-button:hover {
            background-color: #8b6a47;
            text-decoration: underline;
        }
        .back-button {
            text-align: center;
            width: 200px;
            margin: 20px auto;
            display: block;
        }
        #special-field, #image-field {
            display: none;
        }
        #drop-zone {
            border: 2px dashed #d9d2c9;
            padding: 20px;
            text-align: center;
            background-color: #fffcf7;
            border-radius: 5px;
            min-height: 100px;
            cursor: pointer;
        }
        #drop-zone.dragover {
            background-color: #e6e2d9;
            border-color: #a67f59;
        }
        #image-preview {
            margin-top: 10px;
            text-align: center;
        }
        #image-preview img {
            max-width: 100%;
            max-height: 300px;
            object-fit: contain;
            border-radius: 5px;
        }
        .error-message {
            color: #a94442;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h1>Create a New Note</h1>
<form action="/createNewNote.html" method="post" id="noteForm">
    <label for="label">Label:</label>
    <input type="text" id="label" name="label" placeholder="Enter note label">

    <label for="category">Category:</label>
    <select id="category" name="category">
        <% for (Category c : Category.values()) { %>
        <option value="<%= c.getDisplayName() %>"><%= c.getDisplayName() %></option>
        <% } %>
    </select>

    <label for="type">Type:</label>
    <select id="type" name="type">
        <option value="DEFAULT">Default Note</option>
        <option value="LINK">Note with external link</option>
        <option value="IMAGE">Note with picture</option>
    </select>

    <div id="special-field">
        <label for="special">External Link</label>
        <input type="text" id="special" name="special" placeholder="URL of anything">
    </div>

    <div id="image-field">
        <label>Image Upload</label>
        <div id="drop-zone">
            Drag and drop an image here<br>
            <small>(or click to select, max 5MB)</small>
            <input type="file" id="image-input" accept="image/*" style="display: none;">
        </div>
        <input type="hidden" id="image-url" name="imageUrl">
        <div id="image-preview"></div>
        <div id="error-message" class="error-message"></div>
    </div>

    <label for="text">Text:</label>
    <textarea id="text" name="text" placeholder="Enter note text"></textarea>

    <input type="submit" value="Create Note">
</form>

<button class="back-button" onclick="window.history.back();">Back</button>

<script>
    const typeSelect = document.getElementById('type');
    const specialField = document.getElementById('special-field');
    const imageField = document.getElementById('image-field');
    const dropZone = document.getElementById('drop-zone');
    const imageInput = document.getElementById('image-input');
    const imageUrlInput = document.getElementById('image-url');
    const imagePreview = document.getElementById('image-preview');
    const errorMessage = document.getElementById('error-message');
    const maxFileSize = 5 * 1024 * 1024; // 5MB

    function updateFields() {
        const value = typeSelect.value;
        specialField.style.display = value === 'LINK' ? 'block' : 'none';
        imageField.style.display = value === 'IMAGE' ? 'block' : 'none';
        if (value !== 'IMAGE') {
            clearImagePreview();
        }
    }

    function clearImagePreview() {
        imagePreview.innerHTML = '';
        imageUrlInput.value = '';
        errorMessage.textContent = '';
    }

    function handleImage(file) {
        if (!file) {
            errorMessage.textContent = 'No file selected.';
            return;
        }

        if (!file.type.startsWith('image/')) {
            errorMessage.textContent = 'Please upload an image file.';
            return;
        }

        if (file.size > maxFileSize) {
            errorMessage.textContent = 'File size exceeds 5MB limit.';
            return;
        }

        const reader = new FileReader();
        reader.onload = (e) => {
            const imageData = e.target.result;
            imageUrlInput.value = imageData;
            imagePreview.innerHTML = `<img src="${imageData}" alt="Preview">`;
            errorMessage.textContent = '';
        };
        reader.onerror = () => {
            errorMessage.textContent = 'Error loading image.';
        };
        reader.readAsDataURL(file);
    }

    typeSelect.addEventListener('change', updateFields);
    window.addEventListener('load', updateFields);

    dropZone.addEventListener('dragover', (e) => {
        e.preventDefault();
        dropZone.classList.add('dragover');
    });

    dropZone.addEventListener('dragleave', () => {
        dropZone.classList.remove('dragover');
    });

    dropZone.addEventListener('drop', (e) => {
        e.preventDefault();
        dropZone.classList.remove('dragover');
        const file = e.dataTransfer.files[0];
        handleImage(file);
    });

    dropZone.addEventListener('click', () => {
        imageInput.click();
    });

    imageInput.addEventListener('change', (e) => {
        const file = e.target.files[0];
        handleImage(file);
    });

    // Clear image input after selection to prevent re-upload issues
    imageInput.addEventListener('click', () => {
        imageInput.value = '';
    });
</script>

</body>
</html>