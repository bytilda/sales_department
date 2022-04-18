package com.example.sales_department.controller.contract;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/contract/add_contractors.fxml")
public class AddContractors {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button addContractorsButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField correspondentTextField;

    @FXML
    private TextField estimatedTextField;

    @FXML
    private TextField kppTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<?> okpoComboBox;

    @FXML
    private ComboBox<?> adressComboBox;

    @FXML
    private TextField unnTextField;

    @FXML
    void onAddContractorsButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(Contractors.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(Contractors.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
