package uk.ac.ucl.model.Note;

import java.util.ArrayList;
import java.util.List;

public class LinkNote extends Note {

    private String link;

    public LinkNote(String lable, String text, String timestamp)
    {
        super(lable, text, timestamp);
        this.link ="";
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public List<String> toList(){
        return List.of(super.getLabel(), super.getText(),
                super.getTimestamp(), super.getCategory().toString(),
                super.getNoteType().toString(), link);
    }
}
