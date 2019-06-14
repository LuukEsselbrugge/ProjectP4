package screenapp;

public class Shelf {
    private int shelfID;
    private int bookshelf;
    private int Row;
    private int Col;
    private String lowestBookNr;
    private String highestBookNr;

    Shelf(){

    }

    /**
     * Returns the shelfID
     * @return , int shelfID.
     */
    public int getShelfID() {
        return shelfID;
    }

    /**
     * Returns the bookshelf
     * @return , int bookshelf
     */
    public int getBookshelf() {
        return bookshelf;
    }

    /**
     * Returns the row of the shelf.
     * @return , int row.
     */
    public int getRow() {
        return Row;
    }

    /**
     * Returns the column of the shelf.
     * @return , int col.
     */
    public int getCol() {
        return Col;
    }

    /**
     * Returns the lowest book number on the shelf.
     * @return , String lowestBookNr.
     */
    public String getLowestBookNr() {
        return lowestBookNr;
    }


    /**
     * Returns the highest book number on the shelf.
     * @return , String highestBookNr.
     */
    public String getHighestBookNr() {
        return highestBookNr;
    }
}
