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
import uk.ac.ucl.model.Note;


import java.io.IOException;
import java.util.List;


@WebServlet("/searchNotes.html")
public class SearchServlet extends HttpServlet{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
      Model model = ModelFactory.getModel();
      String keyword = request.getParameter("keyword");
      List<Note> results = model.searchNotes(keyword);
      request.setAttribute("results", results);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
      dispatcher.forward(request, response);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext context = getServletContext();
    RequestDispatcher dispatcher = context.getRequestDispatcher("/search.jsp");
    dispatcher.forward(request, response);
  }
}
