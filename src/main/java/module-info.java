module com.example.maze {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;

    requires java.sql;
    requires  org.xerial.sqlitejdbc;
    requires java.desktop;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;
//    requires com.example.maze;


    opens com.example.maze to javafx.fxml;
    exports com.example.maze;
    exports com.example.maze.Controllers;
    exports com.example.maze.Controllers.Admin;
    exports com.example.maze.Controllers.Client;
    exports com.example.maze.Models;
    exports com.example.maze.Views;

}