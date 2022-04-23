package com.example.sales_department.controller.realization;

import com.example.sales_department.controller.contract.AddSpecification;
import com.example.sales_department.controller.order.OrderEdit;
import com.example.sales_department.entity.ProductNomenclature;
import com.example.sales_department.service.ProductNomenclatureService;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@FxmlView("/com/example/sales_department/realization/change_product_realization.fxml")
public class RealizationChangeProduct {
    @Autowired
    FxWeaver fxWeaver;

    @Autowired
    ProductNomenclatureService productNomenclatureService;

    RealizationAdd realizationAdd;

    @Setter
    Map<Long, ProductNomenclature> products;

    @FXML
    private AnchorPane addProductInOrderAnchorPane;

    @FXML
    private Button addProductInOrderButton;

    @FXML
    private Button cancelButton;


    @FXML
    private Button deleteProductFromOrderButton;

    @FXML
    private TableView<ProductNomenclature> nomenclatureProductTableView;

    @FXML
    private TableColumn<ProductNomenclature, Long> productCipherTableColumn;

    @FXML
    private TableColumn<ProductNomenclature, String> productNameTableColumn;

    @FXML
    private TableColumn<ProductNomenclature, Long> weightTableColumn;

    @FXML
    public void initialize(){
        ObservableList<ProductNomenclature> products = FXCollections.observableArrayList(productNomenclatureService.getAll());

        productCipherTableColumn.setCellValueFactory(cd -> new SimpleLongProperty(cd.getValue().getProductCipher()).asObject());
        productNameTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getProductName()));
        weightTableColumn.setCellValueFactory(cd -> new SimpleLongProperty(cd.getValue().getWeight()).asObject());

        nomenclatureProductTableView.setItems(products);
    }

    @FXML
    void onAddProductInOrderButtonClick(ActionEvent event) {

        if(nomenclatureProductTableView.getSelectionModel().getSelectedItem() != null)
            realizationAdd.addProduct(nomenclatureProductTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onDeleteProductFromOrderButtonClick(ActionEvent event) {

        if(nomenclatureProductTableView.getSelectionModel().getSelectedItem() != null)
            realizationAdd.deleteProduct(nomenclatureProductTableView.getSelectionModel().getSelectedItem());
    }

    @Autowired
    public void setRealizationAdd(@Lazy RealizationAdd realizationAdd) {
        this.realizationAdd = realizationAdd;
    }

}
