package uk.ac.ucl.model.Note;

import java.util.List;

public class ImageNote extends Note {

    // Path of the image
    private String image;

    public ImageNote(String label, String text, String timestamp)
    {
        super(label, text, timestamp);
        this.image = "";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public List<String> toList(){
        return List.of(super.getLabel(), super.getText(),
                super.getTimestamp(), super.getCategory().toString(),
                super.getNoteType().toString(), image);
    }

}
