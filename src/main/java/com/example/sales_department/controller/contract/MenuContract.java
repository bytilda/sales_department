package com.example.sales_department.controller.contract;

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
@FxmlView("/com/example/sales_department/contract/menu_contract.fxml")
public class MenuContract {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button addContractButton;

    @FXML
    private Button contractListButton;

    @FXML
    private Button contractorsButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button findContractButton;

    @FXML
    private Button specificationsButton;

    @FXML
    void onAddContractButtonClick(ActionEvent event) {

    }

    @FXML
    void onContractListButtonClick(ActionEvent event) {

    }

    @FXML
    void onCotractorsButtonClick(ActionEvent event) {

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
    void onFindContractButtonClick(ActionEvent event) {

    }

    @FXML
    void onSpecificationsButtonClick(ActionEvent event) {

    }

}
