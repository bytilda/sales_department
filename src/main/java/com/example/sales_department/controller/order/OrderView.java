package com.example.sales_department.controller.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderView {

    @FXML
    private Button addOrderButton;

    @FXML
    private TableColumn<?, ?> contractorTableColumn;

    @FXML
    private TableColumn<?, ?> dateReceiveTableColumn;

    @FXML
    private TableColumn<?, ?> endShipmentTableColumn;

    @FXML
    private Button exitButton;

    @FXML
    private Button findOrderButton;

    @FXML
    private TableColumn<?, ?> numberOrderTableColumn;

    @FXML
    private TableColumn<?, ?> startShipmentTableColumn;

    @FXML
    private Label viewOrderLabel;

    @FXML
    private TableView<?> viewOrderTableView;

    @FXML
    void onAddOrderButtonClick(ActionEvent event) {

    }

    @FXML
    void onExitButtonClick(ActionEvent event) {

    }

    @FXML
    void onFindOderButtonClick(ActionEvent event) {

    }

}
