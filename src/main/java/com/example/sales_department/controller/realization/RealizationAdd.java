package com.example.sales_department.controller.realization;

import com.example.sales_department.controller.HelloController;
import com.example.sales_department.entity.ProductList;
import com.example.sales_department.entity.ProductNomenclature;
import com.example.sales_department.service.ProductListInSpecificationService;
import com.example.sales_department.service.RealizationService;
import com.example.sales_department.service.SpecificationService;
import javafx.beans.property.SimpleLongProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
@FxmlView("/com/example/sales_department/realization/realization_add.fxml")
public class RealizationAdd {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    RealizationService realizationService;
    @Autowired
    ProductListInSpecificationService productListInSpecificationService;

    @FXML
    private Label addRealizeLabel;

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
    private TableView<ProductList> listProductTableView;

    @FXML
    private TableColumn<ProductList, String> codeProductTableColumn;

    @FXML
    private TableColumn<ProductList, String> nameProductTableColumn;

    @FXML
    private TableColumn<ProductList, String> unitTableColumn;

    @FXML
    private TableColumn<ProductList, Long> amountTableColumn;

    @FXML
    private TableColumn<ProductList, String> priceTableColumn;

    @FXML
    private TableColumn<ProductList, String> fullPriceTableColumn;

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
    private Button addRealizeButton;

    @FXML
    private Button removeRealizeButton;

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
    private Button findRealizeButton;

    @FXML
    private Button viewRealizeButton;

    @FXML
    private Button ExitButton;

    ObservableList<ProductList> products = FXCollections.observableArrayList();


    @FXML
    void onAddProductInOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizationChangeProduct.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onAddRealizeButtonClick(ActionEvent event) {

    }

    @FXML
    void onAdressComboBoxAction(ActionEvent event) {

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
    void onFindRealizeButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizeManagment.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onNumberOrderComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onRemoveRealizeButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizeMenu.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onViewRealizeButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(RealizationView.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void deleteProduct(ProductNomenclature productNomenclature){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getIdProduct().getId().equals(productNomenclature.getId())){
                products.remove(i);
                break;
            }
        }
    }

    public void addProduct(ProductNomenclature productNomenclature){
        boolean isExist = false;
        for(ProductList productList: products){
            if(Objects.equals(productList.getIdProduct().getId(), productNomenclature.getId())){
                isExist = true;
                break;
            }
        }
        if(!isExist)
            products.add(ProductList.builder().idProduct(productNomenclature).build());
        listProductTableView.refresh();
    }



}
