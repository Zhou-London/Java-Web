package uk.ac.ucl.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import java.io.IOException;

// Written by Zhouzhou


/* Documentation */
// This is a servlet with ONLY a POST method.
// The POST method is used to delete a note
// The data posted is an index of list
// Then, according to this index, delete the note
// Finally, refresh the page automatically.




@WebServlet("/deleteNote.html")
public class DeleteNote extends HttpServlet {
    // POST method to delete a note
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // Get File handler
            Model model = ModelFactory.getModel();
            // Get Index from POST
            int index = Integer.parseInt(request.getParameter("rowIndex"));
            // Use this Index to delete the note
            model.removeNote(index);
            // notesList.html will automatically detect the deletion
            // and update the view.
            response.sendRedirect("notesList.html");
        } catch (Exception e){
            // Something bad happened, doesn't really matter though
            // So we head back to the home page!
            e.printStackTrace();
            response.sendRedirect("http://localhost:8080/");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
