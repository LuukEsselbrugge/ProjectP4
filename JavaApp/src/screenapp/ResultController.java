package screenapp;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Style;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    @FXML
    ListView<Label> bookResultList;
    @FXML
    Button backBtn;
    @FXML
    Label resultSumLbl;

    private String message;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Label> items = FXCollections.observableArrayList ();

        ArrayList<Book> books = SharedInstance.getInstance().books;

        for(Book book : books) {
            Label lbl = new Label();

            lbl.setFont(new Font("verdana", 10.0));

            lbl.setText(book.getTitle() + " \n   " + book.getDescription());

            items.add(lbl);
        }

        bookResultList.setItems(items);

        resultSumLbl.setText(items.size() + " result(s) have been found");

        bookResultList.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if(mouseEvent.getClickCount() == 2) {
                    SharedInstance.getInstance().search_q = bookResultList.getSelectionModel().getSelectedIndex();
                    StageBuilder.newScene("routescreen.fxml");
                }
            }
        });
    }

    @FXML
    public void btnClick() {
        StageBuilder.newScene("searchscreen.fxml");
    }

}
