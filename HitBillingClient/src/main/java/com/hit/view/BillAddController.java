package com.hit.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BillAddController implements SceneSwitcher {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void changeScene(ActionEvent event, String sceneName) throws IOException {
        root = FXMLLoader.load(getClass().getResource(sceneName));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void onBackButtonClick(ActionEvent event) throws IOException {
        changeScene(event,"electrician-view.fxml");
    }
}
