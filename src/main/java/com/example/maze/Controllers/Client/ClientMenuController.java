package com.example.maze.Controllers.Client;

import com.example.maze.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        accounts_btn.setOnAction(event -> onAccounts());
        logout_btn.setOnAction(actionEvent -> onLogout());
        profile_btn.setOnAction(event -> onProfile());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Dashboard");
    }

    private void onTransections() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Transections");

    }

    private void onProfile() {Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Profile");}

    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Accounts");
    }

    private  void onLogout() {
        //Clear jwt
        Model.getInstance().setJwtToken(null);
        System.out.println("User logged out. JWT cleared");

        //Close current window
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);

        //Redirect to log in window
        Model.getInstance().getViewFactory().showLoginWindow();
    }

}
