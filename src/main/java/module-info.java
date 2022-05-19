module com.example.dmineur2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;


    opens com.example.dmineur2 to javafx.fxml;
    exports com.example.dmineur2;
}