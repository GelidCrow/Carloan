package business.model.Exception;

import Errori.AlertView;
import javafx.scene.control.Alert.AlertType;


@SuppressWarnings("serial")
public class CommonException extends Exception{
	private String message;
	public CommonException(String message){
		this.message= message;
	}
	@Override
	public String getMessage(){
		return message;
	}
   public void showMessage(){
	   AlertView.getAlertView(message,AlertType.ERROR);
   }
   
   
}
