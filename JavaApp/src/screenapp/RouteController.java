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

    /**
     * Initialize the view for the route.
     * @param url , URl url
     * @param resourceBundle , Resourcebundle for display
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Book book = SharedInstance.getInstance().books.get(SharedInstance.getInstance().search_q);

        toFindLbl.setText(book.getTitle());
    }

    @FXML
    void cancelSearch() {
    }


}
