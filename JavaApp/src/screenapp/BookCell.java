package screenapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class BookCell extends ListCell<Book> {
    @FXML
    private Label titleLbl;
    @FXML
    private Label authorLbl;
    @FXML
    private Label locationLbl;

    public BookCell() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listcell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);

        if(empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
//            System.out.println(book.getTitle());
            titleLbl.setText(book.getTitle());
            authorLbl.setText(book.getDescription());
            locationLbl.setText(book.getNumber());

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
