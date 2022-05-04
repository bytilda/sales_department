package com.example.sales_department.controller.realization;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/realization/realization_edit.fxml")
public class RealizationEdit {
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

    @Setter
    Realization realization;

    Customer currentCustomer = null;
    List<Order> orders;
    Order currentOrder = null;

    ObservableList<ProductListInRealization> products = FXCollections.observableArrayList();

    @FXML
    private Label editRealizeText;

    @FXML
    private Button saveButton;

    @FXML
    private Button removeButton;

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
    private Label dateReceiveLabel;

    @FXML
    private DatePicker dateReceiveDatePicker;

    @FXML
    private DatePicker shippingDateDatePicker;

    @FXML
    private Label numberUPDLabel1;

    @FXML
    private ComboBox<String> customerChoiceBox;

    @FXML
    private Button addProductInOrderButton;

    @FXML
    private Label organizationNameLabel;

    @FXML
    public void initialize(){
        products = FXCollections.observableArrayList(productListInRealizationService.getAllByRealization(realization));

        adressLabelComboBox.setEditable(true);
        ObservableList<String> addrData = FXCollections.observableArrayList(
                fiasService.getAll().stream().map(Object::toString).collect(Collectors.toList()));
        Utils.addFilter(addrData, adressLabelComboBox);

        ObservableList<String> data = FXCollections.observableArrayList(
                customerService.getAll().stream().map(customer -> customer.getInn().toString()).collect(Collectors.toList()));
        Utils.addFilter(data, customerChoiceBox);

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

        numberUPDTextField.setText(realization.getUpdNumber().toString());
        currentCustomer = realization.getIdOrder().getIdSpecification().getIdContract().getIdCustomer();
        customerChoiceBox.setValue(currentCustomer.getInn().toString());
        organizationNameLabel.setText(currentCustomer.getOrganizationName());
        dateDatePicker.setValue(realization.getDate());
        dateReceiveDatePicker.setValue(realization.getReceivingDate());
        shippingDateDatePicker.setValue(realization.getFactedTimeOfShipment());
        numberOrderComboBox.setValue(realization.getIdOrder().getOrderNumber().toString());
        adressLabelComboBox.setValue(realization.getConsigneeAddress().toString());
        currentOrder = realization.getIdOrder();

        updateSum();
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
    void onAdressComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onNumberOrderComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onRemoveButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizeManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {
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
            productListInRealizationService.deleteAllByRealization(realization.getId());
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
