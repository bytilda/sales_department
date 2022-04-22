package com.example.sales_department.controller.contract;

import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.ProductList;
import com.example.sales_department.entity.Specification;
import com.example.sales_department.service.ProductListInSpecificationService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LongStringConverter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@FxmlView("/com/example/sales_department/contract/ProductListOfSpecification.fxml")
public class ProductListOfSpecification {
    @Autowired
    FxWeaver fxWeaver;

    @Autowired
    ProductListInSpecificationService productListInSpecificationService;
    @Setter
    Specification specification;

    @FXML
    private TableView<ProductList> ProductListOfSpecificationYableView;

    @FXML
    private TableColumn<ProductList, String> productNameTC;

    @FXML
    private TableColumn<ProductList, String> costTC;

    @FXML
    private TableColumn<ProductList, String> unitTC;

    @FXML
    private TableColumn<ProductList, Long> amountTC;

    @FXML
    private TableColumn<ProductList, String> totalTC;

    @FXML
    public void initialize() {

        ObservableList<ProductList> list = FXCollections.observableArrayList(productListInSpecificationService.getALLBySpecificationId(specification.getId()));

        totalTC.setCellValueFactory(cd -> {
            if((cd.getValue().getPrice() != null) && (cd.getValue().getAmount() != null)){
                return new SimpleStringProperty(cd.getValue().getPrice().multiply(new BigDecimal(cd.getValue().getAmount().toString())).toString());
            }
            else return new SimpleStringProperty("");
        });
        amountTC.setCellValueFactory(cd -> {
            if(cd.getValue().getAmount() != null){
                return new SimpleLongProperty(cd.getValue().getAmount()).asObject();
            }
            else return null;
        });
        unitTC.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdProduct().getUnits().getId()));
        costTC.setCellValueFactory(cd -> {
            if(cd.getValue().getPrice() != null)
                return new SimpleStringProperty(cd.getValue().getPrice().toString());
            else return new SimpleStringProperty("");
        });
        productNameTC.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdProduct().getProductName()));
        costTC.setCellValueFactory(cd -> {
            if(cd.getValue().getPrice() != null)
                return new SimpleStringProperty(cd.getValue().getPrice().toString());
            else return new SimpleStringProperty("");
        });

        ProductListOfSpecificationYableView.setItems(list);
    }

}
