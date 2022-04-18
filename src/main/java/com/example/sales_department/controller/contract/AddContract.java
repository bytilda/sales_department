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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/contract/add_contract.fxml")
public class AddContract {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private AnchorPane addContractAnchorPane;

    @FXML
    private Button addContractButton;

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
    private ComboBox<?> contractorsComboBox;

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
    void onAddContractButtonClick(ActionEvent event) {

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {

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
