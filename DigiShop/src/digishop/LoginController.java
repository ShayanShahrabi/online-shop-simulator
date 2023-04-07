package digishop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    
   
    @FXML
    private TextField tfID;
    
    @FXML
    private PasswordField pfPassword;
    
    @FXML
    private AnchorPane anchorPaneLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    //--------------------------------------------------------------------------    
    @FXML
    private void btnLoginClicked(ActionEvent event) {
        FXMLLoader loader;
        //باز کردن یک کنترل دیگر
        try {
            DataProcess dataProcess = new DataProcess();
            String UserType = dataProcess.IfUserExist(tfID.getText().trim(), pfPassword.getText().trim());
            if(UserType.equals("")){
                Util.showAlert(Alert.AlertType.INFORMATION, "Invalid user!");
                return;
            }
            
            //بستن استیج فعلی
            Stage stage = (Stage) anchorPaneLogin.getScene().getWindow();
            stage.close();
            
            Parent root;
            
            if(UserType.equals("Customer")){
                //پاس کردن نام کاربری
                loader = new FXMLLoader(getClass().getResource("CustomerMenu.fxml"));
                root = loader.load();
                
                CustomerMenuController customerMenuController = loader.getController();
                customerMenuController.setLabelUserIDText(tfID.getText());
            }
            else if(UserType.equals("Seller")){
                loader = new FXMLLoader(getClass().getResource("SellerMenu.fxml"));
                root = loader.load();
                
                SellerMenuController sellerMenuController = loader.getController();
                sellerMenuController.setLabelSellerIDText(tfID.getText());
            }
            else{
                root = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
            }
            Scene scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DigiShop System");
            stage.show();
        } catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
    }
    
    //--------------------------------------------------------------------------    
    @FXML
    private void btnExitClicked(ActionEvent event) {
        closeStage();
        System.exit(0);
    }
    
    //--------------------------------------------------------------------------    
    private void closeStage(){
        Stage stage = (Stage) anchorPaneLogin.getScene().getWindow();
        stage.close();
    }
    
    //--------------------------------------------------------------------------    
    @FXML
    private void btnSignUpClicked(ActionEvent event) {
        //بستن استیج فعلی
        Stage stage = (Stage) anchorPaneLogin.getScene().getWindow();
        stage.close();
        //باز کردن یک کنترل دیگر
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
    }
}
