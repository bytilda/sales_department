package com.example.sales_department.controller.realization;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.entity.Order;
import com.example.sales_department.entity.Realization;
import com.example.sales_department.service.OrderService;
import com.example.sales_department.service.RealizationService;
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
@FxmlView("/com/example/sales_department/realization/realization_view.fxml")
public class RealizationView {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    RealizationService realizationService;

    @FXML
    private Label viewRealizeText;

    @FXML
    private TableView<Realization> viewRealizeTableView;

    @FXML
    private TableColumn<Realization, String> numberUPDTableColumn;

    @FXML
    private TableColumn<Realization, String> dateTableColumn;

    @FXML
    private TableColumn<Realization, String> contractorTableColumn;

    @FXML
    private TableColumn<Realization, String> statusTableColumn;

    @FXML
    private TableColumn<Realization, String> adressTableColumn;

    @FXML
    private TableColumn<Realization, String> timeShipmentTableColumn;

    @FXML
    private TableColumn<Realization, String> dateReceiveTableColumn;

    @FXML
    private TableColumn<Realization, String> priceTableColumn;

    @FXML
    private Button findRealizeButton;

    @FXML
    private Button viewRealizeButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button addRealizationButton;

    @FXML
    public void initialize() {

        ObservableList<Realization> list = FXCollections.observableArrayList(realizationService
                .getAll());

//        priceTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue()..toString()));
        dateReceiveTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getReceivingDate().toString()));
        timeShipmentTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getFactedTimeOfShipment().toString()));
        adressTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getConsigneeAddress().toString()));
        statusTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getPaymentStatus().toString()));
        dateTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getDate().toString()));
        contractorTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdOrder().getIdSpecification().getIdContract().getIdCustomer().getOrganizationName()));
        numberUPDTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getUpdNumber().toString()));

        viewRealizeTableView.setItems(list);
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
    void onViewRealizeButtonClick(ActionEvent event) {

    }

    @FXML
    void onAddRealizationButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizationAdd.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
