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
import uk.ac.ucl.model.Note.Note;
import uk.ac.ucl.util.checkCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* Documentation */
// This servlet handles GET requests to display a list of notes
// Features:
// - Returns all notes by default
// - Supports filtering by category
// - Supports sorting by key, timestamp, label, or category

@WebServlet("/notesList.html")
public class ViewNotesListServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      // Get model
      Model model = ModelFactory.getModel();

      // Get filter and sort parameters
      String categoryFilter = request.getParameter("categoryFilter");
      String sortBy = request.getParameter("sortBy");

      // Get all notes
      HashMap<Integer, Note> notes = model.getNotesMap();

      // Filter the category
      if (categoryFilter != null && !categoryFilter.isEmpty()) {
        Category category = checkCategory.get(categoryFilter);
        if (category != null) {
          notes = model.findNotesByCategory(category);
        }
      }

      // Sort the Entry
      List<Map.Entry<Integer, Note>> noteList = new ArrayList<>(notes.entrySet());
      // Ref:
      // Comparator
      // https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
      if (sortBy != null && !sortBy.isEmpty()) {
        switch (sortBy) {
          case "key":
            noteList.sort(Comparator.comparing(Map.Entry::getKey));
            break;
          case "timestamp":
            noteList.sort(Comparator.comparing(entry -> entry.getValue().getTimestamp()));
            break;
          case "label":
            noteList.sort(Comparator.comparing(entry -> entry.getValue().getLabel()));
            break;
          case "category":
            noteList.sort(Comparator.comparing(entry -> entry.getValue().getCategory().getDisplayName()));
            break;
          default:
            // Default to key sorting if sortBy is invalid
            noteList.sort(Comparator.comparing(Map.Entry::getKey));
            break;
        }
      } else {
        // Default sorting by key if no sortBy specified
        noteList.sort(Comparator.comparing(Map.Entry::getKey));
      }

      // Rebuild sorted HashMap
      // Linked HashMap will iterate in the order in which the entries were put into the map
      // https://stackoverflow.com/questions/26623129/when-to-use-linkedhashmap-over-hashmap-in-java
      LinkedHashMap<Integer, Note> sortedNotes = new LinkedHashMap<>();
      for (Map.Entry<Integer, Note> entry : noteList) {
        sortedNotes.put(entry.getKey(), entry.getValue());
      }

      // Set attributes for JSP
      request.setAttribute("notes", sortedNotes);

    } catch (Exception e) {
      // Handle exceptions
      request.setAttribute("error", e.getMessage());
    }

    // Forward to JSP
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/notesList.jsp");
    dispatch.forward(request, response);
  }
}