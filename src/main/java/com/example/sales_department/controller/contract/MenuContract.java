package com.example.sales_department.controller.contract;

import com.example.sales_department.controller.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/contract/menu_contract.fxml")
public class MenuContract {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button addContractButton;

    @FXML
    private Button contractListButton;

    @FXML
    private Button contractorsButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button findContractButton;

    @FXML
    private Button specificationsButton;

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
    void onCotractorsButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(Contractors.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onExitButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(HelloController.class);
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

    @FXML
    void onSpecificationsButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(SpecificationView.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
