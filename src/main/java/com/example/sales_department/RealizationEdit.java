package com.example.sales_department;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RealizationEdit {

    @FXML
    private Label DateText;

    @FXML
    private ComboBox<?> adressComboBox;

    @FXML
    private Label adressText;

    @FXML
    private TableColumn<?, ?> amountTableColumn;

    @FXML
    private TableColumn<?, ?> codeProductTableColumn;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private DatePicker dateReceiveDatePicker;

    @FXML
    private Label dateReceiveLabel;

    @FXML
    private Label digitSumText;

    @FXML
    private Label editRealizeText;

    @FXML
    private TableColumn<?, ?> fullPriceTableColumn;

    @FXML
    private TableView<?> listProductTableView;

    @FXML
    private Label listProductText;

    @FXML
    private TableColumn<?, ?> nameProductTableColumn;

    @FXML
    private ComboBox<?> numberOrderComboBox;

    @FXML
    private Label numberOrderText;

    @FXML
    private Label numberUPDText;

    @FXML
    private TextField numberUPDTextField;

    @FXML
    private TableColumn<?, ?> priceTableColumn;

    @FXML
    private Button removeButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label statusText;

    @FXML
    private TextField statusTextField;

    @FXML
    private Label sumText;

    @FXML
    private Label timeShipmentText;

    @FXML
    private TextField timeShipmentTextField;

    @FXML
    void onAdressComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onNumberOrderComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onRemoveButtonClick(ActionEvent event) {

    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {

    }

}
