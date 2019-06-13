package screenapp;

public class Book {
    private String number;
    private String title;
    private String description;

    /**
     * Constructor for book
     */
    public Book() {
    }

    /**
     * Returns the ID of the book.
     * @return , String number
     */
    String getNumber() {
        return number;
    }

    /**
     * Returns the title of the book.
     * @return , String title
     */
    String getTitle() {
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
