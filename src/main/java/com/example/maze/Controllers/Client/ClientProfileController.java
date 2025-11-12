package com.example.maze.Controllers.Client;

import com.example.maze.Models.Model;
import com.example.maze.services.HttpClientHelper;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

public class ClientProfileController implements Initializable {
    public Text welcome_text;
    public Label date_lbl;
    public Circle profile_pic;
    public Text profile_initials;
    public TextField name_field;
    public TextField email_field;
    public PasswordField password_field;
    public Button update_btn;
    public Button cancel_btn;
    public Label status_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date_lbl.setText("Today " + LocalDate.now());
        loadProfileDate();

        update_btn.setOnAction(event -> onUpdate());
        cancel_btn.setOnAction(event -> onCancel());
    }

    private  void loadProfileDate() {
        try {
            Map userData = HttpClientHelper.getprofile(Model.getInstance().getJwtToken());
            String name = (String) userData.get("name");
            String email = (String) userData.get("email");
            String role = (String) userData.get("role");

            name_field.setText(name);
            email_field.setText(email);
            welcome_text.setText("welcome, " + name + "(" + role + ")");
            profile_initials.setText(getInitials(name));
        } catch (Exception e) {
            status_lbl.setText("Failed to load Profile.");
            System.err.println("Profile load error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private  void onUpdate() {
        String name = name_field.getText();
        String email = email_field.getText();
        String password = password_field.getText();

        if(name.isEmpty() || email.isEmpty()) {
            status_lbl.setText("Name and Email cannot be empty. ");
            return;
        }
        update_btn.setDisable(true);
        status_lbl.setText("Updating...");

        new Thread(() -> {
            try {
                boolean success = HttpClientHelper.updateProfile(Model.getInstance().getJwtToken(), name, email, password);
                javafx.application.Platform.runLater(() -> {
                    if (success) {
                        status_lbl.setText("Profile updated Successfully!");
                    } else {
                        status_lbl.setText("Update failed. ");
                    }
                    update_btn.setDisable(false);
                });
            } catch (Exception e) {
                javafx.application.Platform.runLater(() -> {
                    status_lbl.setText("Error: " + e.getMessage());
                    update_btn.setDisable(false);
                });
                System.err.println("Update error: " + e.getMessage());
            }
        }).start();
    }

    private  void  onCancel () {
        Stage stage = (Stage) cancel_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }

    private String getInitials(String name) {
        String[] parts = name.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String part: parts) {
            if(!part.isEmpty()) initials.append(part.charAt(0));
        }
        return  initials.toString().toUpperCase();
    }
}
