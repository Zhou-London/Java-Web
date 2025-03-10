package uk.ac.ucl.util;

import uk.ac.ucl.model.NoteType;


public class checkNoteType {

    public static NoteType get(String noteType){
        for(NoteType note : NoteType.values()){
            if(note.toString().equalsIgnoreCase(noteType)){
                return note;
            }
        }
        return null;
    }
}
