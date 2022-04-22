package com.example.sales_department.controller.realization;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.entity.Realization;
import com.example.sales_department.service.RealizationService;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Component
@FxmlView("/com/example/sales_department/realization/realize_managment.fxml")
public class RealizeManagment {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    RealizationService realizationService;

    @FXML
    private Text findRealizeText;

    @FXML
    private Text filterText;

    @FXML
    private TextArea filterTextArea;

    @FXML
    private DatePicker DateDatePicker;

    @FXML
    private TextField numberUPDTextField;

    @FXML
    private Text StatusText;

    @FXML
    private Text numberUPDText;

    @FXML
    private Text dateText;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private ComboBox<String> contractorComboBox;

    @FXML
    private Text contractorText;

    @FXML
    private Button findButton;

    @FXML
    private TableView<Realization> viewRealizeTableView;

    @FXML
    private TableColumn<?, ?> numberUPDTableColumn1;

    @FXML
    private TableColumn<?, ?> dateTableColumn;

    @FXML
    private TableColumn<?, ?> contractorTableColumn1;

    @FXML
    private TableColumn<?, ?> statusTableColumn1;

    @FXML
    private TableColumn<?, ?> adressTableColumn1;

    @FXML
    private TableColumn<?, ?> timeShipmentTableColumn1;

    @FXML
    private TableColumn<?, ?> dateReceiveTableColumn1;

    @FXML
    private TableColumn<?, ?> priceTableColumn;

    @FXML
    private Button addRealizationButton;

    @FXML
    private Button findRealizeButton;

    @FXML
    private Button viewRealizeButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button editRealizationButton;

    @FXML
    void onAddRealizationButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizationAdd.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onContractorComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onEditRealizationButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizationEdit.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    void onFindButtonClick(ActionEvent event) {

        String status = null;
        if ((!statusComboBox.getValue().isEmpty()) && (statusComboBox.getValue() != null)){
            status = statusComboBox.getValue();
        }

        LocalDate date = null;
        if (DateDatePicker.getValue() != null){
            date = DateDatePicker.getValue();
            DateDatePicker.setValue(null);
        }

        Long numberUPD = null;
        if ((!numberUPDTextField.getText().isEmpty()) && (numberUPDTextField.getText() != null)){
            numberUPD = Long.parseLong(numberUPDTextField.getText());
        }

        BigInteger inn = null;
        if ((!contractorComboBox.getValue().isEmpty()) && (contractorComboBox.getValue() != null)){
            inn = new BigInteger(contractorComboBox.getValue());
        }

        List<Realization> realizations = realizationService.findByAll(status, date, numberUPD, inn);

        ObservableList<Realization> tableItems = FXCollections.observableArrayList(realizations);
        viewRealizeTableView.setItems(tableItems);
        viewRealizeTableView.refresh();

    }

    @FXML
    void onFindRealizeButtonClick(ActionEvent event) {

    }

    @FXML
    void onStatusComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onViewRealizeButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizationView.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
