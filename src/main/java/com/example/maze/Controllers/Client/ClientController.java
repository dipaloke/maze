package com.example.maze.Controllers.Client;

import com.example.maze.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observable, oldVal, newVal) -> {
            switch (newVal) {
                case "Transections" -> client_parent.setCenter(Model.getInstance().getViewFactory().getTransectionsView());
                case "Accounts" -> client_parent.setCenter(Model.getInstance().getViewFactory().getAccountsView());
                case "Profile" -> client_parent.setCenter(Model.getInstance().getViewFactory().getClientProfileView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        } );
    }
}
