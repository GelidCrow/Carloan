package presentation.mvp.view.controller.operatore.contratto;

import presentation.mvp.view.controller.Schermata;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class Nuovo_contratto extends Schermata{
	@FXML
	private Button btnCancella;
	
	@FXML
	public void btnCancella(ActionEvent event){
		this.chiudiFinestra();
	}
	
	
}