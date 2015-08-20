package presentation.mvp.view.controller.generale.contratto;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Utente;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.StatoContratto;
import business.model.Exception.CommonException;
import Errori.AlertView;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class NuovoContratto extends Schermata{
	@FXML
	private DatePicker dCreazione;	
	@FXML
	private DatePicker dChiusura;
	@FXML
	private TableView<Cliente> tbcliente;	
	@FXML
	private TextArea textNote;
	@FXML
	private Button btnCancella;
	@FXML
	private Button btnConferma;
	@FXML
	private TableView<Contratto> tw;
	@FXML
	private TableColumn<Cliente,String> codFiscale;
	@FXML
	private TableColumn<Cliente,String> nome;
	@FXML
	private TableColumn<Cliente,String> cognome;
	
	
	@FXML
	public void btnCancella(ActionEvent event){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void btnConferma(ActionEvent event) throws CommonException{
		 if(tbcliente.getSelectionModel().getSelectedIndex()< 0){
	    		throw new CommonException("Nessun cliente selezionato");
		 }
		 else{
			tw= ((SchermataGenerale)this.getChiamante()).getTable("Contratto");
			Contratto contratto= prendiDatiDaView();
			try {
					presenter.processRequest("VerificaContratto", contratto);	
					presenter.processRequest("InserimentoContratto", contratto);
					//Chiama il metodo della schermata che ha chiamato questa schermata per settare nella tabella dei clienti i clienti ricavati
					((SchermataGenerale)this.getChiamante()).aggiungiElementoAtabella(contratto,tw);
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				//AlertView.getAlertView(e.toString(), AlertType.ERROR);
				e.printStackTrace();
			}
		 }
	}
	
	public Contratto prendiDatiDaView(){
		LocalDate dParam= null;
		
		Contratto contratto = new Contratto();
		
		//questa è solo una questione grafica..
		if(tw.getItems().size()==0)
			contratto.setIDContratto(1);//qui setto l'id del cliente.
		else
			contratto.setIDContratto(tw.getItems().get(tw.getItems().size()-1).getIDContratto()+1);//l'id del contratto nuovo sarà dato a partire dall'ultimo id dell'ultimo elemento 
		
		contratto.setCliente(tbcliente.getSelectionModel().getSelectedItem());//prende l'id del cliente selezionato
		
		contratto.setNote(textNote.getText());
		
		contratto.setStato(StatoContratto.Aperto.toString());
	
		//qui setto l'id dell'utente del sistema
		if(Utente.getUtente() instanceof Operatore)
			contratto.setIDOperatore(1);
		else if(Utente.getUtente() instanceof Amministratore)
			contratto.setIDAmministratore(1);
		else if(Utente.getUtente() instanceof SupervisoreSede)
			contratto.setIDSupervisoreSede(1);
		else 
			contratto.setIDSupervisoreAgenzia(1);
		//..... ne mancano altri di controlli cosi.
		
		dParam= dCreazione.getValue();
		contratto.setDataCreazione(Date.valueOf(dParam));
	
		return contratto;
	}
	
	
	private boolean caricaTabella(List<Cliente> list){
		ObservableList<Cliente> obsList= FXCollections.observableList(list);
		tbcliente.setItems(obsList);
		return true;
	}
	
	private void bindingValues(){
		codFiscale.setCellValueFactory(cellData -> cellData.getValue().getCodFiscaleT());
		nome.setCellValueFactory(cellData -> cellData.getValue().getNomeT());
		cognome.setCellValueFactory(cellData -> cellData.getValue().getCognomeT());	
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	
		dCreazione.setValue(LocalDate.now());//setto il valore di default della data di creazione.
	
		bindingValues();
	
		try {
			caricaTabella((List<Cliente>)presenter.processRequest("getAllClienti",null));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
