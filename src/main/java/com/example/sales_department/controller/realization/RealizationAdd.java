package com.example.sales_department.controller.realization;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.controller.Utils;
import com.example.sales_department.controller.order.OrderManagment;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/realization/realization_add.fxml")
public class RealizationAdd {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    RealizationService realizationService;
    @Autowired
    ProductListInSpecificationService productListInSpecificationService;
    @Autowired
    FiasService fiasService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductListInOrderService productListInOrderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductListInRealizationService productListInRealizationService;

    Customer currentCustomer = null;
    List<Order> orders;
    Order currentOrder = null;
    Realization realization;

    @FXML
    private Label addRealizeLabel;

    @FXML
    private Label numberUPDLabel;

    @FXML
    private Label DateLabel;

    @FXML
    private Label timeShipmentLabel;

    @FXML
    private TextField numberUPDTextField;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private Label listProductLabel;

    @FXML
    private TableView<ProductListInRealization> listProductTableView;

    @FXML
    private TableColumn<ProductListInRealization, Long> codeProductTableColumn;

    @FXML
    private TableColumn<ProductListInRealization, String> nameProductTableColumn;

    @FXML
    private TableColumn<ProductListInRealization, String> unitTableColumn;

    @FXML
    private TableColumn<ProductListInRealization, Long> amountTableColumn;

    @FXML
    private TableColumn<ProductListInRealization, String> priceTableColumn;

    @FXML
    private TableColumn<ProductListInRealization, String> fullPriceTableColumn;

    @FXML
    private Label sumLabel;

    @FXML
    private Label digitLabel;

    @FXML
    private Label adressLabel;

    @FXML
    private ComboBox<String> adressLabelComboBox;

    @FXML
    private ComboBox<String> numberOrderComboBox;

    @FXML
    private Label numberOrderLabel;

    @FXML
    private Button addRealizeButton;

    @FXML
    private Button removeRealizeButton;

    @FXML
    private Label dateReceiveLabel;

    @FXML
    private DatePicker dateReceiveDatePicker;

    @FXML
    private DatePicker shippingDateDatePicker;

    @FXML
    private Label numberUPDLabel1;

    @FXML
    private ComboBox<String> customerComboBox;

    @FXML
    private Button addProductInOrderButton;

    @FXML
    private Button findRealizeButton;

    @FXML
    private Button viewRealizeButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Label organizationNameLabel;

    ObservableList<ProductListInRealization> products = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        realization = new Realization();
        products.clear();

        adressLabelComboBox.setEditable(true);
        ObservableList<String> addrData = FXCollections.observableArrayList(
                fiasService.getAll().stream().map(Object::toString).collect(Collectors.toList()));
        Utils.addFilter(addrData, adressLabelComboBox);

        ObservableList<String> data = FXCollections.observableArrayList(
                customerService.getAll().stream().map(customer -> customer.getInn().toString()).collect(Collectors.toList()));
        Utils.addFilter(data, customerComboBox);

        numberUPDTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numberUPDTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        codeProductTableColumn.setCellValueFactory(cd -> new SimpleLongProperty(cd.getValue().getIdProduct().getProductCipher()).asObject());
        nameProductTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdProduct().getProductName()));

        priceTableColumn.setCellValueFactory(cd -> {
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
            ProductListInRealization productList = event.getRowValue();
            productList.setAmount(event.getNewValue());
            listProductTableView.refresh();
            updateSum();
        });

        fullPriceTableColumn.setCellValueFactory(cd -> {
            if((cd.getValue().getPrice() != null) && (cd.getValue().getAmount() != null)){
                return new SimpleStringProperty(cd.getValue().getPrice().multiply(new BigDecimal(cd.getValue().getAmount().toString())).toString());
            }
            else return new SimpleStringProperty("");
        });

        unitTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdProduct().getUnits().getId()));

        listProductTableView.setItems(products);


    }

    @FXML
    void onAddProductInOrderButtonClick(ActionEvent event) {
//        Parent root = fxWeaver.loadView(RealizationChangeProduct.class);
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.setScene(scene);
//        stage.show();
        products.remove(listProductTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onAddRealizeButtonClick(ActionEvent event) {
        boolean isError = false;
        String errorText = "";


        if(currentCustomer == null){
            isError = true;
            errorText += "Поле контрагента пустое либо указанного контрагента не найдено в справочнике. \n";
        }

        if(currentOrder == null){
            isError = true;
            errorText += "Поле номера заказа не должно быть пустым. \n";
        }
        else{
            realization.setIdOrder(currentOrder);
        }

        if(dateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле даты не должно быть пустым. \n";
        }
        else{
            realization.setDate(dateDatePicker.getValue());
        }

        if(numberUPDTextField.getText() == null || numberUPDTextField.getText().isEmpty()){
            isError = true;
            errorText += "Поле номера УПД не должно быть пустым. \n";
        }
        else{
            Long updNumber = Long.parseLong(numberUPDTextField.getText());
            realization.setUpdNumber(updNumber);
        }

        if(shippingDateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле даты отгрузки не должно быть пустым. \n";
        }
        else
            realization.setFactedTimeOfShipment(shippingDateDatePicker.getValue());

        if(dateReceiveDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле даты приемки не должно быть пустым. \n";
        }
        else
            realization.setReceivingDate(dateReceiveDatePicker.getValue());


        if(adressLabelComboBox.getValue() == null){
            isError = true;
            errorText += "Поле адреса грузополучателя не должно быть пустым. \n";
        }
        else{
            Fia address = fiasService.getById(adressLabelComboBox.getValue());
            if(address == null){
                isError = true;
                errorText += "Указанного адреса нет в классификаторе ФИАС. \n";
            }
            realization.setConsigneeAddress(address);
        }

        if(!isError) {
            realizationService.add(realization);

            for(ProductListInRealization productListInRealization: products){
                ProductListInRealizationId id = new ProductListInRealizationId();
                id.setIdRealization(realization.getId());
                id.setIdProduct(productListInRealization.getIdProduct().getId());
                productListInRealization.setId(id);
                productListInRealizationService.save(productListInRealization);
            }
            Parent root = fxWeaver.loadView(RealizationView.class);
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
    void onAdressComboBoxAction(ActionEvent event) {

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
    void onFindRealizeButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizeManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onNumberOrderComboBoxAction(ActionEvent event) {
        if((numberOrderComboBox.getValue() != null) && !numberOrderComboBox.getValue().isEmpty()) {
            Long orderNumber = Long.parseLong(numberOrderComboBox.getValue());
            Order order = orders.stream().filter(ord -> Objects.equals(ord.getOrderNumber(), orderNumber)).findFirst().orElse(null);
            currentOrder = order;
            if(order != null){
                List<ProductListInOrder> productsListInOrder = productListInOrderService.getAllByOrder(order.getId());
                for(ProductListInOrder productInOrder: productsListInOrder){
                    ProductListInRealization productListInRealization = new ProductListInRealization();
                    productListInRealization.setIdRealization(realization);
                    productListInRealization.setIdProduct(productInOrder.getIdProduct());
                    productListInRealization.setPrice(productInOrder.getPrice());
                    productListInRealization.setAmount(productInOrder.getAmount());
                    products.add(productListInRealization);
                }
                listProductTableView.refresh();
                updateSum();
            }

        }
    }

    @FXML
    void onRemoveRealizeButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizeMenu.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onViewRealizeButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizationView.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onCustomerComboBoxAction(ActionEvent event) {
        products.clear();
        if((customerComboBox.getValue() != null) && !customerComboBox.getValue().isEmpty()){
            Customer customer = customerService.getByInn(new BigInteger(customerComboBox.getValue()));
            currentCustomer = customer;
            if(customer != null) {
                orders = orderService.getAllByCustomerInn(new BigInteger(customerComboBox.getValue()));
                ObservableList<String> ordersData = FXCollections.observableArrayList(orders
                        .stream().map(order -> order.getOrderNumber().toString()).collect(Collectors.toList()));
                numberOrderComboBox.setItems(ordersData);
                numberOrderComboBox.setDisable(false);
                organizationNameLabel.setText(customer.getOrganizationName());
                return;
            }
        }
        numberOrderComboBox.setDisable(true);
        organizationNameLabel.setText("");

    }

    public void deleteProduct(ProductNomenclature productNomenclature){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getIdProduct().getId().equals(productNomenclature.getId())){
                products.remove(i);
                break;
            }
        }
    }

    public void updateSum(){
        BigDecimal total = new BigDecimal("0");
        for(int i = 0; i < products.size(); i++){
            String stringValue = fullPriceTableColumn.getCellObservableValue(i).getValue();
            if(stringValue != null && !stringValue.isEmpty()) {
                BigDecimal value = new BigDecimal(stringValue);
                total = total.add(value);
            }
        }
        digitLabel.setText(total.toString());
    }


}
