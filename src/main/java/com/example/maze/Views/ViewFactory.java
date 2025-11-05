package com.example.maze.Views;

import com.example.maze.Controllers.Client.ClientController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    //TODO: Admin view
    //Client View
    private final StringProperty clientSelectedMenuItem;    //setup view flag to change center pane
    private AnchorPane dashboardView;
    private AnchorPane transectionsView;
    private AnchorPane accountsView;

    public ViewFactory(){
        this.clientSelectedMenuItem = new SimpleStringProperty("");
    }
    /*
    * Clients views section
    * */

    public StringProperty getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView() {
        //because everytime user goes to another page from dashboard and returns back. we don't want to reload the page instead shows from memory
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getTransectionsView() {
        if (transectionsView == null) {
            try {
                transectionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transections.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transectionsView;
    }

    public AnchorPane getAccountsView() {
        if (accountsView == null) {
            try {
                accountsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public void showRegistrationWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Registration.fxml"));
        createStage(loader);
    }

    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);

        createStage(loader);
    }


    private void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
    } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("NAAS");
        stage.show();
    }

//    utility method  to close the state
    public void closeStage (Stage stage) {
        stage.close();
    }

}
