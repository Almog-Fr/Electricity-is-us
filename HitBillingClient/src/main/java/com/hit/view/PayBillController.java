package com.hit.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

public class PayBillController implements Initializable,SceneSwitcher {
    private Stage stage;
    private Scene scene;
    private Parent root;

    Text myText;

    @FXML
    private ChoiceBox<String> monthChoiceBox,yearChoiceBox;
    private String[] months = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    private String[] years = {"23","24","25","26","27","28","29"};
    @FXML
    private TextField creditCardNumber;
    @FXML
    private Label customerMessage;


    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        yearChoiceBox.getItems().addAll(years);
        monthChoiceBox.getItems().addAll(months);


    }

    public void payNowButtonClick(ActionEvent event) throws IOException{

        String creditCardNumberString = creditCardNumber.getText();
        String chosenMonth = monthChoiceBox.getValue();
        String chosenYear = yearChoiceBox.getValue();


        if(creditCardNumberString.length() < 16){
            customerMessage.setText("Credit card details are invalid");
            return;
        }

        LocalDate date = LocalDate.of(Integer.parseInt(chosenYear),Integer.parseInt(chosenMonth),1);
        if(date.isBefore(LocalDate.now())){
            customerMessage.setText("Credit card details are invalid");
            return;
        }

        //Set Bill as payed
    }

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
        changeScene(event,"customer-view.fxml");
    }
}
