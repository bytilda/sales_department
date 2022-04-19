package com.example.sales_department.controller.contract;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/contract/add_product_in_specification.fxml")
public class AddProductInSpecification {
    @Autowired
    FxWeaver fxWeaver;

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
        Parent root = fxWeaver.loadView(AddSpecification.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onDeleteProductFromOrderButtonClick(ActionEvent event) {

    }

}
