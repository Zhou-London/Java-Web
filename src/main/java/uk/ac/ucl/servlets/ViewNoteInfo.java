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
import uk.ac.ucl.util.createTimeStamp;
import java.io.IOException;
import java.util.List;


// Written by Zhouzhou

/* Documentation */
// This is a servlet for both GET and POST
// GET:
//      Return the Data (File handler and File itself)
// POST:
//      Update a Note
// Every time I view the note, I wish I can modify it.
// Hence, the POST method is here, rather than anywhere else.



@WebServlet("/viewNoteInfo.html")
public class ViewNoteInfo extends HttpServlet {

    // GET method to return data
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get File handler
            Model model = ModelFactory.getModel();
            // Get note index
            int index = Integer.parseInt(request.getParameter("index"));
            // Get note list
            List<Note> notes = model.getNoteList();

            // Get corresponding note
            Note note = notes.get(index);

            // Send this note to JSP
            request.setAttribute("note", note);

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
            // Get File handler
            Model model = ModelFactory.getModel();
            // Get note's index in the List from POST
            int rowIndex = Integer.parseInt(request.getParameter("rowIndex"));
            // Convert the index to an index field (Start from 0)
            String index = Integer.toString(rowIndex + 1);
            // Get label, or may say title from POST
            String label = request.getParameter("label");
            // Get text from POST
            String text = request.getParameter("text");

            // Get current timestamp from a util function
            String timestamp = createTimeStamp.ISO();

            // Update the notes based on this information
            model.setNoteIndex(rowIndex, index);
            model.setNoteLabel(rowIndex, label);
            model.setNoteText(rowIndex, text);
            model.setNoteTimestamp(rowIndex, timestamp);

            // Notes are already updated, before redirection
            // Then redirect user to the information page
            response.sendRedirect("viewNoteInfo.html?index=" + (rowIndex));

        } catch (Exception e){
            // Anything wrong took place, take user to an "error" page
            // So far, there is nothing, maybe some 404 I guess
            request.setAttribute("error", e.getMessage());
            response.sendRedirect("viewNoteInfo.html?error");
        }
    }
}
