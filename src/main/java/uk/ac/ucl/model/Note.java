package uk.ac.ucl.model;

import java.util.List;

public class Note {
    private String index;     // 标识符
    private String label;     // 标签
    private String text;      // 文本内容
    private String timestamp; // 时间戳（字符串形式）

    public Note(String index, String label, String text, String timestamp) {
        this.index = index;
        this.label = label;
        this.text = text;
        this.timestamp = timestamp;
    }

    // Getter 和 Setter
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // 用于保存到 CSV 时转换为 List
    public List<String> toList() {
        return List.of(index, label, text, timestamp);
    }
}