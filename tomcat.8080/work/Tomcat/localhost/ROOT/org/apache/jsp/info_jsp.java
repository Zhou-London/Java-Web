/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/11.0.4
 * Generated at: 2025-03-09 02:10:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.List;
import uk.ac.ucl.model.Note;

public final class info_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(3);
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("uk.ac.ucl.model.Note");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("  <title>Note Info</title>\n");
      out.write("  <style>\n");
      out.write("    body {\n");
      out.write("      max-width: 800px;\n");
      out.write("      margin: 0 auto;\n");
      out.write("      padding: 20px;\n");
      out.write("      font-family: 'Georgia', serif;\n");
      out.write("      background-color: #f4f1ea; /* 淡黄色背景 */\n");
      out.write("      color: #5a4d41; /* 深棕色文字 */\n");
      out.write("    }\n");
      out.write("    h1 {\n");
      out.write("      text-align: center;\n");
      out.write("      color: #8b7d6b; /* 棕色标题 */\n");
      out.write("      padding-bottom: 10px;\n");
      out.write("      border-bottom: 1px solid #d9d2c9; /* 浅棕色边框 */\n");
      out.write("    }\n");
      out.write("    h2 {\n");
      out.write("      color: #8b7d6b;\n");
      out.write("      margin-top: 30px;\n");
      out.write("      padding-bottom: 5px;\n");
      out.write("      border-bottom: 1px dashed #d9d2c9; /* 虚线边框 */\n");
      out.write("    }\n");
      out.write("    p {\n");
      out.write("      background-color: #e6e0d4; /* 更深的淡黄色背景 */\n");
      out.write("      padding: 10px;\n");
      out.write("      margin: 10px 0;\n");
      out.write("      border: 1px solid #d9d2c9;\n");
      out.write("      border-radius: 5px; /* 圆角 */\n");
      out.write("    }\n");
      out.write("    form {\n");
      out.write("      display: flex;\n");
      out.write("      flex-direction: column;\n");
      out.write("      gap: 15px;\n");
      out.write("      max-width: 500px;\n");
      out.write("      margin: 0 auto;\n");
      out.write("    }\n");
      out.write("    label {\n");
      out.write("      color: #5a4d41;\n");
      out.write("      font-weight: bold;\n");
      out.write("    }\n");
      out.write("    input[type=\"text\"], textarea {\n");
      out.write("      width: 100%;\n");
      out.write("      padding: 8px;\n");
      out.write("      border: 1px solid #d9d2c9;\n");
      out.write("      border-radius: 5px;\n");
      out.write("      background-color: #fffcf7; /* 浅羊皮色输入框 */\n");
      out.write("      font-family: 'Georgia', serif;\n");
      out.write("      color: #5a4d41;\n");
      out.write("      box-sizing: border-box; /* 确保宽度包含边框 */\n");
      out.write("    }\n");
      out.write("    textarea {\n");
      out.write("      height: 100px;\n");
      out.write("      resize: vertical; /* 允许垂直调整大小 */\n");
      out.write("    }\n");
      out.write("    input[type=\"submit\"], button {\n");
      out.write("      padding: 10px;\n");
      out.write("      background-color: #a67f59; /* 棕色按钮 */\n");
      out.write("      color: #f4f1ea; /* 淡黄色文字 */\n");
      out.write("      border: none;\n");
      out.write("      border-radius: 5px;\n");
      out.write("      cursor: pointer;\n");
      out.write("      font-family: 'Georgia', serif;\n");
      out.write("      font-size: 1.1em;\n");
      out.write("    }\n");
      out.write("    input[type=\"submit\"]:hover, button:hover {\n");
      out.write("      background-color: #8b7d6b; /* 悬停时稍深 */\n");
      out.write("      text-decoration: underline;\n");
      out.write("    }\n");
      out.write("    button {\n");
      out.write("      margin-top: 20px;\n");
      out.write("      width: 200px;\n");
      out.write("      display: block;\n");
      out.write("      margin-left: auto;\n");
      out.write("      margin-right: auto;\n");
      out.write("    }\n");
      out.write("  </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<h1>Note Info</h1>\n");

  Note note =(Note) request.getAttribute("note");
  String label = note.getLabel();
  String text = note.getText();
  String index = note.getIndex(); //index is rowIndex + 1. Calculated already.
  String rowIndex = request.getParameter("index"); // Start from 0

      out.write("\n");
      out.write("<p>");
      out.print( index );
      out.write("</p>\n");
      out.write("<p>");
      out.print( label );
      out.write("</p>\n");
      out.write("<p>");
      out.print( text );
      out.write("</p>\n");
      out.write("\n");
      out.write("<h2>Edit this note</h2>\n");
      out.write("<form action=\"/viewNoteInfo.html\" method=\"post\">\n");
      out.write("  <input type=\"hidden\" name=\"rowIndex\" value=\"");
      out.print( rowIndex );
      out.write("\">\n");
      out.write("  <label for=\"label\">Label:</label>\n");
      out.write("  <input type=\"text\" id=\"label\" name=\"label\" value=\"");
      out.print( label );
      out.write("\">\n");
      out.write("  <label for=\"text\">Text:</label>\n");
      out.write("  <textarea id=\"text\" name=\"text\">");
      out.print( text );
      out.write("</textarea>\n");
      out.write("  <input type=\"submit\" value=\"Save Changes\">\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<button onclick=\"window.location.href='http://localhost:8080/notesList.html'\">Back to Notes List</button>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
