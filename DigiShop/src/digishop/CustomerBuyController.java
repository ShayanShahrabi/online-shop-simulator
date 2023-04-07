package digishop;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CustomerBuyController implements Initializable {
    
    @FXML
    private ComboBox cbProduct;
    
    @FXML
    private ComboBox cbSeller;      
    
    @FXML
    private TextField tfQuantity;
    
    @FXML
    private Label lblUserID;
    
    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataProcess dp = new DataProcess();
        dp.getProductList(cbProduct);
        //ليست فروشندگان بسته به انتخاب محصول پر ميشود
    }    
 
    //--------------------------------------------------------------------------    
    @FXML
    private void btnAdd2CartClicked(ActionEvent event){    
        String todaysDate;
        Long price, websiteProfit;
        int quantity;
        if(!TestOK())
            return;
        
        LocalDate currentDate = LocalDate.now();
        todaysDate = currentDate.toString();
        quantity =  Integer.parseInt (tfQuantity.getText());
        //قيمت كالا دريافت شود
        DataProcess dataProcess = new DataProcess();
        price = dataProcess.getProductPrice(cbProduct.getValue().toString().trim(), cbSeller.getValue().toString().trim()) * quantity;
        //براي اينکه ايران نگيرد در يک ضرب کردم
        websiteProfit = Math.round(price * 0.1);
        //
        dataProcess.insert2Orders(lblUserID.getText().trim(), cbProduct.getValue().toString().trim(), cbSeller.getValue().toString().trim(),
                                  quantity, todaysDate, price, false, websiteProfit);
        //در اينجا موجودی کسر نمی شود. زمان خرید قطعی موجودی کسر می شود
        tfQuantity.setText("");
    }
    
    //--------------------------------------------------------------------------    
    private boolean TestOK() {
        int quantity = 0, inventory;
        
        if( cbProduct.getValue() == null || cbProduct.getValue().toString().trim().equals(""))
        {
            Util.showAlert(Alert.AlertType.WARNING, "Invalid Product!");
            return false;
        }
        
        if( cbSeller.getValue() == null || cbSeller.getValue().toString().trim().equals(""))
        {
            Util.showAlert(Alert.AlertType.WARNING, "Invalid Seller!");
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
        //تعداد كالا دريافت شود
        DataProcess dataProcess = new DataProcess();
        inventory = dataProcess.getInventory(cbProduct.getValue().toString().trim(), cbSeller.getValue().toString().trim());
        if(inventory < quantity)
        {
            Util.showAlert(Alert.AlertType.WARNING, "Inventory is not enough!");
            tfQuantity.requestFocus();
            return false;
        }
        
        return true;
    }

    //--------------------------------------------------------------------------    
    @FXML
    private void fillSellerList() {
        DataProcess dataProcess = new DataProcess();
        dataProcess.fillSellerListDependOnProduct(cbProduct.getValue().toString().trim(), cbSeller);
    }
    
}
