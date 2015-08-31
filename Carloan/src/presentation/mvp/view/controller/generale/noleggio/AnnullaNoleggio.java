package presentation.mvp.view.controller.generale.noleggio;

import java.util.Optional;

import MessaggiFinestra.AlertView;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import presentation.mvp.view.controller.Schermata;

public class AnnullaNoleggio extends Schermata{
	@FXML
	private TextArea textAreaAnnulla;
	@FXML
	public void btnConferma(){
		
	}
	@FXML
	public void btnIndietro(){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Se inseriti,perderai tutti i dati ",AlertType.CONFIRMATION);
			if(result.isPresent() && result.get() == ButtonType.OK)
				this.chiudiFinestra();
	}
	
	

}
