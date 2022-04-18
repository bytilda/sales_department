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

@Component
@FxmlView("/com/example/sales_department/realization/realize_managment.fxml")
public class RealizeManagment {
    @Autowired
    FxWeaver fxWeaver;

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
    private ComboBox<?> statusComboBox;

    @FXML
    private ComboBox<?> contractorComboBox;

    @FXML
    private Text contractorText;

    @FXML
    private Button findButton;

    @FXML
    private TableView<?> viewRealizeTableView;

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
