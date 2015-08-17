package presentation.mvp.view.controller.operatore.cliente;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import business.entity.Cliente;
import business.model.Exception.CommonException;
import Errori.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import presentation.mvp.view.controller.Schermata;

public class Nuovo_cliente extends Schermata{
	@FXML
	private Button btnCancella;
	@FXML
	private Button btnConferma;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCognome;
	@FXML
	private RadioButton rdMaschio;
	@FXML
	private DatePicker dEmissPatente;
	@FXML
	private DatePicker dNascita;
	@FXML
	private TextField txtIndirizzo;
	@FXML
	private TextField txtCodFisc;
	@FXML
	private TextField txtNumCel;
	@FXML
	private TextField txtNumTel;
	@FXML
	private TextField txtPatGuida;
	@FXML
	private DatePicker dScadPatente;
	@FXML
	private TextField txtPartIva;
	@FXML
	private TextField txtEmail;
 
	
	@FXML
	public void btnCancella(ActionEvent event){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}

	
	@FXML
	public void btnConferma(ActionEvent event){
		
		Cliente cliente = new Cliente(txtNome.getText(),txtCognome.getText(),"Maschio",dEmissPatente,dNascita,
				txtIndirizzo.getText(),txtCodFisc.getText(),txtNumCel.getText(),txtNumCel.getText(),txtPatGuida.getText(),dScadPatente,txtPartIva.getText(),txtEmail.getText());
		
		try {
				presenter.processRequest("VerificaCliente", cliente);	
				presenter.processRequest("InserimentoCliente", cliente);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			//AlertView.getAlertView(e.toString(), AlertType.ERROR);
		}
	}
}
