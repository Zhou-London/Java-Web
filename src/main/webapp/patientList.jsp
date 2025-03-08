<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>


<head>
  <jsp:include page="/meta.jsp"/>
  <title>Notes App</title>
  <style>
    .header-row {
      display: flex;
      gap: 20px;
      font-weight: bold;
      margin-bottom: 10px;
      margin-left: 30px;
    }


  </style>
</head>


<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Notes: </h2>

  <div class="header-row">
    <span>Index</span>
    <span>Label</span>
  </div>

  <ul>
    <%
      List<String> patients = (List<String>) request.getAttribute("nodeIndex");
      List<String> labels = (List<String>) request.getAttribute("nodeLabel");
      for (int i = 0; i < patients.size(); i++)
      {
        String index = patients.get(i);
        String label = labels.get(i);
        String href = "viewNoteInfo.html?index=" + index;
    %>
    <li>
      <a href="<%= href %>" style="margin-right: 40px" >
      <%= index %>
      </a>
      <%= label.length() > 25 ? label.substring(0,25) + "..." : label %>
    </li>
    <% } %>
  </ul>



</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
