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

        String result = "";
        try {
            result = HttpRequest.sendPOST("*", 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        JsonArray jsonArray = (JsonArray)new JsonParser().parse(result);
        ArrayList<Shelf> shelfs = new ArrayList<>();

        for(int i = 0; i < jsonArray.size(); i++){
            JsonElement jsonElement = jsonArray.get(i);
            String jsonString = jsonElement.toString();
            Shelf shelf = gson.fromJson(jsonString, Shelf.class);
            shelfs.add(shelf);
        }

        System.out.println(result);
    }

    @FXML
    void cancelSearch() {
    }
}
