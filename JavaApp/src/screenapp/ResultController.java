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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    @FXML
    ListView<Book> bookResultList;
    @FXML
    Button backBtn;
    @FXML
    Label resultSumLbl;

    private static final String POST_BOOK_URL = "http://projectp4.com/webscraper/getShelfs?token=secretkey";
    private String message;

    /**
     * Initialize the view for fxml.
     * @param url , Url url
     * @param resourceBundle , Resourcebundle
     */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Book> items = FXCollections.observableArrayList();

        ArrayList<Book> books = SharedInstance.getInstance().books;

        // Fill the Label list items.

        bookResultList.setCellFactory(new BookCellFactory());

        items.addAll(books);


        bookResultList.setItems(items);

        resultSumLbl.setText(items.size() + " result(s) have been found");

        /**
         * Create on click event for selected books.
         */
        bookResultList.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if(mouseEvent.getClickCount() == 2) {
                    SharedInstance.getInstance().search_q = bookResultList.getSelectionModel().getSelectedIndex();
                    Book book = books.get(bookResultList.getSelectionModel().getSelectedIndex());

                    // Do a httprequest on the selected book to get the list of available shells from the database.
                    String result = "";
                    try {
                        result = HttpRequest.sendPOST("*", POST_BOOK_URL);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Transform the returned jsonString to a jsonArray.
                    Gson gson = new Gson();
                    JsonArray jsonArray = null;
                    try{
                        jsonArray = (JsonArray)new JsonParser().parse(result);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    ArrayList<Shelf> shelfs = new ArrayList<>();

                    // Create an object for every JsonElement in the jsonArray.
                    for(int i = 0; i < jsonArray.size(); i++){
                        JsonElement jsonElement = jsonArray.get(i);
                        String jsonString = jsonElement.toString();
                        Shelf shelf = gson.fromJson(jsonString, Shelf.class);
                        shelfs.add(shelf);
                    }

                    // Do a sequentialsearch on the book that is selected.
                    int index = BinarySearch.sequential(shelfs, book.getNumber());
                    if(index == -1){
                        resultSumLbl.setText("Geen resultaat gevonden");
                    }else{
                        System.out.println("bookshelf: " + shelfs.get(index).getBookshelf() + " row: " + shelfs.get(index).getCol() + " col: " + shelfs.get(index).getRow());
                        SharedInstance.getInstance().result = shelfs.get(index);
                        StageBuilder.newScene("routescreen.fxml");
                    }
                }
            }
        });
    }

    @FXML
    public void btnClick() {
        StageBuilder.newScene("searchscreen.fxml");
    }
}
