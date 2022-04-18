package com.example.sales_department.controller;

import com.example.sales_department.controller.contract.MenuContract;
import com.example.sales_department.controller.directory.DepartamentDirectory;
import com.example.sales_department.controller.order.OrderManagment;
import com.example.sales_department.controller.realization.RealizationAdd;
import com.example.sales_department.controller.realization.RealizeMenu;
import com.example.sales_department.service.FiasService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@FxmlView("/com/example/sales_department/hello-view.fxml")
public class HelloController {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button contractButton;

    @FXML
    private Button directoryButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button realizationButton;

    @FXML
    void onContractButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(MenuContract.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onDirectoryButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(DepartamentDirectory.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onOrderClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onRealizationClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizeMenu.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
