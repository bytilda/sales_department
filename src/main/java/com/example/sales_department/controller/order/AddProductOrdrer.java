package com.example.sales_department.controller.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AddProductOrdrer {

    @FXML
    private AnchorPane addProductInOrderAnchorPane;

    @FXML
    private Button addProductInOrderButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteProductFromOrderButton;

    @FXML
    private TableView<?> nomenclatureProductTableView;

    @FXML
    private TableColumn<?, ?> productCipherTableColumn;

    @FXML
    private TableColumn<?, ?> productNameTableColumn;

    @FXML
    private TableColumn<?, ?> weightTableColumn;

    @FXML
    void onAddProductInOrderButtonClick(ActionEvent event) {

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {

    }

    @FXML
    void onDeleteProductFromOrderButtonClick(ActionEvent event) {

    }

}
