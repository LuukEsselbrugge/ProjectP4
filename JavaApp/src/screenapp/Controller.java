package screenapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    Button searchButton;
    @FXML
    TextField searchBook;

    @FXML
    public void btnClick() {
        searchBook.setText("Clicked");
    }
}
