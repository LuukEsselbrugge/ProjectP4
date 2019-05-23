package screenapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchController {
    @FXML
    Button searchButton;
    @FXML
    TextField searchBook;
    @FXML
    Label errorLabel;

    @FXML
    public void btnClick() {
        if(searchBook.getText().equals("")) {
            errorLabel.setVisible(true);
            errorLabel.setText("Please fill in the Title, ISBN or Author");
        } else {
            StageBuilder.newScene("resultscreen.fxml");
        }
    }
}
