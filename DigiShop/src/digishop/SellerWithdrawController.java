package digishop;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class SellerWithdrawController implements Initializable {

    @FXML
    private TextField tfAmount;
    
    @FXML
    private Label lblSellerID;
    
    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    //--------------------------------------------------------------------------    
    private boolean TestOK() {
        
        try{
            // check if input is a number
            int temp =  Integer.parseInt (tfAmount.getText().trim());
        }
        catch (Exception ex){
            Util.showAlert(Alert.AlertType.WARNING, "Invalid Input!");
            tfAmount.requestFocus();
            return false;
        }  
        return true;
    }
    //--------------------------------------------------------------------------    
    @FXML
    private void btnSubmitRequestClicked(ActionEvent event) throws SQLException {
        String todaysDate;
        if(!TestOK())
            return;
        
        //Now Save Data
        DataProcess dataProcess = new DataProcess();
        Long wallet = dataProcess.getWallet(lblSellerID.getText());
        if (wallet < Long.parseLong(tfAmount.getText())){
            Util.showAlert(Alert.AlertType.WARNING, "There is not enough money in your wallet!");
            return;
        }
        LocalDate currentDate = LocalDate.now();
        todaysDate = currentDate.toString();
        
        dataProcess.insert2Transactions(lblSellerID.getText().trim(), todaysDate , Long.parseLong(tfAmount.getText().trim()), "withdraw", false);
        //
        Util.showAlert(Alert.AlertType.INFORMATION, "Transaction request has been sent. The admin will confirm your request asap! ");
        tfAmount.setText("");
    }
    //--------------------------------------------------------------------------        
}
