package com.example.sales_department.controller.order;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Order;
import com.example.sales_department.service.ContractService;
import com.example.sales_department.service.CustomerService;
import com.example.sales_department.service.OrderService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/order/order_search.fxml")
public class OrderSearch {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderEdit orderEdit;
    @Autowired
    CustomerService customerService;

    @FXML
    private Button addOrderButton;

    @FXML
    private ComboBox<String> contractComboBox;

    @FXML
    private TableColumn<Order, String> contractNumberTableColumn;

    @FXML
    private ComboBox<String> contractorComboBox;

    @FXML
    private Label contractorLabel;

    @FXML
    private TableColumn<Order, String> contractorTableColumn;

    @FXML
    private TableColumn<Order, String> dateReceiveTableColumn;

    @FXML
    private Button editOrderButton;

    @FXML
    private DatePicker endShipmentDatePicker;

    @FXML
    private Label endShipmentLabel;

    @FXML
    private TableColumn<Order, String> endShipmentTableColumn;

    @FXML
    private Button exitButton;

    @FXML
    private Label filterLabel;

    @FXML
    private Button findButton;

    @FXML
    private Label findOrderLabel;

    @FXML
    private TableColumn<Order, String> numberOrderTableColumn;

    @FXML
    private Label orderLabel;

    @FXML
    private TableView<Order> orderTableView;

    @FXML
    private ComboBox<String> specificationComboBox;

    @FXML
    private Label specificationLabel;

    @FXML
    private DatePicker startShipmentDatePicker;

    @FXML
    private Label startShipmentLabel;

    @FXML
    private TableColumn<Order, String> startShipmentTableColumn;

    @FXML
    private Button viewOrderButton;

    @FXML
    public void initialize() {

        editOrderButton.setDisable(true);
        orderTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editOrderButton.setDisable(false);
            }
            else {
                editOrderButton.setDisable(true);
            }
        });

        ObservableList<Order> list = FXCollections.observableArrayList(orderService.getAll());

        numberOrderTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getOrderNumber().toString()));
        dateReceiveTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getReceiptDay().toString()));
        contractorTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdSpecification().getIdContract().getIdCustomer().getOrganizationName()));
        contractNumberTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdSpecification().getIdContract().getContractNumber().toString()));
        startShipmentTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getDeliveryBegin().toString()));
        endShipmentTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getDeliveryFinish().toString()));

        orderTableView.setItems(list);

        contractorComboBox.setEditable(true);
        ObservableList<String> data = FXCollections.observableArrayList(
                customerService.getAll().stream().map(customer -> customer.getInn().toString()).collect(Collectors.toList()));
        FilteredList<String> filteredItems = new FilteredList<String>(data, p -> true);
        contractorComboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = contractorComboBox.getEditor();
            final String selected = contractorComboBox.getSelectionModel().getSelectedItem();
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
        contractorComboBox.setItems(filteredItems);
    }

    @FXML
    void onAddOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderAdd.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onContractComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onContractorComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onEditOrderButtonClick(ActionEvent event) {
        orderEdit.setOrder(orderTableView.getSelectionModel().getSelectedItem());
        Parent root = fxWeaver.loadView(OrderEdit.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

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
    void onFindButtonClick(ActionEvent event) {
//        Parent root = fxWeaver.loadView(OrderSearch.class);
//        Scene scene = new Scene(root);
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();

        BigInteger inn = null;
        if ((contractorComboBox.getValue() != null) && (!contractorComboBox.getValue().isEmpty())){
            inn = new BigInteger(contractorComboBox.getValue());
        }

        LocalDate startShipment = null;
        if (startShipmentDatePicker.getValue() != null){
            startShipment = startShipmentDatePicker.getValue();
            startShipmentDatePicker.setValue(null);
        }

        LocalDate endShipment = null;
        if (endShipmentDatePicker.getValue() != null){
            endShipment = endShipmentDatePicker.getValue();
            endShipmentDatePicker.setValue(null);
        }

        Long specification = null;
        if ((specificationComboBox.getValue() != null) && (!specificationComboBox.getValue().isEmpty())){
            specification = Long.parseLong(specificationComboBox.getValue());
        }
        Long contractNumber = null;
        if ((contractComboBox.getValue() != null) && (!contractComboBox.getValue().isEmpty())){
            contractNumber = Long.parseLong(contractComboBox.getValue());
        }

        List<Order> orders = orderService.findByAll(inn, specification, startShipment, endShipment, contractNumber);

        ObservableList<Order> tableItems = FXCollections.observableArrayList(orders);
        orderTableView.setItems(tableItems);
        orderTableView.refresh();

    }

    @FXML
    void onSpecificationComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onViewOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderView.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
