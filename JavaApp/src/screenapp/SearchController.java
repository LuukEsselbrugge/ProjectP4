package screenapp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class SearchController {
    @FXML
    Button searchBookBtn;
    @FXML
    TextField searchBookTxt;
    @FXML
    Label errorLbl;
    @FXML
    Label loadingLbl;

    private static final String POST_URL = "http://projectp4.com/webscraper/getResults?token=secretkey";

    /**
     * Search function on button click. Will trigger an event to do a httprequest to the server.
     */
    @FXML
    public void btnClick() {
        if(searchBookTxt.getText().equals("")) {
            errorLbl.setVisible(true);
            errorLbl.setText("Please fill in the Title, ISBN or Author");
        } else {
            searchBooks();
        }
    }

    public void searchBooks() {
        String results = searchBookTxt.getText();
        String books = null;
        try {
            // httprequest.
            books = HttpRequest.sendPOST(results, POST_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert books != null;

        // Transform jsonString from result into jsonArray.
        Gson gson = new Gson();
        JsonArray jsonArray = (JsonArray)new JsonParser().parse(books);
        ArrayList<Book> bookArrayList = new ArrayList<>();

        // Add each JsonElement to ArrayList<Book> as object Book.
        for(int i = 0; i < jsonArray.size(); i++){
            JsonElement jsonElement = jsonArray.get(i);
            String jsonString = jsonElement.toString();
            Book book = gson.fromJson(jsonString, Book.class);
            bookArrayList.add(book);
        }
        SharedInstance.getInstance().books = bookArrayList;
        StageBuilder.newScene("resultscreen.fxml");
    }
}
