package screenapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RouteController implements Initializable {
    @FXML
    Label colorLbl;
    @FXML
    Label toFindLbl;
    @FXML
    Button cancelSearchBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void cancelSearch() {
    }
}
