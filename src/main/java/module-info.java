module com.example.maze {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;

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