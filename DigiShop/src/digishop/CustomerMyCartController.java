package digishop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CustomerMyCartController implements Initializable {
    
    @FXML
    private AnchorPane anchorPaneMyCart;
    
    @FXML
    private Label lblUserID;
    
    @FXML
    private TableView <Order> tvOrders;
    
    @FXML
    private TableColumn <Order, String> colOrderDate;
    
    @FXML
    private TableColumn <Order, String> colProductName;
    
    @FXML
    private TableColumn <Order, String> colSellerID;
    
    @FXML
    private TableColumn <Order, Integer> colQuantity;
    
    @FXML
    private TableColumn <Order, Long> colPrice;
    
    ObservableList<Order> observableList;

    
    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        colOrderDate.setStyle("-fx-alignment: CENTER;");
        
        colProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        
        colSellerID.setCellValueFactory(new PropertyValueFactory<>("SellerID"));
        
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colQuantity.setStyle("-fx-alignment: CENTER;");
    
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colPrice.setStyle("-fx-alignment: CENTER;");
    }    

    //--------------------------------------------------------------------------    
    @FXML
    private void btnPayClicked(ActionEvent event){
        
        String result;
        DataProcess dataProcess = new DataProcess();
        result = dataProcess.isOrderdProductsExist(lblUserID.getText().trim());
        if(!result.equals("")){
            Util.showAlert(Alert.AlertType.WARNING," Number of this product is not enough:" + result);
            return;
        }
        //چک شود جمع سفارشات چه مبلغي است و آيا در كيفش موجودی به اندازه كافي هست يا خير
        if(!dataProcess.isEnventoryEnough(lblUserID.getText().trim())){
            Util.showAlert(Alert.AlertType.WARNING,"Not Enough Inventory!");
            return;
        }
        //خريد قطعي انجام شود
        if(dataProcess.doOrders(lblUserID.getText().trim())){
            Util.showAlert(Alert.AlertType.WARNING,"Orders Done.");
        }
        //جدول سفارشات غير قابل مشاهده شود
        TableView tableView = (TableView) anchorPaneMyCart.lookup("#tvOrders");
        tableView.setVisible(false);
    }    
    
    //--------------------------------------------------------------------------    
    @FXML
    private void fillTable(ActionEvent event){
        tvOrders.setItems(observableList);
        DataProcess dataProcess = new DataProcess();
        dataProcess.fillObservableListOfOrders(observableList, lblUserID.getText());
    }    
}

     
