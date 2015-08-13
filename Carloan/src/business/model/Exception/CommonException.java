package business.model.Exception;

import Errori.AlertView;
import javafx.scene.control.Alert.AlertType;


@SuppressWarnings("serial")
public class CommonException extends Exception{
   public CommonException(String string){
	   AlertView.getAlertView(string,AlertType.ERROR);
	   //super(string);
   }
}
