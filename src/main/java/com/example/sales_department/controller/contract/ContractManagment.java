package com.example.sales_department.controller.contract;

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

@Component
@FxmlView("/com/example/sales_department/contract/contract_managment.fxml")
public class ContractManagment {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button addContractButton;

    @FXML
    private TableColumn<?, ?> closingCityTableColumn;

    @FXML
    private TextField closingCityTextField;

    @FXML
    private DatePicker closingDateDatePicker;

    @FXML
    private TableColumn<?, ?> closingDateTableColumn;

    @FXML
    private Button contractListButton;

    @FXML
    private TableColumn<?, ?> contractNumberTableColumn;

    @FXML
    private TextField contractNumberTextField;

    @FXML
    private ComboBox<?> contractorComboBox;

    @FXML
    private TableColumn<?, ?> contractorTableColumn;

    @FXML
    private Button editContractButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableView<?> findContractTableView;

    @FXML
    private DatePicker forceDateDatePicker;

    @FXML
    private TableColumn<?, ?> forceDateTableColumn;

    @FXML
    private Button searchContractButton;

    @FXML
    private DatePicker validDateDatePicker;

    @FXML
    private TableColumn<?, ?> validDateTableColumn;

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

    }

}
