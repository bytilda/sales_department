package com.example.sales_department.controller.contract;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/contract/edit_contract.fxml")
public class EditContract {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker closindDateDatePicker;

    @FXML
    private TextField closingCityTextField;

    @FXML
    private Button confirmEditContractButton;

    @FXML
    private TextField consigneerAddressTextField;

    @FXML
    private TextArea contractContentTextArea;

    @FXML
    private TextField contractNumberTextField;

    @FXML
    private ComboBox<?> contractorComboBox;

    @FXML
    private DatePicker forceDateDatePicker;

    @FXML
    private DatePicker validDateDatePicker;

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ContractManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onConfirmEditContractButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ContractManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onContractorComboBoxAction(ActionEvent event) {

    }

}
