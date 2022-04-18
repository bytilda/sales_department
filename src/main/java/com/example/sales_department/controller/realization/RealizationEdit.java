package com.example.sales_department.controller.realization;

import com.example.sales_department.controller.HelloController;
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

@Component
@FxmlView("/com/example/sales_department/realization/realization_edit.fxml")
public class RealizationEdit {
    @Autowired
    FxWeaver fxWeaver;

    @FXML
    private Label editRealizeText;

    @FXML
    private Button saveButton;

    @FXML
    private Button removeButton;

    @FXML
    private Label numberUPDLabel;

    @FXML
    private Label DateLabel;

    @FXML
    private Label timeShipmentLabel;

    @FXML
    private TextField numberUPDTextField;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private Label listProductLabel;

    @FXML
    private TableView<?> listProductTableView;

    @FXML
    private TableColumn<?, ?> codeProductTableColumn;

    @FXML
    private TableColumn<?, ?> nameProductTableColumn;

    @FXML
    private TableColumn<?, ?> unitTableColumn;

    @FXML
    private TableColumn<?, ?> amountTableColumn;

    @FXML
    private TableColumn<?, ?> priceTableColumn;

    @FXML
    private TableColumn<?, ?> fullPriceTableColumn;

    @FXML
    private Label sumLabel;

    @FXML
    private Label digitLabel;

    @FXML
    private Label adressLabel;

    @FXML
    private ComboBox<?> adressLabelComboBox;

    @FXML
    private ComboBox<?> numberOrderComboBox;

    @FXML
    private Label numberOrderLabel;

    @FXML
    private Label dateReceiveLabel;

    @FXML
    private DatePicker dateReceiveDatePicker;

    @FXML
    private DatePicker shippingDateDatePicker;

    @FXML
    private Label numberUPDLabel1;

    @FXML
    private ComboBox<?> customerChoiceBox;

    @FXML
    private Button addProductInOrderButton;

    @FXML
    void onAddProductInOrderButtonClick(ActionEvent event) {

    }

    @FXML
    void onAdressComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onNumberOrderComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onRemoveButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizeManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {

    }

}
