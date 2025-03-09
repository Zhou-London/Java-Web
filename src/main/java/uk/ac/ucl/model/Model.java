package uk.ac.ucl.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

// Written by Zhouzhou Zhang, based on the provided skeleton
// Mar 8, updated by Grok 3 on Mar 08, 2025

public class Model {
  // Instance variable to store the data
  private List<Note> noteList = new ArrayList<>();
  // File Path
  private final String PATH = "data/patients100.csv";
  // Public method to return Notes list
  public List<Note> getNoteList() {
    return noteList;
  }

  // Public Method to modify the specific data
  // rowIndex refers to the absolute order of the note
  // for example, 0, 1, 2...
  // which is different from 1,2,3...
  // Ref:
  // Lambda Expression
  // https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
  public void setNoteIndex(int rowIndex, String value) {
    setRowValue(rowIndex, value, note -> note.setIndex(value));
  }

  public void setNoteLabel(int rowIndex, String value) {
    setRowValue(rowIndex, value, note -> note.setLabel(value));
  }

  public void setNoteText(int rowIndex, String value) {
    setRowValue(rowIndex, value, note -> note.setText(value));
  }

  public void setNoteTimestamp(int rowIndex, String value) {
    setRowValue(rowIndex, value, note -> note.setTimestamp(value));
  }

  // Method to Read the file, load into the noteList
  public void readFile() {
    noteList.clear();
    try (Reader reader = new FileReader(PATH);
         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
      for (CSVRecord csvRecord : csvParser) {
        List<String> rowData = csvToList(csvRecord);
        if (rowData.size() >= 4) {
          noteList.add(new Note(rowData.get(0), rowData.get(1), rowData.get(2), rowData.get(3)));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Method to create a new note
  public void createNote(Note note) {
    noteList.add(note);
    saveData();
  }

  // Method to Modify a row
  // Ref:
  // Function interface
  // https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html
  private void setRowValue(int rowIndex, String value, java.util.function.Consumer<Note> setter) {
    if (rowIndex >= 0 && rowIndex < noteList.size()) {
      Note note = noteList.get(rowIndex);
      setter.accept(note);
      // Update the local file
      saveData();
    }
  }

  // Helper function to save the update
  private void saveData() {
    try (Writer writer = new FileWriter(PATH);
         CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
      for (Note note : noteList) {
        csvPrinter.printRecord(note.toList());
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

  // Search method
  public List<String> searchFor(String keyword) {
    return List.of("Search keyword is: " + keyword, "result1", "result2", "result3");
  }
}