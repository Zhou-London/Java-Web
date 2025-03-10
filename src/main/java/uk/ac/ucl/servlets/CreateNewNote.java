package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Category;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.Note.ImageNote;
import uk.ac.ucl.model.Note.LinkNote;
import uk.ac.ucl.model.Note.Note;
import uk.ac.ucl.model.NoteType;
import uk.ac.ucl.util.checkNoteType;
import uk.ac.ucl.util.createTimeStamp;
import uk.ac.ucl.util.checkCategory;
import java.io.IOException;

// Written by Zhouzhou

/*Documentation*/
// This is a servlet for both GET and POST
// GET:
//      Render the "createNewNote" page
// POST:
//      Receive the data
//      Modify the model



@WebServlet("/createNewNote.html")
public class CreateNewNote extends HttpServlet {
    // POST method to add a new note
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // Get model
            Model model = ModelFactory.getModel();
            // Get data
            String label = request.getParameter("label");
            String text = request.getParameter("text");
            String timestamp = createTimeStamp.ISO();
            String category = request.getParameter("category");
            Category CATEGORY = checkCategory.get(category);
            String type = request.getParameter("type");
            NoteType noteType = checkNoteType.get(type);
            // Construct a new Note
            Note newNote;
            switch(noteType){
                case LINK:
                    LinkNote thisNote = new LinkNote(label, text, timestamp);
                    String special = request.getParameter("special");
                    thisNote.setLink(special);
                    newNote = thisNote;
                    break;
                case IMAGE:
                    ImageNote imageNote = new ImageNote(label, text, timestamp);
                    String image = request.getParameter("imageUrl");
                    imageNote.setImage(image);
                    newNote = imageNote;
                    break;
                default:
                    newNote = new Note(label, text, timestamp);
                    break;
            }
            newNote.setNoteType(noteType);
            newNote.setCategory(CATEGORY);
            // Insert
            model.addNote(newNote);
            // Redirect
            response.sendRedirect("notesList.html");

        } catch (Exception e){
            // Handle Error
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
