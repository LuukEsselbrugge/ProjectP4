module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires gson;
    requires java.sql;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}