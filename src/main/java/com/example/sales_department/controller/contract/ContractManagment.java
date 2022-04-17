package com.example.sales_department.controller.contract;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ContractManagment {

    @FXML
    private Button addContractButton;

    @FXML
    private TableColumn<?, ?> closingCityTableColumn;

    @FXML
    private TextField closingCityTextField;

    @FXML
    private DatePicker closingDateDatePicker;

    @FXML
    private TableColumn<?, ?> closingDateTableColumn;

    @FXML
    private Button contractListButton;

    @FXML
    private TableColumn<?, ?> contractNumberTableColumn;

    @FXML
    private TextField contractNumberTextField;

    @FXML
    private ComboBox<?> contractorComboBox;

    @FXML
    private TableColumn<?, ?> contractorTableColumn;

    @FXML
    private Button editContractButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableView<?> findContractTableView;

    @FXML
    private DatePicker forceDateDatePicker;

    @FXML
    private TableColumn<?, ?> forceDateTableColumn;

    @FXML
    private Button searchContractButton;

    @FXML
    private DatePicker validDateDatePicker;

    @FXML
    private TableColumn<?, ?> validDateTableColumn;

    @FXML
    void onAddContractButtonClick(ActionEvent event) {

    }

    @FXML
    void onContractListButtonClick(ActionEvent event) {

    }

    @FXML
    void onContractorComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onEditContractButtonClick(ActionEvent event) {

    }

    @FXML
    void onExitButtonClick(ActionEvent event) {

    }

    @FXML
    void onSearchContractButtonClick(ActionEvent event) {

    }

}
