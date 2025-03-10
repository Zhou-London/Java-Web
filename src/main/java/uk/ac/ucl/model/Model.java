package uk.ac.ucl.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ucl.model.Note.ImageNote;
import uk.ac.ucl.model.Note.LinkNote;
import uk.ac.ucl.model.Note.Note;
import uk.ac.ucl.util.checkCategory;
import uk.ac.ucl.util.checkNoteType;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

// Written by Zhouzhou Zhang, based on the provided skeleton

/* Documentation */
// This is a file handler, its main job includes:
// 1. Read from the CSV file
// 2. Convert the CSV data into "Note"
// 3. Store a Hash Map of "Note"
// 4. Write to CSV file based on the Hash Map
// 5. Return "Note" under different requirement

public class Model {

  // A HashMap to store the Notes
  HashMap<Integer, Note> notes = new HashMap<>();

  // Configure the file path
  private final String PATH = "data/notes.csv";

  /* Methods */
  // Public method to return Full Notes list
  public HashMap<Integer, Note> getNotesMap() {
    return notes;
  }

  // Public method to return Filtered Notes list
  public HashMap<Integer, Note> findNotesByCategory(Category category) {
    HashMap<Integer, Note> c_notes = new HashMap<>();
    for(Map.Entry<Integer, Note> entry : getNotesMap().entrySet()) {
      if(entry.getValue().getCategory().equals(category)) {
        c_notes.put(entry.getKey(), entry.getValue());
      }
    }
    return c_notes;
  }

  /*Public Method to modify the specific note*/
  public void setNoteID(int id, Integer value) {
    // Get this note
    Note note = notes.get(id);
    if (note != null) {
      // Remove this note first
      notes.remove(id);
      // Change the Key, add it again
      note.setID(value);
      notes.put(value, note);
      // Modify the CSV file
      saveData();
    }
  }

  // Modify label
  public void setNoteLabel(int id, String value) {
    notes.get(id).setLabel(value);
    saveData();
  }

  // Modify text
  public void setNoteText(int id, String value) {
    notes.get(id).setText(value);
    saveData();
  }

  // Modify timestamp(Default Null)
  public void setNoteTimestamp(int id, String value) {
    notes.get(id).setTimestamp(value);
    saveData();
  }

  // Modify category(Default CASUAL)
  public void setNoteCategory(int id, Category value) {
    notes.get(id).setCategory(value);
    saveData();
  }

  // Read the CSV file
  public void readFile() {
    notes.clear();
    try (Reader reader = new FileReader(PATH);
         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
      // Use order as its id
      int id = 0;
      for (CSVRecord csvRecord : csvParser) {
        List<String> rowData = csvToList(csvRecord);
        if (rowData.size() >= 0) {
          // Construct the Note
          // Note newNote = new Note(rowData.get(0), rowData.get(1), rowData.get(2));
          // Parse category and note type
          String label = rowData.get(0);
          String text = rowData.get(1);
          String timestamp = rowData.get(2);
          String category = csvRecord.get(3);
          Category CATEGORY = checkCategory.get(category);
          String type = csvRecord.get(4);
          NoteType noteType = checkNoteType.get(type);
          String special = csvRecord.get(5);

          Note newNote = null;
          switch (noteType) {
            case LINK:
              LinkNote thisNote = new LinkNote(label, text, timestamp);
              thisNote.setLink(special);
              newNote = thisNote;
              break;
            case IMAGE:
              ImageNote imageNote = new ImageNote(label, text, timestamp);
              imageNote.setImage(special);
              newNote = imageNote;
              break;
            default:
              newNote = new Note(label, text, timestamp);
              break;
          }
          newNote.setCategory(CATEGORY);
          newNote.setNoteType(noteType);
          newNote.setID(id);
          notes.put(id, newNote);
          id++;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Create a new note
  public void addNote(Note note) {
    // Empty case
    if(notes.isEmpty()) {
      note.setID(0);
      notes.put(0, note);
      saveData();
      // Return directly
      return;
    }

    // Non-Empty Case
    // Find the maximum key
    Integer MaxKey = notes.keySet().stream().max(Integer::compareTo).get();
    Integer newKey = MaxKey + 1;

    // Set the key and insert
    note.setID(newKey);
    notes.put(newKey, note);
    saveData();
  }

  // Delete a Note
  public void removeNote(int noteId) {
    notes.remove(noteId);
    saveData();
  }

  // Search notes by a keyword
  public List<Note> searchNotesByWord(String keyword){
    // Init a list to return
    List<Note> result = new ArrayList<>();
    // Get keyword
    String lowerCaseKeyword = keyword.toLowerCase();
    // Iterate the Map
    for (Map.Entry<Integer, Note> entry : notes.entrySet()) {
      // Compare value
      Note thisNote = entry.getValue();
      if(thisNote.getLabel().toLowerCase().contains(lowerCaseKeyword)
      || thisNote.getText().toLowerCase().contains(lowerCaseKeyword)) {
        // Add result
        result.add(thisNote);
      }
    }
    return result;
  }

  // Search notes by a key
  public Note searchNoteByID(Integer id) {
    return notes.get(id);
  }

  // Helper function to save the update
  private void saveData() {
    try (Writer writer = new FileWriter(PATH);
         CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
      for (Map.Entry<Integer, Note> entry : notes.entrySet()) {
        Note thisNote = entry.getValue();
        csvPrinter.printRecord(thisNote.toList());
      }
      csvPrinter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Helper function, convert a CSV record to a list of String
  private List<String> csvToList(CSVRecord r) {
    List<String> list = new ArrayList<>();
    for (String s : r) {
      list.add(s);
    }
    return list;
  }
}