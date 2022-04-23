package com.example.sales_department.controller.order;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Order;
import com.example.sales_department.service.OrderService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/order/order_view.fxml")
public class OrderView {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    OrderService orderService;

    @FXML
    private Button addOrderButton;

    @FXML
    private TableColumn<Order, String> contractorTableColumn;

    @FXML
    private TableColumn<Order, String> dateReceiveTableColumn;

    @FXML
    private TableColumn<Order, String> endShipmentTableColumn;

    @FXML
    private Button exitButton;

    @FXML
    private Button findOrderButton;

    @FXML
    private TableColumn<Order, String> numberOrderTableColumn;

    @FXML
    private TableColumn<Order, String> startShipmentTableColumn;

    @FXML
    private Label viewOrderLabel;

    @FXML
    private TableView<Order> viewOrderTableView;

    @FXML
    public void initialize() {

        ObservableList<Order> list = FXCollections.observableArrayList(orderService
                .getAll());

        startShipmentTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getDeliveryBegin().toString()));
        numberOrderTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getOrderNumber().toString()));
        endShipmentTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getDeliveryFinish().toString()));
        dateReceiveTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getReceiptDay().toString()));
        contractorTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdSpecification().getIdContract().getIdCustomer().getOrganizationName()));

        viewOrderTableView.setItems(list);
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
    void onExitButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(HelloController.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onFindOderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderSearch.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
