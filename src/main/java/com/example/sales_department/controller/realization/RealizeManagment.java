package com.example.sales_department.controller.realization;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.controller.Utils;
import com.example.sales_department.entity.ProductListInRealization;
import com.example.sales_department.entity.Realization;
import com.example.sales_department.service.CustomerService;
import com.example.sales_department.service.FiasService;
import com.example.sales_department.service.ProductListInRealizationService;
import com.example.sales_department.service.RealizationService;
import javafx.beans.property.SimpleStringProperty;
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FxmlView("/com/example/sales_department/realization/realize_managment.fxml")
public class RealizeManagment {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    RealizationService realizationService;
    @Autowired
    ProductListInRealizationService productListInRealizationService;
    @Autowired
    CustomerService customerService;
    @Autowired
    RealizationEdit realizationEdit;

    @FXML
    private Text findRealizeText;

    @FXML
    private Text filterText;

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
    private TableColumn<Realization, String> numberUPDTableColumn;

    @FXML
    private TableColumn<Realization, String> dateTableColumn;

    @FXML
    private TableColumn<Realization, String> contractorTableColumn;

    @FXML
    private TableColumn<Realization, String> statusTableColumn;

    @FXML
    private TableColumn<Realization, String> adressTableColumn;

    @FXML
    private TableColumn<Realization, String> timeShipmentTableColumn;

    @FXML
    private TableColumn<Realization, String> dateReceiveTableColumn;

    @FXML
    private TableColumn<Realization, String> priceTableColumn;

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

    public void initialize() {
        editRealizationButton.setDisable(true);
        viewRealizeTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            editRealizationButton.setDisable(newSelection == null);
        });


        ObservableList<String> data = FXCollections.observableArrayList(
                customerService.getAll().stream().map(customer -> customer.getInn().toString()).collect(Collectors.toList()));
        Utils.addFilter(data, contractorComboBox);

        ObservableList<Realization> list = FXCollections.observableArrayList(realizationService
                .getAll());

//        priceTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue()..toString()));
        dateReceiveTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getReceivingDate().toString()));
        timeShipmentTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getFactedTimeOfShipment().toString()));
        adressTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getConsigneeAddress().toString()));
        //statusTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getPaymentStatus().toString()));
        dateTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getDate().toString()));
        contractorTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdOrder().getIdSpecification().getIdContract().getIdCustomer().getOrganizationName()));
        numberUPDTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getUpdNumber().toString()));
        priceTableColumn.setCellValueFactory(cd -> {
            BigDecimal total = new BigDecimal("0");
            List<ProductListInRealization> products = productListInRealizationService.getAllByRealization(cd.getValue());
            for (ProductListInRealization product : products) {
                BigDecimal price = product.getPrice();
                BigDecimal amount = new BigDecimal(product.getAmount());
                total = total.add(price.multiply(amount));
            }
            return new SimpleStringProperty(total.toString());
        });
        viewRealizeTableView.setItems(list);
    }

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
        realizationEdit.setRealization(viewRealizeTableView.getSelectionModel().getSelectedItem());
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
        LocalDate date = null;
        if (DateDatePicker.getValue() != null){
            date = DateDatePicker.getValue();
            DateDatePicker.setValue(null);
        }

        Long numberUPD = null;
        if ((numberUPDTextField.getText() != null) && (!numberUPDTextField.getText().isEmpty())){
            numberUPD = Long.parseLong(numberUPDTextField.getText());
        }

        BigInteger inn = null;
        if ((contractorComboBox.getValue() != null) && (!contractorComboBox.getValue().isEmpty()) ){
            inn = new BigInteger(contractorComboBox.getValue());
        }

        List<Realization> realizations = realizationService.findByAll(date, numberUPD, inn);

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
