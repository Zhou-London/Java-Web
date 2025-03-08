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

@WebServlet("/viewNoteInfo.html")
public class ViewNoteInfo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Model model = ModelFactory.getModel();
            int index = Integer.parseInt(request.getParameter("index")) - 1;
            String label = model.getNodeLabel().get(index);
            String text = model.getNodeText().get(index);
            String s_index = model.getNodeIndex().get(index);

            request.setAttribute("label", label);
            request.setAttribute("text", text);
            request.setAttribute("index", s_index);

        } catch (Exception e){
            request.setAttribute("error", e.getMessage());
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/info.jsp");
        dispatch.forward(request, response);
    }
}
