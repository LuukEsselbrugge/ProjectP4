package screenapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;

import java.net.URL;
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

        for(int i = 0; i < 10; i++) {
            items.add(new Label("Test " + i));
        }

        bookResultList.setItems(items);

        resultSumLbl.setText(items.size() + " result(s) have been found");

        bookResultList.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if(mouseEvent.getClickCount() == 2) {
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
