package com.example.sales_department.controller.contract;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/contract/add_specification.fxml")
public class AddSpecification {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button addSpecificationButton;

    @FXML
    private TextField amountTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField consigneeAddressTextField;

    @FXML
    private TextField contractNumberTextField;

    @FXML
    private DatePicker deliveryDateDatePicker;

    @FXML
    private TextField unitsTextField;

    @FXML
    void onAddSpecificationButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(Specification.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(Specification.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
