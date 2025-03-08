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


import java.io.IOException;
import java.util.List;

@WebServlet("/patientList.html")
public class ViewPatientListServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get data from model
    try {
      Model model = ModelFactory.getModel();
      List<String> nodeIndex = model.getNodeIndex();
      List<String> nodeLabel = model.getNodeLabel();
      List<String> nodeText = model.getNodeText();

      // Set them into request body
      request.setAttribute("nodeIndex", nodeIndex);
      request.setAttribute("nodeLabel", nodeLabel);
      request.setAttribute("nodeText", nodeText);
    } catch (Exception e){
      request.setAttribute("error", e.getMessage());
    }

    // Set request destination
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
    dispatch.forward(request, response);
  }
}
