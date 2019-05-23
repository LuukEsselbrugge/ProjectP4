package screenapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    @FXML
    ListView<Label> booksFoundView;
    @FXML
    Button returnHome;
    @FXML
    Label resultLbl;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Label> items = FXCollections.observableArrayList ();

        for(int i = 0; i < 10; i++) {
            items.add(new Label("Test " + i));
        }

        booksFoundView.setItems(items);

        resultLbl.setText(items.size() + " result(s) have been found");
    }

    @FXML
    public void btnClick() {
        StageBuilder.newScene("homescreen.fxml");
    }
}
