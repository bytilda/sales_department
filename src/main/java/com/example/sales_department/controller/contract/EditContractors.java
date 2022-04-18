package com.example.sales_department.controller.contract;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/contract/Edit_contractors.fxml")
public class EditContractors {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmEditContractorButton;

    @FXML
    private TextField correspondentTextField;

    @FXML
    private TextField estimatedTextField;

    @FXML
    private TextField kppTextField;

    @FXML
    private TextField labelAddressTextField;

    @FXML
    private TextField unnTextField;

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(Contractors.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onConfirmEditContractorButtonCkick(ActionEvent event) {
        Parent root = fxWeaver.loadView(Contractors.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
