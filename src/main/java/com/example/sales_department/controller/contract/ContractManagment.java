package com.example.sales_department.controller.contract;

import com.example.sales_department.controller.Utils;
import com.example.sales_department.entity.Contract;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
@FxmlView("/com/example/sales_department/contract/contract_managment.fxml")
public class ContractManagment {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    ContractService contractService;
    @Autowired
    CustomerService customerService;
    @Autowired
    EditContract editContract;

    @FXML
    private Button addContractButton;

    @FXML
    private TableColumn<Contract, String> adressTableColumn;

    @FXML
    private TableColumn<Contract, String> closingCityTableColumn;

    @FXML
    private TextField closingCityTextField;

    @FXML
    private DatePicker closingDateDatePicker;

    @FXML
    private TableColumn<Contract, String> closingDateTableColumn;

    @FXML
    private Button contractListButton;

    @FXML
    private TableColumn<Contract, String> contractNumberTableColumn;

    @FXML
    private TextField contractNumberTextField;

    @FXML
    private ComboBox<String> contractorComboBox;

    @FXML
    private TableColumn<Contract, String> contractorTableColumn;

    @FXML
    private Button editContractButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableView<Contract> findContractTableView;

    @FXML
    private DatePicker forceDateDatePicker;

    @FXML
    private TableColumn<Contract, String> forceDateTableColumn;

    @FXML
    private Button searchContractButton;

    @FXML
    private DatePicker validDateDatePicker;

    @FXML
    private TableColumn<Contract, String> validDateTableColumn;

    @FXML
    public void initialize() {

        editContractButton.setDisable(true);
        findContractTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editContractButton.setDisable(false);
            }
            else {
                editContractButton.setDisable(true);
            }
        });

        ObservableList<Contract> list = FXCollections.observableArrayList(contractService
                .getAll());

        validDateTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getValidUntil().toString()));
        forceDateTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getValidFrom().toString()));
        contractorTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdCustomer().getOrganizationName()));
        contractNumberTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getContractNumber().toString()));
        closingDateTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getConclusionDate().toString()));
        closingCityTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCity()));
        adressTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getConsigneeAddress().getId()));

        findContractTableView.setItems(list);

        contractorComboBox.setEditable(true);
        ObservableList<String> data = FXCollections.observableArrayList(
                customerService.getAll().stream().map(customer -> customer.getInn().toString()).collect(Collectors.toList()));
        Utils.addFilter(data, contractorComboBox);
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
    void onContractListButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ViewContract.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onContractorComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onEditContractButtonClick(ActionEvent event) {
        editContract.setContract(findContractTableView.getSelectionModel().getSelectedItem());
        Parent root = fxWeaver.loadView(EditContract.class);
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

    @FXML
    void onSearchContractButtonClick(ActionEvent event) {
        Long contractNumber = null;
        if (!contractNumberTextField.getText().isEmpty()){
            contractNumber = Long.parseLong(contractNumberTextField.getText());
        }

        String city = null;
        if ((closingCityTextField.getText() != null) && (!closingCityTextField.getText().isEmpty())){
            city = closingCityTextField.getText();
        }

        LocalDate closingDate = null;
        if (closingDateDatePicker.getValue() != null){
            closingDate = closingDateDatePicker.getValue();
            closingDateDatePicker.setValue(null);
        }

        LocalDate validFrom = null;
        if (forceDateDatePicker.getValue() != null){
            validFrom = forceDateDatePicker.getValue();
            validDateDatePicker.setValue(null);
        }

        LocalDate validUntil = null;
        if (validDateDatePicker.getValue() != null){
            validUntil = validDateDatePicker.getValue();
            validDateDatePicker.setValue(null);
        }

        BigInteger inn = null;

        if ((contractorComboBox.getValue() != null) && (!contractorComboBox.getValue().isEmpty())){
            inn = new BigInteger(contractorComboBox.getValue());
        }


        List<Contract> contracts = contractService.findByAll(contractNumber, closingDate, inn, validFrom, validUntil, city);

        ObservableList<Contract> tableItems = FXCollections.observableArrayList(contracts);
        findContractTableView.setItems(tableItems);
        findContractTableView.refresh();
    }

}
