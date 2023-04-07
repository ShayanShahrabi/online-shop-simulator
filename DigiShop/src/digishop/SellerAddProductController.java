package digishop;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SellerAddProductController implements Initializable {

    @FXML
    private TextField tfProductName;
    
    @FXML
    private TextField tfPrice;

    @FXML
    private TextField tfQuantity;
    
    @FXML
    private TextField tfComment;
    
    @FXML
    private Label lblSellerID;

    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    //--------------------------------------------------------------------------    
    @FXML
    private void btnAddProductClicked(ActionEvent event){    
        Long price;
        Integer quantity;
        if(!TestOK())
            return;
        
        price = Long.parseLong(tfPrice.getText());
        quantity =  Integer.parseInt (tfQuantity.getText());
        
        //
        DataProcess dataProcess = new DataProcess();
        //
        dataProcess.insert2Products(lblSellerID.getText().trim(), tfProductName.getText().trim(), price, quantity, tfComment.getText().trim());
        //
        tfProductName.setText("");
        tfPrice.setText("");
        tfQuantity.setText("");
        tfComment.setText("");
        //
        tfProductName.requestFocus();
    }
    
    //--------------------------------------------------------------------------    
    private boolean TestOK() {
        int quantity = 0;
        Long Price;
        
        if(tfProductName.getText().trim().equals(""))
        {
            Util.showAlert(Alert.AlertType.WARNING, "Invalid Product Name!");
            tfProductName.requestFocus();
            return false;
        }
        
        //تست قيمت
        try{
            Price =  Long.parseLong(tfPrice.getText().trim());
        }
        catch (Exception ex){
            Util.showAlert(Alert.AlertType.WARNING, "Invalid Price!");
            tfPrice.requestFocus();
            return false;
        }  
        
        //تست تعداد
        try{
            quantity =  Integer.parseInt (tfQuantity.getText().trim());
        }
        catch (Exception ex){
            Util.showAlert(Alert.AlertType.WARNING, "Invalid Quantity!");
            tfQuantity.requestFocus();
            return false;
        }  
        
        return true;
    }
    
}
