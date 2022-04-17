package com.example.sales_department.controller.contract;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditContract {

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

    }

    @FXML
    void onConfirmEditContractButtonClick(ActionEvent event) {

    }

    @FXML
    void onContractorComboBoxAction(ActionEvent event) {

    }

}
