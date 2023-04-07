package digishop;
//
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
//
public class MyProfileController implements Initializable {
    @FXML 
    private TextField tfID;
    @FXML 
    private TextField tfPhoneNumber;
    @FXML 
    private TextField tfEmail;
    @FXML 
    private TextField tfAddress;
    @FXML 
    private TextField tfWallet;

    //--------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //--------------------------------------------------------------------------
    
}
