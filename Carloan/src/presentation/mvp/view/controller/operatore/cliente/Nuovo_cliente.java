package presentation.mvp.view.controller.operatore.cliente;

import java.lang.reflect.InvocationTargetException;

import business.entity.Cliente;
import Errori.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
		this.chiudiFinestra();
	}
	
	@FXML
	public void btnConferma(ActionEvent event){
		
		Cliente cliente = new Cliente(txtNome.getText(),txtCognome.getText(),"Maschio",dEmissPatente,dNascita,
				txtIndirizzo.getText(),txtCodFisc.getText(),txtNumCel.getText(),txtNumCel.getText(),txtPatGuida.getText(),dScadPatente,txtPartIva.getText(),txtEmail.getText());
		
		try {
			presenter.processRequest("VerificaCliente", cliente);
			//presenter.processRequest("InserimentoCliente", cliente);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			 AlertView.getAlertView(e.getMessage(),AlertType.ERROR);
		}
		  //AlertView.getAlertView("Cliente Inserito con successo",AlertType.INFORMATION);
	}
}
