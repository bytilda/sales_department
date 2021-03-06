package com.example.sales_department.controller.contract;

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
@FxmlView("/com/example/sales_department/contract/add_product_in_specification.fxml")
public class AddProductInSpecification {
    @Autowired
    FxWeaver fxWeaver;

    @Autowired
    ProductNomenclatureService productNomenclatureService;

    AddSpecification addSpecification;

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
            addSpecification.addProduct(nomenclatureProductTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onDeleteProductFromOrderButtonClick(ActionEvent event) {
        if(nomenclatureProductTableView.getSelectionModel().getSelectedItem() != null)
            addSpecification.deleteProduct(nomenclatureProductTableView.getSelectionModel().getSelectedItem());
    }

    @Autowired
    public void setAddSpecification(@Lazy AddSpecification addSpecification) {
        this.addSpecification = addSpecification;
    }

}
