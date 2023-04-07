package digishop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerSearchController implements Initializable {
    
    @FXML
    private TableView <Product> tvResult;
    
    @FXML
    private TextField tfSearch;
    
    @FXML
    private TableColumn <Product, String> colProductName;
    
    @FXML
    private TableColumn <Product, String> colSeller;
    
    @FXML
    private TableColumn <Product, Integer> colQuantity;
    
    @FXML
    private TableColumn <Product, Long> colPrice;
    
    @FXML
    private TableColumn <Product, Integer> colComments;
    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    //--------------------------------------------------------------------------
    @FXML
    private void btnGoClicked(ActionEvent event){
        
        if(tfSearch.getText().trim().length() < 3)
        {
            Util.showAlert(Alert.AlertType.INFORMATION, "Input text must contain 3 characters at least!");
            return;
        }
        
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));        

        colSeller.setCellValueFactory(new PropertyValueFactory<>("sellerID"));
    
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));        
        colQuantity.setStyle("-fx-alignment: CENTER;");
        
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setStyle("-fx-alignment: CENTER;");
        
        colComments.setCellValueFactory(new PropertyValueFactory<>("comment"));
        
        
        ObservableList<Product> observableList = FXCollections.observableArrayList();

        tvResult.setItems(observableList);

        DataProcess dataProcess = new DataProcess();
        dataProcess.fillSearchProductTable(observableList, tfSearch.getText().trim());            
    }
}
