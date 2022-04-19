package com.example.sales_department.controller.contract;

import com.example.sales_department.entity.Customer;
import com.example.sales_department.service.CustomerService;
import javafx.beans.property.*;
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
    private TableColumn<Customer, Long> correspondentTableColumn;

    @FXML
    private Button editContractorButton;

    @FXML
    private TableColumn<Customer, Long> estimatedTableColumn;

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<Customer, Long> kppTableColumn;

    @FXML
    private TableColumn<Customer, String> labelAddressTableColumn;

    @FXML
    private TableColumn<Customer, String> nameTableColumn;

    @FXML
    private TableColumn<Customer, Long> unnTableColumn;

    @FXML
    public void initialize() {
        ObservableList<Customer> list = FXCollections.observableArrayList(customerService
                .getAll());

        unnTableColumn.setCellValueFactory(cd -> new SimpleLongProperty(cd.getValue().getInn()).asObject());
        kppTableColumn.setCellValueFactory(cd -> new SimpleLongProperty(cd.getValue().getKpp()).asObject());
        estimatedTableColumn.setCellValueFactory(cd -> new SimpleLongProperty(cd.getValue().getInn()).asObject());
        correspondentTableColumn.setCellValueFactory(cd -> new SimpleLongProperty(cd.getValue().getCorrespondentAccount()).asObject());
        nameTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getOrganizationName()));
        labelAddressTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getLegalAddress().getId()));

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


/*
@Getter
@Setter
class CustomerModel{
    Customer customer;
    LongProperty inn;
    LongProperty kpp;
    StringProperty organizationName;
    StringProperty address;
    LongProperty correspondent;
    LongProperty estimated;
    LongProperty okpo;

    CustomerModel(Customer customer){
        this.customer = customer;
        this.inn = new SimpleLongProperty(customer.getInn());
        this.kpp = new SimpleLongProperty(customer.getKpp());
        this.okpo = new SimpleLongProperty(customer.getOkpo().getId());
        this.organizationName = new SimpleStringProperty(customer.getOrganizationName());
        this.estimated = new SimpleLongProperty(customer.getCheckingAccount());
        this.correspondent = new SimpleLongProperty(customer.getCorrespondentAccount());
        this.address = new SimpleStringProperty(customer.getLegalAddress().getId());
    }
}
*/