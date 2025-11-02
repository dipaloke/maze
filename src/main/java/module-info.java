module com.example.maze {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;

    requires java.sql;
    requires  org.xerial.sqlitejdbc;


    opens com.example.maze to javafx.fxml;
    exports com.example.maze;
    exports com.example.maze.Controllers;
    exports com.example.maze.Controllers.Admin;
    exports com.example.maze.Controllers.Client;
    exports com.example.maze.Models;
    exports com.example.maze.Views;

}