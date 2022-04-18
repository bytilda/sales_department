package com.example.sales_department.controller.order;

import com.example.sales_department.controller.HelloController;
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
@FxmlView("/com/example/sales_department/order/order_add.fxml")
public class OrderAdd {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button addOrderButton;

    @FXML
    private Button addProductInOrderButton;

    @FXML
    private TableColumn<?, ?> amountTableColumn;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<?> customerComboBox;

    @FXML
    private DatePicker deliveryFinishDateDatePicker;

    @FXML
    private DatePicker deliveryStartDateDatePicker;

    @FXML
    private Button exitButton;

    @FXML
    private Button findOrderButton;

    @FXML
    private Button orderListButton;

    @FXML
    private TableColumn<?, ?> priceForAllTableColumn;

    @FXML
    private TableColumn<?, ?> priceForOneTableColumn;

    @FXML
    private Label priceForOrderLabel;

    @FXML
    private TableColumn<?, ?> productCipherTableColumn;

    @FXML
    private TableView<?> productListInOrderTebleView;

    @FXML
    private TableColumn<?, ?> productNameTableColumn;

    @FXML
    private ComboBox<?> specificationComboBox;

    @FXML
    void onAddOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onAddProductInOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(AddProductOrdrer.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onCustomerComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onExitButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(HelloController.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onFindOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderSearch.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onOrderListButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderView.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onSpecificationComboBoxAction(ActionEvent event) {

    }

}
