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

// Written by Zhouzhou

/* Documentation */

// This is a servlet for ONLY GET method
// It will give a file handler to JSP
// JSP can directly read the Notes

// It might be better to just give the list of notes
// However, my index is dynamic
// And I have to update the index dynamically
// Hence, a file handler would be much easier




@WebServlet("/notesList.html")
public class ViewPatientListServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    try {
      // Get File handler
      Model model = ModelFactory.getModel();

      // Set this handler into the request body directly
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
