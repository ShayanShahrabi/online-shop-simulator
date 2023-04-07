package digishop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class SellerProductListController implements Initializable {

    @FXML
    private TableView <Product> tvProducts;
    
    @FXML
    private TableColumn <Product, String> colProductName;
    
    @FXML
    private TableColumn <Product, String> colPrice;
    
    @FXML
    private TableColumn <Product, String> colQuantity;
    
    @FXML
    private TableColumn <Product, String> colComments;
    
    ObservableList<Product> observableList;
    //--------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setStyle("-fx-alignment: CENTER;");
        
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colQuantity.setStyle("-fx-alignment: CENTER;");
        
        colComments.setCellValueFactory(new PropertyValueFactory<>("comment"));     
    }      
    //--------------------------------------------------------------------------
}
