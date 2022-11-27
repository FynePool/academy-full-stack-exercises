package com.bookstore;

public enum BookCategory {
    FANTASY, 
    SCIFI, 
    ACTION,
    ADVENTURE, 
    POETRY,
    HISTORICAL,
    OTHER;

    public static BookCategory getByValue(String value) {
        for (BookCategory category : BookCategory.values())
            if (category.toString().toLowerCase().equals(value.toLowerCase()))
                return category;
        
        return null;
    }

}
