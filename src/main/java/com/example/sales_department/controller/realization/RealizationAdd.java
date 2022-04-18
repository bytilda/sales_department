package com.example.sales_department.controller.realization;

import com.example.sales_department.controller.contract.MenuContract;
import com.example.sales_department.entity.Fia;
import com.example.sales_department.service.FiasService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/realization/realization_add.fxml")
public class RealizationAdd {
    @Autowired
    FxWeaver fxWeaver;

    @Autowired
    FiasService fiasService;

    @FXML
    private Label DateLabel;

    @FXML
    private Button ExitButton;

    @FXML
    private Button addRealizeButton;

    @FXML
    private Label addRealizeLabel;

    @FXML
    private Label adressLabel;

    @FXML
    private ComboBox<String> adressLabelComboBox;

    @FXML
    private TableColumn<?, ?> amountTableColumn;

    @FXML
    private TableColumn<?, ?> codeProductTableColumn;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private DatePicker dateReceiveDatePicker;

    @FXML
    private Label dateReceiveLabel;

    @FXML
    private Label digitLabel;

    @FXML
    private Button findRealizeButton;

    @FXML
    private TableColumn<?, ?> fullPriceTableColumn;

    @FXML
    private Label listProductLabel;

    @FXML
    private TableView<?> listProductTableView;

    @FXML
    private TableColumn<?, ?> nameProductTableColumn;

    @FXML
    private ComboBox<?> numberOrderComboBox;

    @FXML
    private Label numberOrderLabel;

    @FXML
    private Label numberUPDLabel;

    @FXML
    private TextField numberUPDTextField;

    @FXML
    private TableColumn<?, ?> priceTableColumn;

    @FXML
    private Button removeRealizeButton;

    @FXML
    private Label sumLabel;

    @FXML
    private Label timeShipmentLabel;

    @FXML
    private TextField timeShipmentTextField;

    @FXML
    private Button viewRealizeButton;

    @FXML
    public void initialize() {
        ObservableList<String> data = FXCollections.observableArrayList(
                fiasService.getAll().stream().map(Fia::getId).collect(Collectors.toList()));
        adressLabelComboBox.setItems(data);
    }


    @FXML
    void onAddRealizeButtonClick(ActionEvent event) {

    }

    @FXML
    void onAdressComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onExitButtonClick(ActionEvent event) {

    }

    @FXML
    void onFindRealizeButtonClick(ActionEvent event) {

    }

    @FXML
    void onNumberOrderComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onRemoveRealizeButtonClick(ActionEvent event) {

    }

    @FXML
    void onViewRealizeButtonClick(ActionEvent event) {

    }

}
