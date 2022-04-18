package com.example.sales_department.controller.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@FxmlView("/com/example/sales_department/order/order_managment.fxml")
public class OrderManagment {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button addOrderButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button findOrderButton;

    @FXML
    private Label orderLabel;

    @FXML
    private Button viewOrderButton;

    @FXML
    void onAddOrderButtonClick(ActionEvent event) {

    }

    @FXML
    void onExitButtonClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/sales_department/hello-view.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onFindOrderButtonClick(ActionEvent event) {

    }

    @FXML
    void onViewOrderButtonClick(ActionEvent event) {

    }

}
