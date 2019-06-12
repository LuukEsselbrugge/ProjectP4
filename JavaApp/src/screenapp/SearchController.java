package screenapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchController {
    @FXML
    Button searchBookBtn;
    @FXML
    TextField searchBookTxt;
    @FXML
    Label errorLbl;

    @FXML
    public void btnClick() {
        if(searchBookTxt.getText().equals("")) {
            errorLbl.setVisible(true);
            errorLbl.setText("Please fill in the Title, ISBN or Author");
        } else {
            SharedInstance.getInstance().data = searchBookTxt.getText();
            StageBuilder.newScene("resultscreen.fxml");
        }
    }
}
