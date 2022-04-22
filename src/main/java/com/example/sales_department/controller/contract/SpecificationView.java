package com.example.sales_department.controller.contract;

import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Specification;
import com.example.sales_department.service.SpecificationService;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/com/example/sales_department/contract/Specification.fxml")
public class SpecificationView {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    SpecificationService specificationService;

    @FXML
    private TableColumn<Specification, String> contractorTableColumn;

    @FXML
    private TableColumn<Specification, String> dateConclusionTableColumn;

    @FXML
    private TableColumn<Specification, String> appNumberTableColumn;

    @FXML
    private TableColumn<Specification, String> numberContractTableColumn;

    @FXML
    private TableColumn<Specification, String> numberTableColumn;

    @FXML
    private TableView<Specification> specificationTableView;

    @FXML
    private Text specificationText;

    @FXML
    public void initialize(){
        ObservableList<Specification> list = FXCollections.observableArrayList(specificationService.getAll());
        //Integer i = 1;

        contractorTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdContract().getIdCustomer().getOrganizationName()));
        //numberContractTableColumn.setCellValueFactory(cd -> new SimpleStringProperty((i++).toString()));
        appNumberTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getApplicationNumber().toString()));
        numberContractTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getIdContract().getContractNumber().toString()));
        dateConclusionTableColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getConclusionDate().toString()));

        specificationTableView.setItems(list);
    }



}
