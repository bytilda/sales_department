package com.example.sales_department;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class Specification {

    @FXML
    private Button addSpecificationButton;

    @FXML
    private TableColumn<?, ?> adressTableColumn;

    @FXML
    private TableColumn<?, ?> amountTableColumn;

    @FXML
    private TableColumn<?, ?> dataTableColumn;

    @FXML
    private TableColumn<?, ?> numberContractTableColumn;

    @FXML
    private TableColumn<?, ?> numberTableColumn;

    @FXML
    private TableView<?> specificationTableView;

    @FXML
    private Text specificationText;

    @FXML
    private TableColumn<?, ?> unitMeasureTableColumn;

    @FXML
    void onAddSpecificationButtonClick(ActionEvent event) {

    }

}
