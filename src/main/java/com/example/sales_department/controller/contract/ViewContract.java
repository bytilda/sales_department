package com.example.sales_department.controller.contract;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.service.ContractService;
import com.example.sales_department.service.CustomerService;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@FxmlView("/com/example/sales_department/contract/view_contract.fxml")
public class ViewContract {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    ContractService contractService;


    @FXML
    private TableColumn<Contract, String> ContractorTableColumn;

    @FXML
    private Button addContractButton;

    @FXML
    private TableColumn<Contract, String> adressTableColumn;

    @FXML
    private TableColumn<Contract, String> cityTableColumn;

    @FXML
    private TableColumn<Contract, String> dataConclusionTableColumn;

    @FXML
    private TableColumn<Contract, String> dataEndTableColumn;

    @FXML
    private TableColumn<Contract, String> dataStartTableColumn;

    @FXML
    private Button editContractButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<Contract, String> numberContractTableColumn;

    @FXML
    private TableColumn<Contract, String> statusTableColumn;

    @FXML
    private TableView<Contract> viewContractTableView;

    @FXML
    private Text viewContractText;

    @FXML
    public void initialize() {

        ObservableList<Contract> list = FXCollections.observableArrayList(contractService
                .getAll());

        statusTableColumn.setCellValueFactory(cd -> {
            LocalDate now = LocalDate.now();
            if(cd.getValue().getValidUntil().isAfter(now))
                return new SimpleStringProperty("Действителен");
            else
                return new SimpleStringProperty("Не действителен") ;

        });
        numberContractTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getContractNumber().toString()));
        dataStartTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getValidFrom().toString()));
        dataEndTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getValidUntil().toString()));
        dataConclusionTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getConclusionDate().toString()));
        cityTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCity()));
        adressTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getConsigneeAddress().getId()));
        ContractorTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdCustomer().getOrganizationName()));

        viewContractTableView.setItems(list);
    }

    @FXML
    void onAddContractButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(AddContract.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onEditContractButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ContractManagment.class);
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
