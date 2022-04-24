package com.example.sales_department.controller.order;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.controller.Utils;
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
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    SpecificationService specificationService;
    @Autowired
    ProductListInSpecificationService productListInSpecificationService;
    @Autowired
    ProductListInOrderService productListInOrderService;

    Customer currentCustomer;
    List<Contract> contracts;
    Contract currentContract;
    List<Specification> specifications;
    Specification currentSpecification;

    ObservableList<ProductListInOrder> products;
    Order order;

    @FXML
    private Button addOrderButton;

    @FXML
    private TextField orderNumberTextField;

    @FXML
    private Button addProductInOrderButton;

    @FXML
    private TableColumn<ProductListInOrder, Long> amountTableColumn;

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
    private DatePicker receiveDateDatePicker;

    @FXML
    private Button exitButton;

    @FXML
    private Button findOrderButton;

    @FXML
    private Button orderListButton;

    @FXML
    private TableColumn<ProductListInOrder, String> priceForAllTableColumn;

    @FXML
    private TableColumn<ProductListInOrder, String> priceForOneTableColumn;

    @FXML
    private Label priceForOrderLabel;

    @FXML
    private TableColumn<ProductListInOrder, Long> productCipherTableColumn;

    @FXML
    private TableView<ProductListInOrder> productListInOrderTebleView;

    @FXML
    private TableColumn<ProductListInOrder, String> productNameTableColumn;

    @FXML
    private ComboBox<String> specificationComboBox;

    @FXML
    public void initialize(){
        products = FXCollections.observableArrayList();
        order = new Order();
        customerComboBox.setEditable(true);
        ObservableList<String> data = FXCollections.observableArrayList(
                customerService.getAll().stream().map(customer -> customer.getInn().toString()).collect(Collectors.toList()));
        Utils.addFilter(data, customerComboBox);

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
            productListInOrderTebleView.refresh();
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



    }

    @FXML
    void onAddOrderButtonClick(ActionEvent event) {
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
    void onAddProductInOrderButtonClick(ActionEvent event) {
//        Parent root = fxWeaver.loadView(AddProductOrdrer.class);
//        Scene scene = new Scene(root);
//        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        products.remove(productListInOrderTebleView.getSelectionModel().getSelectedItem());


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
            productListInOrderTebleView.setItems(products);
            updateSum();
        }
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

    public void deleteProduct(ProductNomenclature productNomenclature){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getIdProduct().getId().equals(productNomenclature.getId())){
                products.remove(i);
                break;
            }
        }
    }

    public void addProduct(ProductNomenclature productNomenclature){
        boolean isExist = false;
        for(ProductListInOrder productList: products){
            if(Objects.equals(productList.getIdProduct().getId(), productNomenclature.getId())){
                isExist = true;
                break;
            }
        }
        if(!isExist)
            products.add(ProductListInOrder.builder().idProduct(productNomenclature).build());
        productListInOrderTebleView.refresh();
    }

}
