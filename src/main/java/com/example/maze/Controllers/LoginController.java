package com.example.maze.Controllers;

import com.example.maze.Models.Model;
import com.example.maze.services.HttpClientHelper;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<String> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_field;
    public Label password_lbl;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;
    public Button registration_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.getItems().addAll("INTERN", "ENGINEER", "ADMIN");

        acc_selector.setValue("INTERN");

        //creating single tone (if user clicks login button we want to go to view fectory and show cliwnt window )
        login_btn.setOnAction(event -> onLogin());
        registration_btn.setOnAction(event -> onRegistration());
    }

    //functionality the close the login state (initially simple)
    private void onLogin () {
        String email = payee_address_field.getText();
        String password = password_fld.getText();
        String role = acc_selector.getValue();

        if (email.isEmpty() || password.isEmpty() || role == null){
           error_lbl.setText("Please fill all fields and select a role.");
           return;
        }

        try {
            String token = HttpClientHelper.login(email, password);
            Model.getInstance().setJwtToken(token);
            System.out.println("Login successful. JWT: " + token);

            Stage stage = (Stage) error_lbl.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
            Model.getInstance().getViewFactory().showClientWindow();


        } catch (Exception e) {
            error_lbl.setText("Login failed: " + e.getMessage());
            System.err.println("Login error: " + e.getMessage());
        }

    }

    private void onRegistration () {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showRegistrationWindow();
    }

    //
}
