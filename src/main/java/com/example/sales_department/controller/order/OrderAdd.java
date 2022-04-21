package com.example.sales_department.controller.order;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.controller.contract.ViewContract;
import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Order;
import com.example.sales_department.service.ContractService;
import com.example.sales_department.service.CustomerService;
import com.example.sales_department.service.OrderService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/order/order_add.fxml")
public class OrderAdd {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ContractService contractService;

    @FXML
    private Button addOrderButton;

    @FXML
    private Button addProductInOrderButton;

    @FXML
    private TableColumn<?, ?> amountTableColumn;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> customerComboBox;

    @FXML
    private ComboBox<String> contractComboBox;

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
    private ComboBox<String> specificationComboBox;

    @FXML
    public void initialize(){
        customerComboBox.setEditable(true);
        ObservableList<String> data = FXCollections.observableArrayList(
                customerService.getAll().stream().map(customer -> customer.getInn().toString()).collect(Collectors.toList()));
        FilteredList<String> filteredItems = new FilteredList<String>(data, p -> true);
        customerComboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = customerComboBox.getEditor();
            final String selected = customerComboBox.getSelectionModel().getSelectedItem();
            Platform.runLater(() -> {
                if (selected == null || !selected.equals(editor.getText())) {
                    filteredItems.setPredicate(item -> {
                        if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            });
        });
        customerComboBox.setItems(filteredItems);
    }

    @FXML
    void onAddOrderButtonClick(ActionEvent event) {
//        boolean isError = false;
//        String errorText = "";
//        Order order = new Order();
//
//        if(customerComboBox.getValue() == null){
//            isError = true;
//            errorText += "Поле не должно быть пустым. \n";
//        }
//        else{
//            Customer customer = customerService.getByInn(new BigInteger(customerComboBox.getValue()));
//            if(customer == null){
//                isError = true;
//                errorText += "Указанного Контрагента нет в справочнике. \n";
//            }
//            order.setIdCustomer(customer);
//        }
//
//        if(deliveryStartDateDatePicker.getValue() == null){
//            isError = true;
//            errorText += "Поле Даты заключения не должно быть пустым. \n";
//        }
//        else{
//            order.setConclusionDate(deliveryStartDateDatePicker.getValue());
//        }
//
//        if(deliveryFinishDateDatePicker.getValue() == null){
//            isError = true;
//            errorText += "Поле Даты вступления договора в силу не должно быть пустым. \n";
//        }
//        else
//            order.setValidFrom(deliveryFinishDateDatePicker.getValue());
//
//        if(specificationComboBox.getValue() == null){
//            isError = true;
//            errorText += "Поле не должно быть пустым. \n";
//        }
//        else{
//            Customer customer = customerService.getByInn(new BigInteger(specificationComboBox.getValue()));
//            if(customer == null){
//                isError = true;
//                errorText += "Указанного Контрагента нет в справочнике. \n";
//            }
//            order.setIdCustomer(customer);
//        }
//
//        if(!isError) {
//            orderService.add(order);
//            Parent root = fxWeaver.loadView(ViewContract.class);
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        }
//        else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Ошибка");
//            alert.setHeaderText("Заполните все поля");
//            alert.setContentText(errorText);
//            alert.showAndWait();
//        }
//
//    }
    }

    @FXML
    void onAddProductInOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(AddProductOrdrer.class);
        Scene scene = new Scene(root);
        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
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
        Customer customer = customerService.getByInn(new BigInteger(customerComboBox.getValue()));
        if(customer != null) {

            contractComboBox.setEditable(true);
            ObservableList<String> data = FXCollections.observableArrayList(
                    contractService.getAllCustomerId(customer).stream().map(contract -> contract.getContractNumber().toString()).collect(Collectors.toList()));
            FilteredList<String> filteredItems = new FilteredList<String>(data, p -> true);
            contractComboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
                final TextField editor = contractComboBox.getEditor();
                final String selected = contractComboBox.getSelectionModel().getSelectedItem();
                Platform.runLater(() -> {
                    if (selected == null || !selected.equals(editor.getText())) {
                        filteredItems.setPredicate(item -> {
                            if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                                return true;
                            } else {
                                return false;
                            }
                        });
                    }
                });
            });
            contractComboBox.setItems(filteredItems);
        }
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

    @FXML
    void onContractComboBoxAction(ActionEvent event) {

    }

}
