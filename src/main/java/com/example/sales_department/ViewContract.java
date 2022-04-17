package com.example.sales_department;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ViewContract {

    @FXML
    private TableColumn<?, ?> ContractorTableColumn;

    @FXML
    private Button addContractButton;

    @FXML
    private TableColumn<?, ?> adressTableColumn;

    @FXML
    private TableColumn<?, ?> cityTableColumn;

    @FXML
    private TableColumn<?, ?> dataConclusionTableColumn;

    @FXML
    private TableColumn<?, ?> dataEndTableColumn;

    @FXML
    private TableColumn<?, ?> dataStartTableColumn;

    @FXML
    private Button editContractButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<?, ?> numberSpecificationTableColumn;

    @FXML
    private TableColumn<?, ?> statusTableColumn;

    @FXML
    private TableView<?> viewContractTableView;

    @FXML
    private Text viewContractText;

    @FXML
    void onAddContractButtonClick(ActionEvent event) {

    }

    @FXML
    void onEditContractButtonClick(ActionEvent event) {

    }

    @FXML
    void onExitButtonClick(ActionEvent event) {

    }

}
