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
@FxmlView("/com/example/sales_department/order/order_search.fxml")
public class OrderSearch {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button addOrderButton;

    @FXML
    private ComboBox<?> contractorComboBox;

    @FXML
    private Label contractorLabel;

    @FXML
    private TableColumn<?, ?> contractorTableColumn;

    @FXML
    private TableColumn<?, ?> dateReceiveTableColumn;

    @FXML
    private Button editOrderButton;

    @FXML
    private DatePicker endShipmentDatePicker;

    @FXML
    private Label endShipmentLabel;

    @FXML
    private TableColumn<?, ?> endShipmentTableColumn;

    @FXML
    private Button exitButton;

    @FXML
    private Label filterLabel;

    @FXML
    private Button findButton;

    @FXML
    private Label findOrderLabel;

    @FXML
    private TableColumn<?, ?> numberOrderTableColumn;

    @FXML
    private Label orderLabel;

    @FXML
    private TableView<?> orderTableView;

    @FXML
    private ComboBox<?> specificationComboBox;

    @FXML
    private Label specificationLabel;

    @FXML
    private DatePicker startShipmentDatePicker;

    @FXML
    private Label startShipmentLabel;

    @FXML
    private TableColumn<?, ?> startShipmentTableColumn;

    @FXML
    private Button viewOrderButton;

    @FXML
    void onAddOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderAdd.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onContractorComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onEditOrderButtonClick(ActionEvent event) {
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
        Parent root = fxWeaver.loadView(OrderSearch.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

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
