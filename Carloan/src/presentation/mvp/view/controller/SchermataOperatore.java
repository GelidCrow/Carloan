package presentation.mvp.view.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import Errori.AlertView;
import presentation.mvp.view.ReturnableStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;


public class SchermataOperatore extends Schermata{

	@FXML
	private Button btnNuovo;

	@FXML
	public void btnNuovo(ActionEvent e){
		ReturnableStage stager;
		try {
			FXMLParameter.setTitolo("Nuovo Contratto");
		    FXMLParameter.setRidimensionabile(false);
			stager = (ReturnableStage) presenter.processRequest("MostraSchermataNuovoContratto",FXMLParameter);
			stager.setStageToWindow(stager);
			stager.show();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void btnLogout(ActionEvent e){
		ReturnableStage stager;
		try {
			 Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?",AlertType.CONFIRMATION);
			 
			if(result.isPresent() && result.get() == ButtonType.OK){
				this.chiudiFinestra();
				FXMLParameter.setTitolo("Login");
			    FXMLParameter.setRidimensionabile(false);
				stager = (ReturnableStage) presenter.processRequest("MostraLogin",FXMLParameter);
				stager.setStageToWindow(stager);
				stager.show();
			}
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
}
