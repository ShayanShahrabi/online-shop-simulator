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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AdminShowUsersListController implements Initializable {
    
    @FXML
    private AnchorPane anchorPaneConfirmDeposit;
    
    @FXML
    private TableView <User> tvUser;
    
    @FXML
    private TableColumn <User, String> colUserID;
    
    @FXML
    private TableColumn <User, String> colType;
    
    @FXML
    private TableColumn <User, String> colPhoneNum;
    
    @FXML
    private TableColumn <User, String> colEmail;
    
    @FXML
    private TableColumn <User, String> colAddress;
    
    @FXML
    private TableColumn <User, String> colWallet;
    
    ObservableList<User> observableList = FXCollections.observableArrayList();

    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        

        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colType.setStyle("-fx-alignment: CENTER;");
    
        colPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));        
        colPhoneNum.setStyle("-fx-alignment: CENTER;");
        
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        colWallet.setCellValueFactory(new PropertyValueFactory<>("wallet"));
        colWallet.setStyle("-fx-alignment: CENTER;");

        
        tvUser.setItems(observableList);
        
        DataProcess dataProcess = new DataProcess();
        dataProcess.fillObservableListUsers(observableList);
    }    
    //-------------------------------------------------------------------------- 
    
    
}
