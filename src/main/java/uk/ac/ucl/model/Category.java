package uk.ac.ucl.model;

public enum Category {
    CASUAL("Casual"),
    IMPORTANT("Important"),
    ACTION("Action");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}