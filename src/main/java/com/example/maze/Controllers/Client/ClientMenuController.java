package com.example.maze.Controllers.Client;

import com.example.maze.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transection_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_listeners();
    }

    private void add_listeners() {
        dashboard_btn.setOnAction(event -> onDashboard());
        transection_btn.setOnAction(event -> onTransections());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Dashboard");
    }
    private void onTransections() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Transections");
    }


}
