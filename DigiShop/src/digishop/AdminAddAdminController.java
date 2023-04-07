package digishop;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminAddAdminController implements Initializable {
   
    @FXML
    TextField tfID;
    @FXML
    PasswordField pfPassword;
    @FXML
    PasswordField pfPasswordConfirm;
    @FXML
    TextField tfEmail;
    @FXML
    TextField tfAddress;
    @FXML
    TextField tfPhoneNumber;
    @FXML
    private AnchorPane anchorPaneSignUp;

    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    //--------------------------------------------------------------------------    
    @FXML
    private void btnSignUpClicked(ActionEvent event) throws SQLException {
        String type;
        if(!TestOK())
            return;
        //Save Data
        DataProcess dataProcess = new DataProcess();
        type = "Admin";
        dataProcess.Insert2Users(tfID.getText().trim(), pfPassword.getText().trim(), type.trim(), tfEmail.getText().trim(), tfAddress.getText().trim(), tfPhoneNumber.getText().trim());
        //برگرد به صفحه لاگین
        tfID.setText("");
        pfPassword.setText("");
        pfPasswordConfirm.setText("");
        tfEmail.setText("");
        tfAddress.setText("");
        tfPhoneNumber.setText("");    
    }
    //--------------------------------------------------------------------------    
        private boolean TestOK() {
            if(tfID.getText().trim().equals(""))
            {
                Util.showAlert(Alert.AlertType.WARNING, "Invalid ID!");

                tfID.requestFocus();
                return false;
            }
            if(pfPassword.getText().trim().equals(""))
            {
                Util.showAlert(Alert.AlertType.WARNING, "Invalid Password!");

                pfPassword.requestFocus();
                return false;
            }
            if(!pfPassword.getText().trim().equals(pfPasswordConfirm.getText()))
            {
                Util.showAlert(Alert.AlertType.WARNING, "Passwords do no match!");

                pfPassword.requestFocus();
                return false;
            }
            //بررسی فرمت ایمیل
            String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(tfEmail.getText());
            if(!matcher.matches())
            {
                Util.showAlert(Alert.AlertType.WARNING, "Invalid email format!");

                tfEmail.requestFocus();
                return false;
            }
            //
            if(tfAddress.getText().trim().equals(""))
            {
                Util.showAlert(Alert.AlertType.WARNING, "Invalid Address!");

                tfAddress.requestFocus();
                return false;
            }        
            return true;
        }
    //--------------------------------------------------------------------------    
    @FXML
    private void btnBack2AdminMenu(ActionEvent event) {        
        //بستن استیج فعلی
        Stage stage = (Stage) anchorPaneSignUp.getScene().getWindow();
        stage.close();
        //باز کردن یک کنترل دیگر
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
    }
    //--------------------------------------------------------------------------    
    
}
    

