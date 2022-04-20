package com.example.sales_department.controller.contract;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Fia;
import com.example.sales_department.entity.Okpo;
import com.example.sales_department.service.ContractService;
import com.example.sales_department.service.CustomerService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/contract/add_contract.fxml")
public class AddContract {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    ContractService contractService;
    @Autowired
    CustomerService customerService;

    @FXML
    private AnchorPane addContractAnchorPane;

    @FXML
    private Button addContractButton;

    @FXML
    private Button addSpecificationButton;

    @FXML
    private Button cancelButtonClick;

    @FXML
    private TextField cityContractTextField;

    @FXML
    private TextField consigneeAddress;

    @FXML
    private TextArea contractContentTextArea;

    @FXML
    private Button contractListButton;

    @FXML
    private TextField contractNumberTextField;

    @FXML
    private ComboBox<String> contractorsComboBox;

    @FXML
    private Button createContractButton;

    @FXML
    private DatePicker dateClosingDatePicker;

    @FXML
    private DatePicker dateForceDatePicker;

    @FXML
    private Button exitButton;

    @FXML
    private Button findContractButton;

    @FXML
    private TableColumn<?, ?> numberAppTableColumn;

    @FXML
    private TableView<?> numberAppTableView;

    @FXML
    private DatePicker validDateDatePicker;

    @FXML
    public void initialize(){
        contractorsComboBox.setEditable(true);
        ObservableList<String> data = FXCollections.observableArrayList(
                customerService.getAll().stream().map(customer -> customer.getInn().toString()).collect(Collectors.toList()));
        FilteredList<String> filteredItems = new FilteredList<String>(data, p -> true);
        contractorsComboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = contractorsComboBox.getEditor();
            final String selected = contractorsComboBox.getSelectionModel().getSelectedItem();
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
        contractorsComboBox.setItems(filteredItems);

        contractNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                contractNumberTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        cityContractTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\D*")) {
                cityContractTextField.setText(newValue.replaceAll("[^\\D]", ""));
            }
        });


    }

    @FXML
    void onAddContractButtonClick(ActionEvent event) {
        boolean isError = false;
        String errorText = "";
        Contract contract = new Contract();

        if(contractNumberTextField.getText().isEmpty()) {
            isError = true;
            errorText += "Поле номера договора не должно быть пустым. \n";
        }
        else
            contract.setContractNumber(Long.parseLong(contractNumberTextField.getText()));


        if(contractorsComboBox.getValue() == null){
            isError = true;
            errorText += "Поле Контрагента не должно быть пустым. \n";
        }
        else{
            Customer customer = customerService.getByInn(new BigInteger(contractorsComboBox.getValue()));
            if(customer == null){
                isError = true;
                errorText += "Указанного Контрагента нет в справочнике. \n";
            }
            contract.setIdCustomer(customer);
        }

        if(cityContractTextField.getText().isEmpty()){
            isError = true;
            errorText += "Поле города не должно быть пустым. \n";
        }
        else{
            contract.setCity(cityContractTextField.getText());
        }

        if(dateClosingDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле Даты заключения не должно быть пустым. \n";
        }
        else{
            contract.setConclusionDate(dateClosingDatePicker.getValue());
        }

        if(dateForceDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле Даты вступления договора в силу не должно быть пустым. \n";
        }
        else
            contract.setValidFrom(dateForceDatePicker.getValue());

        if(validDateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле Действителен до не должно быть пустым. \n";
        }
        else contract.setValidUntil(validDateDatePicker.getValue());

        if(contractContentTextArea.getText().isEmpty()){
            isError = true;
            errorText += "Поле предмета договора не должно быть пустым. \n";
        }
        else{
            contract.setContractSubject(contractContentTextArea.getText());
        }

        if(!isError) {
            contractService.add(contract);
            Parent root = fxWeaver.loadView(ViewContract.class);
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

    @FXML
    void onCancelButtonClick(ActionEvent event) {

    }

    @FXML
    void onAddSpecificationButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(AddSpecification.class);
        Scene scene = new Scene(root);
        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
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
    void onContractorsComboBoxClick(ActionEvent event) {

    }

    @FXML
    void onCreateContractButtonClick(ActionEvent event) {

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
    void onFindContractButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ContractManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
