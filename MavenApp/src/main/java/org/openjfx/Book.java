package org.openjfx;

public class Book {
    private String number;
    private String title;
    private String description;

    /**
     * Constructor for book
     */
    public Book() {
    }

    public Book(String title, String number, String description) {
        this.title = title;
        this.number = number;
        this.description = description;
    }

    /**
     * Returns the ID of the book.
     * @return , String number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Returns the title of the book.
     * @return , String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the  description of the book.
     * @return , String description.
     */
    public String getDescription() {
        return description;
    }
}
