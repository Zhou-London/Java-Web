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
// Used to remove a Note from the model




@WebServlet("/deleteNote.html")
public class DeleteNote extends HttpServlet {
    // POST method to delete a note
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // Get File handler
            Model model = ModelFactory.getModel();
            // Get key
            int key = Integer.parseInt(request.getParameter("key"));
            // Remove the note
            model.removeNote(key);
            // Redirect
            response.sendRedirect("notesList.html");
        } catch (Exception e){
            // Handle error
            e.printStackTrace();
            response.sendRedirect("http://localhost:8080/");
        }
    }

}
