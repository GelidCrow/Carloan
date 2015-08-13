package presentation.mvp.view.controller.operatore;

import java.util.Optional;

import presentation.mvp.view.controller.Schermata;
import Errori.AlertView;
import utility.Finestra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;


public class SchermataOperatore extends Schermata{

	@FXML
	private Button btnNuovo;

	@FXML
	public void btnNuovoContratto(ActionEvent e){
		FXMLParameter.setTitolo("Nuovo Contratto");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,"MostraSchermataNuovoContratto",Modality.APPLICATION_MODAL);
	}
	

	
	@FXML
	public void btnNuovoCliente(ActionEvent e){
		FXMLParameter.setTitolo("Nuovo Cliente");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,"MostraSchermataNuovoCliente",Modality.APPLICATION_MODAL);
	}

	
	@FXML
	public void btnLogout(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?",AlertType.CONFIRMATION);
		 
		if(result.isPresent() && result.get() == ButtonType.OK){
			this.chiudiFinestra();
			FXMLParameter.setTitolo("Login");
		    FXMLParameter.setRidimensionabile(false);
			Finestra.visualizzaFinestra(presenter,FXMLParameter,"MostraLogin",Modality.WINDOW_MODAL);
		}
	}
}
