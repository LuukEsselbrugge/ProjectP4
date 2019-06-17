package org.openjfx;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class BookCellFactory implements Callback<ListView<Book>, ListCell<Book>> {

    @Override
    public ListCell<Book> call(ListView<Book> bookListView) {
        return new BookCell();
    }
}
