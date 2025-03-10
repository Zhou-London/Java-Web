package uk.ac.ucl.model;

/* Documentation */
// This is an enum class for all the categories
// There are CASUAL, IMPORTANT and ACTION




public enum Category {
    // Enum
    CASUAL("Casual"),
    IMPORTANT("Important"),
    ACTION("Action");

    // Used to display in the HTML
    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}