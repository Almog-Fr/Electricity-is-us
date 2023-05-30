module com.example.hitbillingclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hit.view to javafx.fxml;
    exports com.hit.view;
}