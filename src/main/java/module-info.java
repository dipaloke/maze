module com.example.maze {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.maze to javafx.fxml;
    exports com.example.maze;
}