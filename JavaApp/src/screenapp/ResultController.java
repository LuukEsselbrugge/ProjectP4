package screenapp;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Wrapper;
import java.util.ArrayList;
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

        String results = SharedInstance.getInstance().data;
        String books = null;
        try {
            books = HttpRequest.sendPOST(results, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert books != null;

        Gson gson = new Gson();
        JsonArray jsonArray = (JsonArray)new JsonParser().parse(books);
        ArrayList<Book> bookArrayList = new ArrayList<>();

        for(int i = 0; i < jsonArray.size(); i++){
            JsonElement jsonElement = jsonArray.get(i);
            String jsonString = jsonElement.toString();
            Book book = gson.fromJson(jsonString, Book.class);
            bookArrayList.add(book);
            items.add(new Label(book.getTitle()));
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

    private class Book{
        private String number;
        private String title;
        private String description;

        public Book(){}


        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
