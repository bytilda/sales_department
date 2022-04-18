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
@FxmlView("/com/example/sales_department/contract/Specification.fxml")
public class Specification {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Button addSpecificationButton;

    @FXML
    private TableColumn<?, ?> adressTableColumn;

    @FXML
    private TableColumn<?, ?> amountTableColumn;

    @FXML
    private TableColumn<?, ?> dataTableColumn;

    @FXML
    private TableColumn<?, ?> numberContractTableColumn;

    @FXML
    private TableColumn<?, ?> numberTableColumn;

    @FXML
    private TableView<?> specificationTableView;

    @FXML
    private Text specificationText;

    @FXML
    private TableColumn<?, ?> unitMeasureTableColumn;

    @FXML
    void onAddSpecificationButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(AddSpecification.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
