module org.zero {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.zero to javafx.fxml;
    exports org.zero;
}