package com.example.sales_department.controller.contract;

import com.example.sales_department.entity.*;
import com.example.sales_department.entity.Specification;
import com.example.sales_department.service.ProductListInSpecificationService;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.RollbackException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@FxmlView("/com/example/sales_department/contract/add_specification.fxml")
public class AddSpecification {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    SpecificationService specificationService;
    @Autowired
    AddProductInSpecification addProductInSpecification;
    @Autowired
    ProductListInSpecificationService productListInSpecificationService;

    @Setter
    Contract contract;

    @FXML
    private Button addProductIlSpecification;

    @FXML
    private Button addSpecificationButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label contractNumberLabel;

    @FXML
    private Label labelContractors;

    @FXML
    private TextField numberAppTextField;

    @FXML
    private Button skipButton;

    @FXML
    private TableView<ProductList> productsTableView;

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
    private Label sumLabel;

    @FXML
    private Label digitLabel;

    @FXML
    private DatePicker conclusionDateDP;

    //Map<Long, ProductNomenclature> products = new HashMap<>();
    ObservableList<ProductList> products = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        contractNumberLabel.setText("Номер договора: " + contract.getContractNumber());
        labelContractors.setText("Контрагент: " + contract.getIdCustomer().getOrganizationName());

        numberAppTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numberAppTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        productNameTC.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdProduct().getProductName()));
        costTC.setCellValueFactory(cd -> {
            if(cd.getValue().getPrice() != null)
                return new SimpleStringProperty(cd.getValue().getPrice().toString());
            else return new SimpleStringProperty("");
        });
        costTC.setCellFactory(TextFieldTableCell.forTableColumn());
        costTC.setOnEditCommit(event ->{
            ProductList productList = event.getRowValue();
            productList.setPrice(new BigDecimal(event.getNewValue()));
            productsTableView.refresh();
            updateSum();
        });

        amountTC.setCellValueFactory(cd -> {
            if(cd.getValue().getAmount() != null){
                return new SimpleLongProperty(cd.getValue().getAmount()).asObject();
            }
            else return null;
        });
        amountTC.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        amountTC.setOnEditCommit(event -> {
            ProductList productList = event.getRowValue();
            productList.setAmount(event.getNewValue());
            productsTableView.refresh();
            updateSum();
        });
        unitTC.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdProduct().getUnits().getId()));
        totalTC.setCellValueFactory(cd -> {
            if((cd.getValue().getPrice() != null) && (cd.getValue().getAmount() != null)){
                return new SimpleStringProperty(cd.getValue().getPrice().multiply(new BigDecimal(cd.getValue().getAmount().toString())).toString());
            }
            else return new SimpleStringProperty("");
        });

        productsTableView.setItems(products);
    }


    @FXML
    void onAddProductInSpecificationClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(AddProductInSpecification.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onAddSpecificationButtonClick(ActionEvent event) {
        Specification specification = new Specification();
        specification.setIdContract(contract);
        boolean isError = false;
        String errorText = "";
        if(numberAppTextField.getText() == null || numberAppTextField.getText().isEmpty()){
            isError = true;
            errorText += "Поле номера спецификации не долнжно быть пустым\n";
        }
        else {
            specification.setApplicationNumber(Long.parseLong(numberAppTextField.getText()));
        }

        if(conclusionDateDP.getValue() == null){
            isError = true;
            errorText += "Выберите дату заключения";
        }
        else{
            specification.setConclusionDate(conclusionDateDP.getValue());
        }


        if(!isError) {
            specificationService.add(specification);
            for(ProductList productList: products){
                productList.setIdSpecification(specification);
                ProductListId productListId = new ProductListId();
                productListId.setIdProduct(productList.getIdProduct().getId());
                productListId.setIdSpecification(specification.getId());
                productList.setId(productListId);
                productListInSpecificationService.add(productList);
            }
            Parent root = fxWeaver.loadView(ViewContract.class);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Заполните все поля");
            alert.setContentText(errorText);
            alert.showAndWait();
        }

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) throws RollbackException {
        Parent root = fxWeaver.loadView(AddContract.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        throw new RollbackException();

    }

    @FXML
    void onNumberAppTextField(ActionEvent event) {

    }

    @FXML
    void onSkipButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ViewContract.class);
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
        productsTableView.refresh();
    }

    public void updateSum(){
        BigDecimal total = new BigDecimal("0");
        for(int i = 0; i < products.size(); i++){
            String stringValue = totalTC.getCellObservableValue(i).getValue();
            if(stringValue != null && !stringValue.isEmpty()) {
                BigDecimal value = new BigDecimal(stringValue);
                total = total.add(value);
            }
        }
        digitLabel.setText(total.toString());
    }

}
