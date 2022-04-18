package com.example.sales_department.controller.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/order/order_edit.fxml")
public class OrderEdit {
    @Autowired
    FxWeaver fxWeaver;

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
        Parent root = fxWeaver.loadView(OrderSearch.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onConfirmEditOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderSearch.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onCustomerComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onEditProductListInOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ChangeProductOrder.class);
        Scene scene = new Scene(root);
        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onSpecificationComboBoxAction(ActionEvent event) {

    }

}
