package presentation.mvp.view.controller;

import java.lang.reflect.InvocationTargetException;

import presentation.mvp.view.ReturnableStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	

	
	
}
