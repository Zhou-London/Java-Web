package uk.ac.ucl.model.Note;

import uk.ac.ucl.model.Category;
import uk.ac.ucl.model.NoteType;

import java.util.List;

// Written by Zhouzhou
// This is a data structure to store the Data of a note
// "Note" can be directly transferred to JSP
// Hence, I use "Note" to do what JSON can do.

public class Note {

    /*Documentation*/
    // "id" is the key in the HashMap, which is not yet determined
    // "label" is title
    // "text" is the content
    // "timestamp" is the time since last update
    // "Category" is an enum class


    private int id;
    private String label;
    private String text;
    private String timestamp;
    private Category category;
    private NoteType noteType;


    // Constructor
    public Note(String label, String text, String timestamp) {
        // Default id
        this.id = -1;
        this.label = label;
        this.text = text;
        this.timestamp = timestamp;
        // Default
        this.category = Category.CASUAL;
        this.noteType = NoteType.DEFAULT;
    }

    // Getter with Setter
    public int getID() { return id; }

    public void setID(int id) { this.id = id; }

    public String getLabel() { return label; }

    public void setLabel(String label) { this.label = label; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public NoteType getNoteType() { return noteType; }

    public void setNoteType(NoteType noteType) { this.noteType = noteType; }

    // Convert a note to a list
    // Can be then inserted into CSV
    public List<String> toList() {
        return List.of(label, text, timestamp, category.toString(), noteType.toString(), "");
    }
}