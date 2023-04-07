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

public class AdminConfirmDepositsController implements Initializable {

    @FXML
    private AnchorPane anchorPaneConfirmDeposit;
    
    @FXML
    private TableView <DepositRequest> tvDepositRequest;
    
    @FXML
    private TableColumn <DepositRequest, String> colRequestDate;
    
    @FXML
    private TableColumn <DepositRequest, String> colUserID;
    
    @FXML
    private TableColumn <DepositRequest, Long> colAmount;
    
    ObservableList<DepositRequest> observableList = FXCollections.observableArrayList();
    
    //--------------------------------------------------------------------------    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colRequestDate.setCellValueFactory(new PropertyValueFactory<>("RequestDate"));
        colRequestDate.setStyle("-fx-alignment: CENTER;");
        
        colUserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));        
    
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        colAmount.setStyle("-fx-alignment: CENTER;");
        
        tvDepositRequest.setItems(observableList);
        
        DataProcess dataProcess = new DataProcess();
        dataProcess.fillObservableListDepositRequest(observableList);
    }    
    //--------------------------------------------------------------------------    
    @FXML
    private void btnConfirmClicked(ActionEvent event){
        Boolean result;
        DataProcess dataProcess = new DataProcess();
        
        result = dataProcess.isDepositRequestsExist();
        if(!result){
            Util.showAlert(Alert.AlertType.WARNING,"There is no deposit request.");
            return;
        }
        //تقاضاهاي واريز تاييد شود
        dataProcess.doDepositRequests();
        Util.showAlert(Alert.AlertType.WARNING,"Deposit Requests Done.");
        //جدول تقاضاهاي واريز غير قابل مشاهده شود
        TableView tableView = (TableView) anchorPaneConfirmDeposit.lookup("#tvDepositRequest");
        tableView.setVisible(false);
    }    
}
