package com.example.sales_department.controller.realization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RealizeMenu {

    @FXML
    private Button addRealizeButton;

    @FXML
    private Button editRealizeButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button viewRealizeButton;

    @FXML
    void onAddRealizeButtonClick(ActionEvent event) {

    }

    @FXML
    void onEditRealizeButtonClick(ActionEvent event) {

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
    void onViewRealizeButtonClick(ActionEvent event) {

    }

}
