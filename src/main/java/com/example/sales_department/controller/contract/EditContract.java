package com.example.sales_department.controller.contract;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Fia;
import com.example.sales_department.service.ContractService;
import com.example.sales_department.service.CustomerService;
import com.example.sales_department.service.FiasService;
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
import javafx.stage.Stage;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/contract/edit_contract.fxml")
public class EditContract {
    @Autowired
    FxWeaver fxWeaver;

    @Autowired
    ContractService contractService;
    @Autowired
    CustomerService customerService;
    @Autowired
    FiasService fiasService;

    @Setter
    Contract contract;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker closindDateDatePicker;

    @FXML
    private TextField closingCityTextField;

    @FXML
    private Button confirmEditContractButton;

    @FXML
    private ComboBox<String> consigneerAddressComboBox;

    @FXML
    private TextArea contractContentTextArea;

    @FXML
    private TextField contractNumberTextField;

    @FXML
    private ComboBox<String> contractorComboBox;

    @FXML
    private DatePicker forceDateDatePicker;

    @FXML
    private DatePicker validDateDatePicker;

    @FXML
    public void initialize(){
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


        contractNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                contractNumberTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        closingCityTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\D*")) {
                closingCityTextField.setText(newValue.replaceAll("[^\\D]", ""));
            }
        });

        consigneerAddressComboBox.setEditable(true);
        ObservableList<String> addressData = FXCollections.observableArrayList(
                fiasService.getAll().stream().map(Object::toString).collect(Collectors.toList()));
        FilteredList<String> addressFilteredItems = new FilteredList<String>(addressData, p -> true);
        consigneerAddressComboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = consigneerAddressComboBox.getEditor();
            final String selected = consigneerAddressComboBox.getSelectionModel().getSelectedItem();
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
        consigneerAddressComboBox.setItems(addressFilteredItems);

        contractorComboBox.setValue(contract.getIdCustomer().getInn().toString());
        consigneerAddressComboBox.setValue(contract.getIdCustomer().getLegalAddress().getId());
        contractNumberTextField.setText(contract.getContractNumber().toString());
        closingCityTextField.setText(contract.getCity());
        contractContentTextArea.setText(contract.getContractSubject());
        closindDateDatePicker.setValue(contract.getConclusionDate());
        forceDateDatePicker.setValue(contract.getValidFrom());
        validDateDatePicker.setValue(contract.getValidUntil());

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ContractManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onConfirmEditContractButtonClick(ActionEvent event) {
//        Parent root = fxWeaver.loadView(ContractManagment.class);
//        Scene scene = new Scene(root);
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();

        boolean isError = false;
        String errorText = "";

        if(contractNumberTextField.getText().isEmpty()) {
            isError = true;
            errorText += "Поле номера договора не должно быть пустым. \n";
        }
        else
            contract.setContractNumber(Long.parseLong(contractNumberTextField.getText()));


        if(contractorComboBox.getValue() == null){
            isError = true;
            errorText += "Поле Контрагента не должно быть пустым. \n";
        }
        else{
            Customer customer = customerService.getByInn(new BigInteger(contractorComboBox.getValue()));
            if(customer == null){
                isError = true;
                errorText += "Указанного Контрагента нет в справочнике. \n";
            }
            contract.setIdCustomer(customer);
        }

        if(closingCityTextField.getText().isEmpty()){
            isError = true;
            errorText += "Поле города не должно быть пустым. \n";
        }
        else{
            contract.setCity(closingCityTextField.getText());
        }

        if(closindDateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле Даты заключения не должно быть пустым. \n";
        }
        else{
            contract.setConclusionDate(closindDateDatePicker.getValue());
        }

        if(forceDateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле Даты вступления договора в силу не должно быть пустым. \n";
        }
        else
            contract.setValidFrom(forceDateDatePicker.getValue());

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

        if(consigneerAddressComboBox.getValue() == null){
            isError = true;
            errorText += "Поле юридического адреса организации не должно быть пустым. \n";
        }
        else{
            Fia address = fiasService.getById(consigneerAddressComboBox.getValue());
            if(address == null){
                isError = true;
                errorText += "Указанного адреса нет в классификаторе ФИАС. \n";
            }
            contract.setConsigneeAddress(address);
        }

        if(!isError) {
            contractService.add(contract);
            Parent root = fxWeaver.loadView(ContractManagment.class);
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
    void onContractorComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onConsigneerAddressComboBoxAction(ActionEvent event) {

    }

}
