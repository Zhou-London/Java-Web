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

@WebServlet("/viewNoteInfo.html")
public class ViewNoteInfo extends HttpServlet {

    // GET method to return data
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Model model = ModelFactory.getModel();
            int index = Integer.parseInt(request.getParameter("index"));
            List<Note> notes = model.getNoteList();

            Note note = notes.get(index);

            request.setAttribute("note", note);

        } catch (Exception e){
            request.setAttribute("error", e.getMessage());
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/info.jsp");
        dispatch.forward(request, response);
    }

    // POST method to update data
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Model model = ModelFactory.getModel();
            int rowIndex = Integer.parseInt(request.getParameter("rowIndex"));
            String index = Integer.toString(rowIndex + 1);
            String label = request.getParameter("label");
            String text = request.getParameter("text");

            model.setNoteIndex(rowIndex, index);
            model.setNoteLabel(rowIndex, label);
            model.setNoteText(rowIndex, text);

            response.sendRedirect("viewNoteInfo.html?index=" + (rowIndex));

        } catch (Exception e){
            request.setAttribute("error", e.getMessage());
            response.sendRedirect("viewNoteInfo.html?error");
        }
    }
}
