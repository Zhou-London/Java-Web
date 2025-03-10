package uk.ac.ucl.model;

import java.util.List;

// Written by Zhouzhou
// This is a data structure to store the Data of a note
// "Note" can be directly transferred to JSP
// Hence, I use "Note" to do what JSON can do.

public class Note {

    /*Documentation*/
    // "index" is the node's index in the List
    // "label" is title
    // "text" is the content
    // "timestamp" is the time since last update
    // "index" is defined dynamically
    // Everytime the nodesList page is refreshed,
    // "index" will be assigned
    // This would automatically fix the gap of deleted notes


    private String index;
    private String label;
    private String text;
    private String timestamp;
    private Category category;


    // Constructor
    public Note(String index, String label, String text, String timestamp) {
        this.index = index;
        this.label = label;
        this.text = text;
        this.timestamp = timestamp;
        this.category = Category.CASUAL;
    }

    // Getter with Setter
    public String getIndex() { return index; }

    public void setIndex(String index) { this.index = index; }

    public String getLabel() { return label; }

    public void setLabel(String label) { this.label = label; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    // Convert a note to a list
    // Can be then inserted into CSV
    public List<String> toList() {
        return List.of(index, label, text, timestamp, category.toString());
    }
}