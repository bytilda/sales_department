package com.example.sales_department.controller.realization;

import com.example.sales_department.controller.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/realization/realization_view.fxml")
public class RealizationView {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Label viewRealizeText;

    @FXML
    private TableView<?> viewRealizeTableView;

    @FXML
    private TableColumn<?, ?> numberUPDTableColumn;

    @FXML
    private TableColumn<?, ?> dateTableColumn;

    @FXML
    private TableColumn<?, ?> contractorTableColumn;

    @FXML
    private TableColumn<?, ?> statusTableColumn;

    @FXML
    private TableColumn<?, ?> adressTableColumn;

    @FXML
    private TableColumn<?, ?> timeShipmentTableColumn;

    @FXML
    private TableColumn<?, ?> dateReceiveTableColumn;

    @FXML
    private TableColumn<?, ?> priceTableColumn;

    @FXML
    private Button findRealizeButton;

    @FXML
    private Button viewRealizeButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button addRealizationButton;

    @FXML
    void onExitButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(HelloController.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onFindRealizeButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizeManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onViewRealizeButtonClick(ActionEvent event) {

    }

    @FXML
    void onAddRealizationButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizationAdd.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
