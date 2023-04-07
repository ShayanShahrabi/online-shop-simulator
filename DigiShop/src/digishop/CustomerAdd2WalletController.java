package digishop;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import javafx.scene.control.Label;

public class CustomerAdd2WalletController implements Initializable {
    @FXML
    private TextField tfAmount;
    
    @FXML
    private Label lblUserID;
    
    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    //--------------------------------------------------------------------------    
    private boolean TestOK() {
        try{
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
        
        LocalDate currentDate = LocalDate.now();
        todaysDate = currentDate.toString();
        
        dataProcess.insert2Transactions(lblUserID.getText().trim(), todaysDate , Long.parseLong(tfAmount.getText().trim()), "deposit", false);
        //
        Util.showAlert(Alert.AlertType.INFORMATION, "Transaction request has been sent. The admin will confirm your request asap! ");
        tfAmount.setText("");
    }
    //--------------------------------------------------------------------------
}
