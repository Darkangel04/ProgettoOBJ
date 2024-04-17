module com.example.paginewiki {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports Model;
    opens Model to javafx.fxml;
    exports Database;
    opens Database to javafx.fxml;
    exports GUI;
    opens GUI to javafx.fxml;
    exports test;
    opens test to javafx.fxml;
}