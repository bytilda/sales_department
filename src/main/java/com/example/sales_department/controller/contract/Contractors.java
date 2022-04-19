package com.example.sales_department.controller.contract;

import com.example.sales_department.entity.Customer;
import com.example.sales_department.service.CustomerService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/contract/Contractors.fxml")
public class Contractors {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    CustomerService customerService;

    @FXML
    private Button addContractorButton;

    @FXML
    private TableView<Customer> contractorsTableView;

    @FXML
    private TableColumn<Customer, Integer> correspondentTableColumn;

    @FXML
    private Button editContractorButton;

    @FXML
    private TableColumn<Customer, Integer> estimatedTableColumn;

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<Customer, Integer> kppTableColumn;

    @FXML
    private TableColumn<Customer, String> labelAddressTableColumn;

    @FXML
    private TableColumn<Customer, String> nameTableColumn;

    @FXML
    private TableColumn<Customer, Long> unnTableColumn;



    @FXML
    public void initialize() {
        ObservableList<Customer> list = FXCollections.observableArrayList(customerService.getAll());

        unnTableColumn.setCellValueFactory(customer -> new SimpleLongProperty(customer.getValue().getInn()));
        kppTableColumn.setCellValueFactory(new PropertyValueFactory<Example, Integer>("kppTableColumn") );
        estimatedTableColumn.setCellValueFactory(new PropertyValueFactory<Example, Integer>("estimatedTableColumn") );
        correspondentTableColumn.setCellValueFactory(new PropertyValueFactory<Example, Integer>("correspondentTableColumn") );
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Example, String>("nameTableColumn") );
        labelAddressTableColumn.setCellValueFactory(new PropertyValueFactory<Example, String>("labelAddressTableColumn") );

        contractorsTableView.setItems(list);
    }

    @FXML
    void onAddContractorsButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(AddContractors.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onEditContractorButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(EditContractors.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onExitButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(MenuContract.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
