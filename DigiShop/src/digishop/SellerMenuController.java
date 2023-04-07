package digishop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SellerMenuController implements Initializable {

    @FXML
    private AnchorPane anchorPaneSellerMenu;    
    
    @FXML
    private AnchorPane anchorPaneSellerMenuContent;
    
    @FXML
    private Button btnMyProfile;
    
    @FXML
    private Button btnAddProduct;    

    @FXML
    private Button btnProductList;

    @FXML
    private Button btnWithdraw;
    
    @FXML
    private Button btnLogout;
    
    @FXML
    private Label lblSellerID;
    
    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    //--------------------------------------------------------------------------    
    @FXML
    private void btnLogoutClicked(ActionEvent event) {
        //بستن استیج فعلی
        Stage stage = (Stage) anchorPaneSellerMenu.getScene().getWindow();
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
        btnMyProfile.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnAddProduct.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnProductList.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnWithdraw.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        btnLogout.setStyle("-fx-background-color: yellow;-fx-text-fill:black;");
        
        switch(sw){
            case "btnMyProfile":
                btnMyProfile.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnAddProduct":
                btnAddProduct.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnProductList":
                btnProductList.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
            case "btnWithdraw":
                btnWithdraw.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;       
            case "btnLogout":
                btnLogout.setStyle("-fx-background-color: darkblue;-fx-text-fill:white;");
                break;
        }
    }    
    
    //--------------------------------------------------------------------------    
    public void setLabelSellerIDText(String value){
        lblSellerID.setText(value);
    }
    //--------------------------------------------------------------------------    
    @FXML
    private void btnAddproductClicked(ActionEvent event) {
        String sellerID;
        try {
            sellerID = lblSellerID.getText(); //شناسه كاربر
            
            ArrangeMenuColor("btnAddProduct");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("SellerAddProduct.fxml"));
            anchorPaneSellerMenuContent.getChildren().removeAll();
            anchorPaneSellerMenuContent.getChildren().setAll(fxml);
            //پاس كردن نام كاربری
            Label label = (Label) anchorPaneSellerMenuContent.lookup("#lblSellerID");
            label.setText(sellerID);
            label.setVisible(false);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }
    }  
    //--------------------------------------------------------------------------
    @FXML
    private void btnProductListClicked(ActionEvent event){
        String sellerID;
        try {
            ArrangeMenuColor("btnProductList");
            
            sellerID = lblSellerID.getText(); //شناسه كاربر
            
            Parent fxml = FXMLLoader.load(getClass().getResource("SellerProductList.fxml"));
            
            anchorPaneSellerMenuContent.getChildren().removeAll();
            anchorPaneSellerMenuContent.getChildren().setAll(fxml);
            
            //پر كردن جدول سفارشات در آنجا نمي توانستيم انجام دهيم زيرا هنوز ليبل كاربر مقدار نگرفته است
            TableView <Product> tv = (TableView) anchorPaneSellerMenuContent.lookup("#tvProducts");
            ObservableList<Product> observableList = FXCollections.observableArrayList();

            tv.setItems(observableList);

            DataProcess dataProcess = new DataProcess();
            dataProcess.fillObservableListOfProducts(observableList, sellerID);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
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
            anchorPaneSellerMenuContent.getChildren().removeAll();
            anchorPaneSellerMenuContent.getChildren().setAll(fxml);

            TextField tfPhoneNumber = (TextField) anchorPaneSellerMenuContent.lookup("#tfPhoneNumber");
            tfPhoneNumber.setText(phoneNumber);
            
            TextField tfEmail = (TextField) anchorPaneSellerMenuContent.lookup("#tfEmail");
            tfEmail.setText(email);
            
            TextField tfAddress = (TextField) anchorPaneSellerMenuContent.lookup("#tfAddress");
            tfAddress.setText(address);
            
            TextField tfWallet = (TextField) anchorPaneSellerMenuContent.lookup("#tfWallet");
            tfWallet.setText(wallet);
            
            DataProcess dataProcess = new DataProcess();
            dataProcess.fillProfile(lblSellerID.getText().trim(), tfPhoneNumber, tfEmail, tfAddress, tfWallet);
            
            TextField tfID = (TextField) anchorPaneSellerMenuContent.lookup("#tfID");
            tfID.setText(lblSellerID.getText().trim());
            
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
        
    }
    //--------------------------------------------------------------------------
    @FXML
    public void btnWithdrawClicked(){

        String uID;
        try {
            uID = lblSellerID.getText(); //شناسه كاربر     
            ArrangeMenuColor("btnWithdraw");
            
            Parent fxml = FXMLLoader.load(getClass().getResource("SellerWithdraw.fxml"));
            anchorPaneSellerMenuContent.getChildren().removeAll();
            anchorPaneSellerMenuContent.getChildren().setAll(fxml);
            
            //پاس کردن نام کاربری
            Label label = (Label) anchorPaneSellerMenuContent.lookup("#lblSellerID");
            label.setText(uID);
            label.setVisible(false);
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getMessage());
        }
    }
}
