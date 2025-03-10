package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.Note.Note;


import java.io.IOException;
import java.util.List;

/* Documentation */
// This is a servlet for GET and POST
// GET: Just render a jsp page
// POST: Receive a keyword, return a list of Notes




@WebServlet("/searchNotes.html")
public class SearchServlet extends HttpServlet{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Get File Handler
    Model model = ModelFactory.getModel();
    // Get data
    String keyword = request.getParameter("keyword");
    // Get search results
    List<Note> results = model.searchNotesByWord(keyword);
    // Send it out
    request.setAttribute("results", results);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
    dispatcher.forward(request, response);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext context = getServletContext();
    RequestDispatcher dispatcher = context.getRequestDispatcher("/search.jsp");
    dispatcher.forward(request, response);
  }
}
