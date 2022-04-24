package com.example.sales_department.controller.order;

import com.example.sales_department.controller.Utils;
import com.example.sales_department.controller.contract.ContractManagment;
import com.example.sales_department.entity.*;
import com.example.sales_department.service.*;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/order/order_edit.fxml")
public class OrderEdit {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    FiasService fiasService;
    @Autowired
    ContractService contractService;
    @Autowired
    SpecificationService specificationService;
    @Autowired
    ProductListInOrderService productListInOrderService;
    @Autowired
    ProductListInSpecificationService productListInSpecificationService;

    @Setter
    Order order;

    Customer currentCustomer;
    List<Contract> contracts;
    Contract currentContract;
    List<Specification> specifications;
    Specification currentSpecification;


    @FXML
    private Button confirmEditOrderButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TableView<ProductListInOrder> productListTableView;

    @FXML
    private TableColumn<ProductListInOrder, Long> productCipherTableColumn;

    @FXML
    private TableColumn<ProductListInOrder, String> productNameTableColumn;

    @FXML
    private TableColumn<ProductListInOrder, String> priceForOneTableColumn;

    @FXML
    private TableColumn<ProductListInOrder, Long> amountTableColumn;

    @FXML
    private TableColumn<ProductListInOrder, String> priceForAllTableColumn;

    @FXML
    private Button editProductListInOrderButton;

    @FXML
    private Label priceForOrderLabel;

    @FXML
    private ComboBox<String> specificationComboBox;

    @FXML
    private DatePicker deliveryStartDateDatePicker;

    @FXML
    private DatePicker deliveryFinishDateDatePicker;

    @FXML
    private ComboBox<String> customerComboBox;

    @FXML
    private ComboBox<String> contractComboBox;

    @FXML
    private TextField orderNumberTextField;

    @FXML
    private DatePicker receiveDateDatePicker;

    ObservableList<ProductListInOrder> products;

    @FXML
    public void initialize() {
        products = FXCollections.observableArrayList(productListInOrderService.getAllByOrder(order.getId()));
        customerComboBox.setEditable(true);
        ObservableList<String> data = FXCollections.observableArrayList(
                customerService.getAll().stream().map(customer -> customer.getInn().toString()).collect(Collectors.toList()));
        Utils.addFilter(data, customerComboBox);

        contractComboBox.setEditable(true);
        Customer customer = order.getIdSpecification().getIdContract().getIdCustomer();
        ObservableList<String> contractData = FXCollections.observableArrayList(
                contractService.getAllCustomerId(customer).stream().map(contract -> contract.getContractNumber().toString()).collect(Collectors.toList()));
        Utils.addFilter(contractData, contractComboBox);

        specificationComboBox.setEditable(true);
        ObservableList<String> specData = FXCollections.observableArrayList(
                specificationService
                        .getAllByContract(order.getIdSpecification().getIdContract())
                        .stream().map(specification -> specification.getApplicationNumber().toString())
                        .collect(Collectors.toList()));
        Utils.addFilter(specData, specificationComboBox);


        productCipherTableColumn.setCellValueFactory(cd -> new SimpleLongProperty(cd.getValue().getIdProduct().getProductCipher()).asObject());
        productNameTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdProduct().getProductName()));

        priceForOneTableColumn.setCellValueFactory(cd -> {
            if(cd.getValue().getPrice() != null)
                return new SimpleStringProperty(cd.getValue().getPrice().toString());
            else return new SimpleStringProperty("");
        });

        amountTableColumn.setCellValueFactory(cd -> {
            if(cd.getValue().getAmount() != null){
                return new SimpleLongProperty(cd.getValue().getAmount()).asObject();
            }
            else return null;
        });
        amountTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        amountTableColumn.setOnEditCommit(event -> {
            ProductListInOrder productList = event.getRowValue();
            productList.setAmount(event.getNewValue());
            productListTableView.refresh();
            updateSum();
        });

        priceForAllTableColumn.setCellValueFactory(cd -> {
            if((cd.getValue().getPrice() != null) && (cd.getValue().getAmount() != null)){
                return new SimpleStringProperty(cd.getValue().getPrice().multiply(new BigDecimal(cd.getValue().getAmount().toString())).toString());
            }
            else return new SimpleStringProperty("");
        });

        orderNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                orderNumberTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        orderNumberTextField.setText(order.getOrderNumber().toString());
        customerComboBox.setValue(order.getIdSpecification().getIdContract().getIdCustomer().getInn().toString());
        contractComboBox.setValue(order.getIdSpecification().getIdContract().getContractNumber().toString());
        specificationComboBox.setValue(order.getIdSpecification().getApplicationNumber().toString());
        deliveryStartDateDatePicker.setValue(order.getDeliveryBegin());
        deliveryFinishDateDatePicker.setValue(order.getDeliveryFinish());
        receiveDateDatePicker.setValue(order.getReceiptDay());
        products = FXCollections.observableArrayList(productListInOrderService.getAllByOrder(order.getId()));
        productListTableView.setItems(products);

        currentCustomer = order.getIdSpecification().getIdContract().getIdCustomer();
        currentContract = order.getIdSpecification().getIdContract();
        currentSpecification = order.getIdSpecification();
    }

    @FXML
    void onContractComboBoxAction(ActionEvent event) {
        Long contractNumber = Long.parseLong(contractComboBox.getValue());
        Contract contract = contractService.getByContractNumber(contractNumber);
        currentContract = contract;
        products.clear();
        if (contract != null) {
            specificationComboBox.setEditable(true);
            specificationComboBox.setDisable(false);
            specifications = specificationService.getAllByContract(contract);
            ObservableList<String> data = FXCollections.observableArrayList(
                    specifications.stream().map(s -> s.getApplicationNumber().toString()).collect(Collectors.toList()));
            Utils.addFilter(data, specificationComboBox);
        }
        else{
            specificationComboBox.setDisable(true);
        }
    }

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
        boolean isError = false;
        String errorText = "";

        Status status = new Status();
        status.setId(1L);
        order.setStatus(status);

        if(currentCustomer == null){
            isError = true;
            errorText += "Поле контрагента пустое либо указанного контрагента не найдено в справочнике. \n";
        }

        if(deliveryStartDateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле начала поставки не должно быть пустым. \n";
        }
        else{
            order.setDeliveryBegin(deliveryStartDateDatePicker.getValue());
        }

        if(orderNumberTextField.getText() == null || orderNumberTextField.getText().isEmpty()){
            isError = true;
            errorText += "Поле номера заказа не должно быть пустым. \n";
        }
        else{
            Long orderNumber = Long.parseLong(orderNumberTextField.getText());
            order.setOrderNumber(orderNumber);
        }

        if(deliveryFinishDateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле окончания поставки не должно быть пустым. \n";
        }
        else
            order.setDeliveryFinish(deliveryFinishDateDatePicker.getValue());

        if(receiveDateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле даты поступления заказа не должно быть пустым. \n";
        }
        else
            order.setReceiptDay(deliveryFinishDateDatePicker.getValue());

        if(currentSpecification == null){
            isError = true;
            errorText += "Поле спецификации пустое либо в базе отсутвует уканный номер приложения. \n";
        }
        else{
            order.setIdSpecification(currentSpecification);
        }

        if(!isError) {
            orderService.add(order);
            productListInOrderService.deleteAllInOrder(order.getId());
            for(ProductListInOrder productListInOrder: products){
                ProductListInOrderId id = new ProductListInOrderId();
                id.setIdOrder(order.getId());
                id.setIdProduct(productListInOrder.getIdProduct().getId());
                productListInOrder.setId(id);
                productListInOrderService.add(productListInOrder);
            }
            Parent root = fxWeaver.loadView(OrderManagment.class);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Заполните все поля");
            alert.setContentText(errorText);
            alert.showAndWait();
        }
    }

    @FXML
    void onCustomerComboBoxAction(ActionEvent event) {
        products.clear();
        Customer customer = customerService.getByInn(new BigInteger(customerComboBox.getValue()));
        currentCustomer = customer;
        if(customer != null) {
            contractComboBox.setEditable(true);
            contracts = contractService.getAllCustomerId(customer);
            ObservableList<String> data = FXCollections.observableArrayList(
                    contracts.stream().map(contract -> contract.getContractNumber().toString()).collect(Collectors.toList()));
            Utils.addFilter(data, contractComboBox);
        }
        else{
            contractComboBox.setDisable(true);
            specificationComboBox.setDisable(true);
        }
        productListTableView.refresh();
    }

    @FXML
    void onEditProductListInOrderButtonClick(ActionEvent event) {
        products.remove(productListTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onSpecificationComboBoxAction(ActionEvent event) {
        Long specificationNumber = Long.parseLong(specificationComboBox.getValue());
        currentSpecification = null;
        products.clear();
        for(Specification specification: specifications){
            if(specificationNumber.equals(specification.getApplicationNumber())){
                currentSpecification = specification;
            }
        }
        if(currentSpecification != null) {
            List<ProductList> productListInSpecification = productListInSpecificationService.getAllBySpecificationId(currentSpecification.getId());
            for(ProductList productList: productListInSpecification){
                ProductListInOrder productListInOrder = new ProductListInOrder();
                productListInOrder.setOrder(order);
                productListInOrder.setIdProduct(productList.getIdProduct());
                productListInOrder.setAmount(productList.getAmount());
                productListInOrder.setPrice(productList.getPrice());
                products.add(productListInOrder);
            }
            productListTableView.setItems(products);
            updateSum();
        }
        productListTableView.refresh();
    }

    public void updateSum(){
        BigDecimal total = new BigDecimal("0");
        for(int i = 0; i < products.size(); i++){
            String stringValue = priceForAllTableColumn.getCellObservableValue(i).getValue();
            if(stringValue != null && !stringValue.isEmpty()) {
                BigDecimal value = new BigDecimal(stringValue);
                total = total.add(value);
            }
        }
        priceForOrderLabel.setText(total.toString());
    }

}
