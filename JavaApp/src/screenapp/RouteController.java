package screenapp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        Book book = SharedInstance.getInstance().books.get(SharedInstance.getInstance().search_q);

        toFindLbl.setText(book.getTitle());
    }

    @FXML
    void cancelSearch() {
    }


}
