package com.example.sales_department.controller.contract;

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

    @FXML
    private Button addContractorButton;

    @FXML
    private TableView<?> contractorsTableView;

    @FXML
    private TableColumn<?, ?> correspondentTableColumn;

    @FXML
    private Button editContractorButton;

    @FXML
    private TableColumn<?, ?> estimatedTableColumn;

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<?, ?> kppTableColumn;

    @FXML
    private TableColumn<?, ?> labelAddressTableColumn;

    @FXML
    private TableColumn<?, ?> nameTableColumn;

    @FXML
    private TableColumn<?, ?> unnTableColumn;

    @FXML
    public void initialize() {

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
