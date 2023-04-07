package digishop;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Util {


    //--------------------------------------------------------------------------    
    public static void showAlert(Alert.AlertType at, String Message){
        Alert alert = new Alert(at);
        alert.setHeaderText(null);
        alert.setContentText(Message);
        //آیکون تنظیم شود
        Image image;
        String imagePath = "Mhr.png";
        File file = new File(imagePath);
        String absolutePath = file.getAbsolutePath();
        image = new Image("file:///" + absolutePath);
        
        ImageView icon = new ImageView(image);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setGraphic(icon);        
        
        alert.showAndWait();
        return;        
    }
    
    
    
}
