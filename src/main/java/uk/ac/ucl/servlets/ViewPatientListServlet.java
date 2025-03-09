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

@WebServlet("/notesList.html")
public class ViewPatientListServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get data from model
    try {
      Model model = ModelFactory.getModel();
      List<Note> notes = model.getNoteList();


      // Set them into request body
      request.setAttribute("notes", notes);
      request.setAttribute("model", model);
    } catch (Exception e){
      // Handle exceptions
      request.setAttribute("error", e.getMessage());
    }

    // Render to html
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/notesList.jsp");
    dispatch.forward(request, response);
  }
}
