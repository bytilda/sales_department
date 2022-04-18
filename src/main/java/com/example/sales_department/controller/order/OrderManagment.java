package com.example.sales_department.controller.order;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.controller.contract.MenuContract;
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
        Parent root = fxWeaver.loadView(OrderAdd.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onExitButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(HelloController.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onFindOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderSearch.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onViewOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderView.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
