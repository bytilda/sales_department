package com.example.sales_department.controller.contract;

import com.example.sales_department.controller.Utils;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Fia;
import com.example.sales_department.entity.Okpo;
import com.example.sales_department.service.CustomerService;
import com.example.sales_department.service.FiasService;
import com.example.sales_department.service.OkpoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/contract/add_contractors.fxml")
public class AddContractors {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    CustomerService customerService;
    @Autowired
    FiasService fiasService;
    @Autowired
    OkpoService okpoService;

    @FXML
    private Button addContractorsButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField correspondentTextField;

    @FXML
    private TextField estimatedTextField;

    @FXML
    private TextField kppTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<String> okpoComboBox;

    @FXML
    private ComboBox<String> adressComboBox;

    @FXML
    private TextField unnTextField;

    @FXML
    public void initialize() {
        adressComboBox.setEditable(true);
        ObservableList<String> data = FXCollections.observableArrayList(
                fiasService.getAll().stream().map(Object::toString).collect(Collectors.toList()));
        Utils.addFilter(data, adressComboBox);

        okpoComboBox.setEditable(true);

        ObservableList<String> okpoData = FXCollections.observableArrayList(
                okpoService.getAll().stream().map(Object::toString).collect(Collectors.toList()));
        okpoComboBox.setItems(okpoData);

        unnTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                unnTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        kppTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                kppTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        correspondentTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                correspondentTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        estimatedTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                estimatedTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    void onAddContractorsButtonClick(ActionEvent event) {
        boolean isError = false;
        String errorText = "";
        Customer customer = new Customer();

        if(nameTextField.getText().isEmpty()){
            isError = true;
            errorText += "???????? ???????????????? ?????????????????????? ???? ???????????? ???????? ????????????. \n";
        }
        customer.setOrganizationName(nameTextField.getText());

        if(okpoComboBox.getValue() == null){
            isError = true;
            errorText += "???????? ???????? ???? ???????????? ???????? ????????????. \n";
        }
        else{
            Okpo okpo = okpoService.getById(Long.parseLong(okpoComboBox.getValue()));
            if(okpo == null){
                isError = true;
                errorText += "???????????????????? ???????? ?????? ?? ??????????????????????. \n";
            }
            customer.setOkpo(okpo);
        }

        if(adressComboBox.getValue() == null){
            isError = true;
            errorText += "???????? ???????????????????????? ???????????? ?????????????????????? ???? ???????????? ???????? ????????????. \n";
        }
        else{
            Fia address = fiasService.getById(adressComboBox.getValue());
            if(address == null){
                isError = true;
                errorText += "???????????????????? ???????????? ?????? ?? ???????????????????????????? ????????. \n";
            }
            customer.setLegalAddress(address);
        }

        if(unnTextField.getText().isEmpty()){
            isError = true;
            errorText += "???????? ?????? ???? ???????????? ???????? ????????????. \n";
        }
        else{
            customer.setInn(new BigInteger(unnTextField.getText()));
        }

        if(kppTextField.getText().isEmpty()){
            isError = true;
            errorText += "???????? ?????? ???? ???????????? ???????? ????????????. \n";
        }
        else{
            customer.setKpp(new BigInteger(kppTextField.getText()));
        }

        if(correspondentTextField.getText().isEmpty()){
            isError = true;
            errorText += "???????? ???????????????????????????????????? ?????????? ???? ???????????? ???????? ????????????. \n";
        }
        else
            customer.setCorrespondentAccount(new BigInteger(correspondentTextField.getText()));

        if(estimatedTextField.getText().isEmpty()){
            isError = true;
            errorText += "???????? ???????????????????? ?????????? ???? ???????????? ???????? ????????????. \n";
        }
        else customer.setCheckingAccount(new BigInteger(estimatedTextField.getText()));

        if(!isError) {
            customerService.add(customer);
            Parent root = fxWeaver.loadView(Contractors.class);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("????????????");
            alert.setHeaderText("?????????????????? ?????? ????????");
            alert.setContentText(errorText);
            alert.showAndWait();
        }
    }

    @FXML
    void onAdressComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(Contractors.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
