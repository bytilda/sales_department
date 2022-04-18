package com.example.sales_department.controller.order;

import com.example.sales_department.controller.HelloController;
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

    @FXML
    private Button addOrderButton;

    @FXML
    private TableColumn<?, ?> contractorTableColumn;

    @FXML
    private TableColumn<?, ?> dateReceiveTableColumn;

    @FXML
    private TableColumn<?, ?> endShipmentTableColumn;

    @FXML
    private Button exitButton;

    @FXML
    private Button findOrderButton;

    @FXML
    private TableColumn<?, ?> numberOrderTableColumn;

    @FXML
    private TableColumn<?, ?> startShipmentTableColumn;

    @FXML
    private Label viewOrderLabel;

    @FXML
    private TableView<?> viewOrderTableView;

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
