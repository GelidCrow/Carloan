package presentation.mvp.view.controller.operatore.cliente;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.ClienteTab;
import business.model.Exception.CommonException;
import Errori.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.operatore.SchermataGenerale;
import utility.ParametriFXML;

public class ControllerCliente extends Schermata{
	@FXML
	private Button btnCancella;
	@FXML
	private Button btnConferma;
	@FXML
	private Button btnConfermaModifica;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCognome;
	@FXML
	private RadioButton rdMaschio;
	@FXML
	private RadioButton rdFemmina;
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
	
	final ToggleGroup group = new ToggleGroup();
	
	@FXML
	public void btnCancella(ActionEvent event){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void btnConferma(ActionEvent event){
		Cliente cliente= prendiDatiDaView();
		try {
				presenter.processRequest("VerificaCliente", cliente);	
				presenter.processRequest("InserimentoCliente", cliente);
				//Chiama il metodo della schermata che ha chiamato questa schermata per settare nella tabella dei clienti i clienti ricavati
				((SchermataGenerale)this.getChiamante()).aggiungiElementoAtabella(cliente,((SchermataGenerale)this.getChiamante()).getTableClienti());
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			//AlertView.getAlertView(e.toString(), AlertType.ERROR);
		}
	}
	
	@SuppressWarnings({ "unchecked","rawtypes" })
	@FXML
	public void btnConfermaModifica(ActionEvent event){

		SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
		Cliente cliente= (Cliente) scChiamante.getEntitaElementoSelezionato();
		//	settaViewIniziale();
		try {
				presenter.processRequest("VerificaCliente", cliente);	
				presenter.processRequest("ModificaCliente", cliente);
				//Prendo la schermata che ha chiamato questo metodo , li passo l'elemento selezionato , il cliente da modificare e la tabella su cui lavorare
				((SchermataGenerale)this.getChiamante()).aggiornaElementotabella(scChiamante.getElemSelezionato(),cliente,((SchermataGenerale)this.getChiamante()).getTableClienti());
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			//AlertView.getAlertView(e.toString(), AlertType.ERROR);
		}
	}
	
	/**
	 * 
	 * <p>Prende i valori inseriti e ci crea un istanza.</p>
	 * @return
	 */
	
	public Cliente prendiDatiDaView(){
		LocalDate dParam= null;
		
		Cliente cliente= new Cliente();
		
		cliente.setNome(txtNome.getText());
		
		cliente.setCognome(txtCognome.getText());
		
		cliente.setSesso(((RadioButton)group.getSelectedToggle()).getText());
		
		dParam= dEmissPatente.getValue();
		cliente.setDataEmissPatente(Date.valueOf(dParam));
		
		dParam= dNascita.getValue();
		cliente.setDatanascita(Date.valueOf(dParam));
		
		cliente.setIndirizzo(txtIndirizzo.getText());
		
		cliente.setCodFiscale(txtCodFisc.getText());
		
		cliente.setNumCell(txtNumCel.getText());
		
		cliente.setNumTel(txtNumTel.getText());
		
		cliente.setPatenteGuida(txtPatGuida.getText());
		
		dParam=  dScadPatente.getValue();
		cliente.setDataScadPatente(Date.valueOf(dParam));
		
		cliente.setPartitaIva(txtPartIva.getText());
		
		cliente.setEmail(txtEmail.getText());
		
		return cliente;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		
		rdMaschio.setToggleGroup(group);
		rdMaschio.setSelected(true);
		
		rdFemmina.setToggleGroup(group);
	}
}
