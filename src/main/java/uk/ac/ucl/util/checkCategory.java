package uk.ac.ucl.util;

import uk.ac.ucl.model.Category;

// Written by Zhouzhou, assisted by IntelliJ IDEA
// It just generated these things
// Used to convert a string of category
// to a enum of category

public class checkCategory {

    public static Category get(String categoryFilter){
        for(Category c : Category.values()){
            if(c.getDisplayName().equalsIgnoreCase(categoryFilter)){
                return c;
            }
        }
        return null;
    }
}
