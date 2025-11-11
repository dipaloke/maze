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

public class RegistrationController implements Initializable {
    public ChoiceBox<String> acc_selector;
    public Label payee_address_lbl;
    public TextField register_full_name;
    public Label password_lbl;
    public TextField register_email;
    public Label payee_address_lbl1;
    public TextField register_password;
    public Button registration_btn;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.getItems().addAll("INTERN", "ENGINEER", "ADMIN");

        acc_selector.setValue("INTERN");

        login_btn.setOnAction(actionEvent -> onLoginClick());
        registration_btn.setOnAction(actionEvent -> onRegisterClick());
    }

    private void onLoginClick() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    private  void  onRegisterClick() {
        String name = register_full_name.getText();
        String email = register_email.getText();
        String password = register_password.getText();
        String role = acc_selector.getValue();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
            error_lbl.setText("Please fill all fields and select a role");
            return;
        }

        try {
            boolean success = HttpClientHelper.register(name, email, password, role);
            if (success) {
                error_lbl.setText("Registration successful! Redirecting...");
                System.out.println("user registered successfully");

                //Delay before redirecting
                Thread.sleep(1000);
                onLoginClick();
            } else {
                error_lbl.setText("Registration failed");
                System.err.println("Failed to register");
            }
        } catch (Exception e) {
            error_lbl.setText("Error: " + e.getMessage());
            System.err.println("Registration error: " + e.getMessage());
        }
    }
}
