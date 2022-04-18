package com.example.sales_department.controller.contract;

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

@Component
@FxmlView("/com/example/sales_department/contract/view_contract.fxml")
public class ViewContract {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private TableColumn<?, ?> ContractorTableColumn;

    @FXML
    private Button addContractButton;

    @FXML
    private TableColumn<?, ?> adressTableColumn;

    @FXML
    private TableColumn<?, ?> cityTableColumn;

    @FXML
    private TableColumn<?, ?> dataConclusionTableColumn;

    @FXML
    private TableColumn<?, ?> dataEndTableColumn;

    @FXML
    private TableColumn<?, ?> dataStartTableColumn;

    @FXML
    private Button editContractButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<?, ?> numberSpecificationTableColumn;

    @FXML
    private TableColumn<?, ?> statusTableColumn;

    @FXML
    private TableView<?> viewContractTableView;

    @FXML
    private Text viewContractText;

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
