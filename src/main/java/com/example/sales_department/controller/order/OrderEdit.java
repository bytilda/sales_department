package com.example.sales_department.controller.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderEdit {

    @FXML
    private TableColumn<?, ?> amountTableColumn;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmEditOrderButton;

    @FXML
    private ComboBox<?> customerComboBox;

    @FXML
    private DatePicker deliveryFinifhDateDatePicker;

    @FXML
    private DatePicker deliveryStartDateDatePicker;

    @FXML
    private Button editProductListInOrderButton;

    @FXML
    private TableColumn<?, ?> priceForAllTableColumn;

    @FXML
    private TableColumn<?, ?> priceForOneTableColumn;

    @FXML
    private Label priceForOrderLabel;

    @FXML
    private TableColumn<?, ?> productCipherTableColumn;

    @FXML
    private TableView<?> productListTableView;

    @FXML
    private TableColumn<?, ?> productNameTableColumn;

    @FXML
    private ComboBox<?> specificationComboBox;

    @FXML
    void onCancelButtonClick(ActionEvent event) {

    }

    @FXML
    void onConfirmEditOrderButtonClick(ActionEvent event) {

    }

    @FXML
    void onCustomerComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onEditProductListInOrderButtonClick(ActionEvent event) {

    }

    @FXML
    void onSpecificationComboBoxAction(ActionEvent event) {

    }

}
