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

// Written by Zhouzhou

/*Documentation*/
// This is a servlet for both GET and POST
// GET:
//      Render the "createNewNote" page
// POST:
//      Receive the data
//      Insert it to the List
//      Modify the CSV file



@WebServlet("/createNewNote.html")
public class CreateNewNote extends HttpServlet {
    // POST method to add a new note
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Model model = ModelFactory.getModel();
            String label = request.getParameter("label");
            String text = request.getParameter("text");
            String timestamp = createTimeStamp.ISO();
            // Index will be added, after its being inserted into the note List.
            Note note = new Note("0",label, text, timestamp);

            model.addNote(note);

            response.sendRedirect("notesList.html");

        } catch (Exception e){
            // Failed adding the new note
            e.printStackTrace();
        }
    }

    // GET method of "create a new note" page
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/createNewNote.jsp");
        dispatcher.forward(request, response);
    }



}
