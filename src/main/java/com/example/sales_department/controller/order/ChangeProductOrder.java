package com.example.sales_department.controller.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeProductOrder {

    @FXML
    private AnchorPane addProductInOrderAnchorPane;

    @FXML
    private Button addProductInOrderButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmChangesButton;

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
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/sales_department/order/order_edit.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onConfirmChangesButtonClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/sales_department/order/order_edit.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onDeleteProductFromOrderButtonClick(ActionEvent event) {

    }

}
