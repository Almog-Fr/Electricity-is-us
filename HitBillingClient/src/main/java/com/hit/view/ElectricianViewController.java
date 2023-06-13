package com.hit.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ElectricianViewController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeScene(ActionEvent event, String sceneName) throws IOException {
        root = FXMLLoader.load(getClass().getResource(sceneName));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goBackElectricianMenu(ActionEvent event) throws IOException {
        changeScene(event,"hello-view.fxml");
    }

    public void onAddBillButton(ActionEvent event) throws IOException {
        changeScene(event,"bill-add-view.fxml");
    }

    public void onAddCustomerClick(ActionEvent event) throws IOException {
        changeScene(event,"customer-add-view.fxml");
    }

    public void onCustomerDeleteButtonClick(ActionEvent event) throws IOException {
        changeScene(event,"customer-delete-view.fxml");
    }

    public void onBillDeleteClick(ActionEvent event) throws IOException {
        changeScene(event,"bill-delete-view.fxml");
    }

}
