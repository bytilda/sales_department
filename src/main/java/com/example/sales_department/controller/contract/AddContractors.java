package com.example.sales_department.controller.contract;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private ComboBox<Okpo> okpoComboBox;

    @FXML
    private ComboBox<Fia> adressComboBox;

    @FXML
    private TextField unnTextField;

    @FXML
    public void initialize() {
        adressComboBox.setEditable(true);

        ObservableList<Fia> data = FXCollections.observableArrayList(
                fiasService.getAll());
        adressComboBox.setItems(data);

        okpoComboBox.setEditable(true);

        ObservableList<Okpo> okpoData = FXCollections.observableArrayList(
                okpoService.getAll());
        okpoComboBox.setItems(okpoData);
    }

    @FXML
    void onAddContractorsButtonClick(ActionEvent event) {

        Customer customer = new Customer();
        customer.setOrganizationName(nameTextField.getText());
        customer.setOkpo(okpoComboBox.getSelectionModel().getSelectedItem());
        customer.setLegalAddress(adressComboBox.getSelectionModel().getSelectedItem());
        customer.setInn(Long.parseLong(unnTextField.getText()));
        customer.setKpp(Long.parseLong(kppTextField.getText()));
        customer.setCorrespondentAccount(Long.parseLong(correspondentTextField.getText()));
        customer.setCheckingAccount(Long.parseLong(estimatedTextField.getText()));
        customerService.add(customer);

        Parent root = fxWeaver.loadView(Contractors.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
