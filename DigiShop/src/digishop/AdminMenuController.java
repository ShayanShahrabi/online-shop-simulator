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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminMenuController implements Initializable {

    @FXML
    private AnchorPane anchorPaneAdminMenu;    
    
    @FXML
    private AnchorPane anchorPaneAdminMenuContent;

    @FXML
    private Button btnConfirmDeposits;
    
    @FXML
    private Button btnConfirmWithdraws;
    
    @FXML
    private Button btnAddAdmin;
    @FXML
    private Button btnUsersList;
    @FXML
    private Button btnLogout;
    @FXML
    
    
    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    //--------------------------------------------------------------------------    
    @FXML
    private void btnLogoutClicked(ActionEvent event) {
        //بستن استیج فعلی
        Stage stage = (Stage) anchorPaneAdminMenu.getScene().getWindow();
        stage.close();
        //باز کردن یک کنترلر دیگر
        try {
            ArrangeMenuColor("btnLogout");
            
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } 
        catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }    

    //--------------------------------------------------------------------------    
    private void ArrangeMenuColor(String sw){
        btnConfirmDeposits.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnConfirmWithdraws.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnAddAdmin.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnUsersList.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnLogout.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");

        btnLogout.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        switch(sw){
            case "btnConfirmDeposits":
                btnConfirmDeposits.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnConfirmWithdraws":
                btnConfirmWithdraws.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;    
            case "btnAddAdmin":
                btnAddAdmin.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnUsersList":
                btnUsersList.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;      
            case "btnLogout":
                btnLogout.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
        }
    }
    
    //--------------------------------------------------------------------------    
    @FXML
    private void btnConfirmDepositsClicked(ActionEvent event){
        
        try {

            ArrangeMenuColor("btnConfirmDeposits");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminConfirmDeposits.fxml"));
            anchorPaneAdminMenuContent.getChildren().removeAll();
            anchorPaneAdminMenuContent.getChildren().setAll(fxml);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());            
        }
    }
    //--------------------------------------------------------------------------    
    @FXML
    private void btnAddAdminClicked(ActionEvent event){
        
        try {

            ArrangeMenuColor("btnAddAdmin");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminAddAdmin.fxml"));
            anchorPaneAdminMenuContent.getChildren().removeAll();
            anchorPaneAdminMenuContent.getChildren().setAll(fxml);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());            
        }
    }
    //--------------------------------------------------------------------------    
    @FXML
    private void btnUsersListClicked(ActionEvent event){
        
        try {

            ArrangeMenuColor("btnUsersList");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminShowUsersList.fxml"));
            anchorPaneAdminMenuContent.getChildren().removeAll();
            anchorPaneAdminMenuContent.getChildren().setAll(fxml);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());            
        }
    }
    //--------------------------------------------------------------------------
    @FXML
    private void btnConfirmWithdrawsClicked(ActionEvent event){
        try {

            ArrangeMenuColor("btnConfirmWithdraws");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminConfirmWithdraws.fxml"));
            anchorPaneAdminMenuContent.getChildren().removeAll();
            anchorPaneAdminMenuContent.getChildren().setAll(fxml);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());            
        }    
    }

    
    
}
