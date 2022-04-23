package com.example.sales_department.controller.order;

import com.example.sales_department.controller.contract.ContractManagment;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Fia;
import com.example.sales_department.entity.Order;
import com.example.sales_department.entity.Specification;
import com.example.sales_department.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@FxmlView("/com/example/sales_department/order/order_edit.fxml")
public class OrderEdit {
    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    FiasService fiasService;
    @Autowired
    ContractService contractService;
    @Autowired
    SpecificationService specificationService;

    @Setter
    Order order;

    @FXML
    private TableColumn<Order, String> amountTableColumn;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> contractNumberComboBox;

    @FXML
    private Button confirmEditOrderButton;

    @FXML
    private ComboBox<String> customerComboBox;

    @FXML
    private DatePicker deliveryFinifhDateDatePicker;

    @FXML
    private DatePicker deliveryStartDateDatePicker;

    @FXML
    private Button editProductListInOrderButton;

    @FXML
    private TableColumn<Order, String> priceForAllTableColumn;

    @FXML
    private TableColumn<Order, String> priceForOneTableColumn;

    @FXML
    private Label priceForOrderLabel;

    @FXML
    private TableColumn<Order, String> productCipherTableColumn;

    @FXML
    private TableView<Order> productListTableView;

    @FXML
    private TableColumn<Order, String> productNameTableColumn;

    @FXML
    private ComboBox<String> specificationComboBox;

    @FXML
    void onContractNumberComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(OrderSearch.class);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onConfirmEditOrderButtonClick(ActionEvent event) {
//        Parent root = fxWeaver.loadView(OrderSearch.class);
//        Scene scene = new Scene(root);
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();

        boolean isError = false;
        String errorText = "";

        if(contractNumberComboBox.getValue() == null){
            isError = true;
            errorText += "Поле Контрагента не должно быть пустым. \n";
        }

        if(deliveryStartDateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле Даты начала доставки не должно быть пустым. \n";
        }
        else{
            order.setDeliveryBegin(deliveryStartDateDatePicker.getValue());
        }

        if(deliveryFinifhDateDatePicker.getValue() == null){
            isError = true;
            errorText += "Поле Даты окончания доставки не должно быть пустым. \n";
        }
        else
            order.setDeliveryFinish(deliveryFinifhDateDatePicker.getValue());


        if(specificationComboBox.getValue() == null){
            isError = true;
            errorText += "Поле Спецификации не должно быть пустым. \n";
        }
        else{
            Specification specification = specificationService.getById(Long.parseLong(specificationComboBox.getValue()));
            if(specification == null){
                isError = true;
                errorText += "Указанной спецификации нет в справочнике. \n";
            }
            order.setIdSpecification(specification);
        }

        if(!isError) {
            orderService.add(order);
            Parent root = fxWeaver.loadView(ContractManagment.class);
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
    void onCustomerComboBoxAction(ActionEvent event) {

    }

    @FXML
    void onEditProductListInOrderButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ChangeProductOrder.class);
        Scene scene = new Scene(root);
        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onSpecificationComboBoxAction(ActionEvent event) {

    }

}
