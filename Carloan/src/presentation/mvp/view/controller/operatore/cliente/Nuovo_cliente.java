package presentation.mvp.view.controller.operatore.cliente;

import Errori.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import presentation.mvp.view.controller.Schermata;

public class Nuovo_cliente extends Schermata{
	@FXML
	private Button btnCancella;
	@FXML
	private Button btnConferma;
	
	@FXML
	public void btnCancella(ActionEvent event){
		this.chiudiFinestra();
	}
	
	@FXML
	public void btnConferma(ActionEvent event){
		presenter.processRequest("InserimentoCliente", parameter);
		AlertView.getAlertView("Cliente Inserito con successo",AlertType.INFORMATION);
	}
}
