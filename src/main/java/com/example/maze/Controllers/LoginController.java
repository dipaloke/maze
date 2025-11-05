package com.example.maze.Controllers;

import com.example.maze.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_field;
    public Label password_lbl;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;
    public Button registration_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //creating single tone (if user clicks login button we want to go to view fectory and show cliwnt window )
        login_btn.setOnAction(event -> onLogin());
        registration_btn.setOnAction(event -> onRegistration());
    }

    //functionality the close the login state (initially simple)
    private void onLogin () {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }

    private void onRegistration () {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showRegistrationWindow();
    }

    //
}
