package presentation.mvp.view.controller.generale.cliente;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;

public class NuovoCliente extends Schermata{
	@FXML
	protected Button btnCancella;
	@FXML
	protected Button btnConferma;
	@FXML
	protected Button btnConfermaModifica;
	@FXML
	protected TextField txtNome;
	@FXML
	protected TextField txtCognome;
	@FXML
	protected RadioButton rdMaschio;
	@FXML
	protected RadioButton rdFemmina;
	@FXML
	protected DatePicker dEmissPatente;
	@FXML
	protected DatePicker dNascita;
	@FXML
	protected TextField txtIndirizzo;
	@FXML
	protected TextField txtCodFisc;
	@FXML
	protected TextField txtNumCel;
	@FXML
	protected TextField txtNumTel;
	@FXML
	protected TextField txtPatGuida;
	@FXML
	protected DatePicker dScadPatente;
	@FXML
	protected TextField txtPartIva;
	@FXML
	protected TextField txtEmail;
	
	private TableView<Cliente> tw;
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
		tw=  ((SchermataGenerale)this.getChiamante()).getTable("Cliente");
		Cliente cliente= prendiDatiDaView();
		try {
				presenter.processRequest("VerificaCliente", cliente);	
				presenter.processRequest("InserimentoCliente", cliente);
				//Chiama il metodo della schermata che ha chiamato questa schermata per settare nella tabella dei clienti i clienti ricavati
				((SchermataGenerale)this.getChiamante()).aggiungiElementoAtabella(cliente,tw);
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
		
		//nota che nel db l'id non lo setto io , ma va in automatico, qui è solo una questione grafica.
		if(tw.getItems().size()==0)
			cliente.setId(1);//qui setto l'id del cliente.
		else 
			cliente.setId(tw.getItems().get(tw.getItems().size()-1).getId()+1);//qui setto l'id del cliente.
	
		cliente.setNome(txtNome.getText());
		
		cliente.setCognome(txtCognome.getText());
		
		cliente.setSesso(((RadioButton)group.getSelectedToggle()).getText());
		
		dParam= dNascita.getValue();
		cliente.setDatanascita(Date.valueOf(dParam));

		dParam= dEmissPatente.getValue();
		cliente.setDataEmissPatente(Date.valueOf(dParam));
		
		cliente.setIndirizzo(txtIndirizzo.getText());
		
		cliente.setCodFiscale(txtCodFisc.getText());
		
		cliente.setNumCell(txtNumCel.getText());
		
		cliente.setNumTel(txtNumTel.getText());
		
		cliente.setPatenteGuida(txtPatGuida.getText());
		
		//data scadenza patente -> dataEmissione + 10 anni.
		dParam=  cliente.getDataScadPatente().toLocalDate();
		dParam.plusYears(10);
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
		
		dEmissPatente.setValue(LocalDate.of(1980,1,1));
		dNascita.setValue(LocalDate.of(1980,1,1));
	}
}
