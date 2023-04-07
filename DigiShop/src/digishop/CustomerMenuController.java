package digishop;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;



public class CustomerMenuController implements Initializable {

    @FXML
    private AnchorPane anchorPaneCustomerMenu;
    
    @FXML
    private AnchorPane anchorPaneCustomerMenuContent;

    @FXML
    private Button btnAdd2Wallet;
    @FXML
    private Button btnCustomerOrder;
    @FXML
    private Button btnSearchProduct;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnMyCart;
    @FXML
    private Button btnMyProfile;
    
    @FXML
    private Label lblUserID;
    
    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    //--------------------------------------------------------------------------    
    @FXML
    private void btnAdd2WalletClicked(ActionEvent event){    
    
        String uID;
        try {
            
            uID = lblUserID.getText(); //شناسه كاربر
            
            ArrangeMenuColor("btnAdd2Wallet");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("CustomerAdd2Wallet.fxml"));
            anchorPaneCustomerMenuContent.getChildren().removeAll();
            anchorPaneCustomerMenuContent.getChildren().setAll(fxml);
            //پاس کردن نام کاربری
            Label label = (Label) anchorPaneCustomerMenuContent.lookup("#lblUserID");
            label.setText(uID);
            label.setVisible(false);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
    }

    //--------------------------------------------------------------------------    
    @FXML
    private void btnMyCartClicked(ActionEvent event){    
    
        String uID;
        try {
            
            uID = lblUserID.getText(); //شناسه كاربر
            
            ArrangeMenuColor("btnMyCart");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("CustomerMyCart.fxml"));
            anchorPaneCustomerMenuContent.getChildren().removeAll();
            anchorPaneCustomerMenuContent.getChildren().setAll(fxml);
            //پاس كردن نام كاربری
            Label label = (Label) anchorPaneCustomerMenuContent.lookup("#lblUserID");
            label.setText(uID);
            label.setVisible(false);
            //پر كردن جدول سفارشات در آنجا نمي توانستيم انجام دهيم زيرا هنوز ليبل كاربر مقدار نگرفته است
            TableView <Order> tv = (TableView) anchorPaneCustomerMenuContent.lookup("#tvOrders");
            ObservableList<Order> observableList = FXCollections.observableArrayList();

            tv.setItems(observableList);

            DataProcess dataProcess = new DataProcess();
            dataProcess.fillObservableListOfOrders(observableList, lblUserID.getText());
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
    }
    
    //--------------------------------------------------------------------------    
    @FXML
    private void btnCustomerOrderClicked(ActionEvent event){
        
        String uID;
        try {
            uID = lblUserID.getText(); //شناسه کاربر

            ArrangeMenuColor("btnCustomerBuy");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("CustomerBuy.fxml"));
            anchorPaneCustomerMenuContent.getChildren().removeAll();
            anchorPaneCustomerMenuContent.getChildren().setAll(fxml);
            //پاس کردن نام کاربری
            Label label = (Label) anchorPaneCustomerMenuContent.lookup("#lblUserID");
            label.setText(uID);
            label.setVisible(false);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());            
        }
    }
    //--------------------------------------------------------------------------    
    @FXML
    private void btnSearchProductClicked(ActionEvent event){
        
        try {
            ArrangeMenuColor("btnSearchProduct");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("CustomerSearch.fxml"));
            anchorPaneCustomerMenuContent.getChildren().removeAll();
            anchorPaneCustomerMenuContent.getChildren().setAll(fxml);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
    }
    //--------------------------------------------------------------------------    
    @FXML
    private void btnLogoutClicked(ActionEvent event) {
        //بستن استیج فعلی
        Stage stage = (Stage) anchorPaneCustomerMenu.getScene().getWindow();
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
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
    }    
    //--------------------------------------------------------------------------    
    private void ArrangeMenuColor(String sw){
        btnAdd2Wallet.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnCustomerOrder.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnSearchProduct.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnMyCart.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnMyProfile.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        
        btnLogout.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        switch(sw){
            case "btnAdd2Wallet":
                btnAdd2Wallet.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnCustomerBuy":
                btnCustomerOrder.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnSearchProduct":
                btnSearchProduct.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnMyCart":
                btnMyCart.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnMyProfile":
                btnMyProfile.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnLogout":
                btnLogout.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
        }
    }
    //--------------------------------------------------------------------------
    public void btnMyProfileClicked(ActionEvent event){
        try {
            ArrangeMenuColor("btnMyProfile");
            
            String phoneNumber = "";
            String  email = "";
            String address = "";
            String wallet = "";
            
            Parent fxml = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
            anchorPaneCustomerMenuContent.getChildren().removeAll();
            anchorPaneCustomerMenuContent.getChildren().setAll(fxml);

            TextField tfPhoneNumber = (TextField) anchorPaneCustomerMenuContent.lookup("#tfPhoneNumber");
            tfPhoneNumber.setText(phoneNumber);
            
            TextField tfEmail = (TextField) anchorPaneCustomerMenuContent.lookup("#tfEmail");
            tfEmail.setText(email);
            
            TextField tfAddress = (TextField) anchorPaneCustomerMenuContent.lookup("#tfAddress");
            tfAddress.setText(address);
            
            TextField tfWallet = (TextField) anchorPaneCustomerMenuContent.lookup("#tfWallet");
            tfWallet.setText(wallet);
            
            DataProcess dataProcess = new DataProcess();
            dataProcess.fillProfile(lblUserID.getText().trim(), tfPhoneNumber, tfEmail, tfAddress, tfWallet);
            
            TextField tfID = (TextField) anchorPaneCustomerMenuContent.lookup("#tfID");
            tfID.setText(lblUserID.getText().trim());
            
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
        
    }
    //--------------------------------------------------------------------------    
    public void setLabelUserIDText(String value){
        lblUserID.setText(value);
    }
    //--------------------------------------------------------------------------
}
