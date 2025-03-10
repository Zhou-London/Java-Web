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
import uk.ac.ucl.util.createTimeStamp;
import java.io.IOException;


// Written by Zhouzhou

/* Documentation */
// This is a servlet for both GET and POST
// GET:
//      Return the specific Note you clicked
// POST:
//      Update this Note based on your edition
//



@WebServlet("/viewNoteInfo.html")
public class ViewNoteInfo extends HttpServlet {

    private Model model;

    // GET method to return data
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get File handler
            model = ModelFactory.getModel();
            // Get Key
            Integer key = Integer.parseInt(request.getParameter("key"));
            // Get note
            Note thisNote = model.searchNoteByID(key);
            // Send this note to JSP
            request.setAttribute("note", thisNote);

        } catch (Exception e){
            //Failed
            request.setAttribute("error", e.getMessage());
        }

        // Let's go
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/info.jsp");
        dispatch.forward(request, response);
    }

    // POST method to update data
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // Get key
            Integer key = Integer.parseInt(request.getParameter("key"));
            // Get label
            String label = request.getParameter("label");
            // Get text
            String text = request.getParameter("text");
            // Get current timestamp from a util function
            String timestamp = createTimeStamp.ISO();

            // Update the notes based on this information
            model.setNoteLabel(key, label);
            model.setNoteText(key, text);
            model.setNoteTimestamp(key, timestamp);

            // Redirect
            response.sendRedirect("viewNoteInfo.html?key=" + key);

        } catch (Exception e){
            // Anything wrong took place, take user to an "error" page
            // So far, there is nothing, maybe some 404 I guess
            request.setAttribute("error", e.getMessage());
            response.sendRedirect("viewNoteInfo.html?error");
        }
    }
}
