package com.hit.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class FirstScreenController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView logoView = new ImageView();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("file:///C:/Users/alfranco/HIT/HitBillingClient/src/main/java/com/hit/resources/transparent%20logo.png");
        logoView.setImage(image);
    }

    public void onCustomerButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("customer-view.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onElectricianButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("electrician-view.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
