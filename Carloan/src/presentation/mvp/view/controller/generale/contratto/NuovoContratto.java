package presentation.mvp.view.controller.generale.contratto;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class NuovoContratto extends Schermata{
	@FXML
	private DatePicker dCreazione;	
	@FXML
	private DatePicker dChiusura;
	@FXML
	private TableView<Cliente> tbcliente;	
	@FXML
	private TextField txtNome;
	@FXML
	private Button btnCancella;
	@FXML
	private Button btnConferma;
	@FXML
	private TableView<Contratto> tw;
	
	@FXML
	public void btnCancella(ActionEvent event){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void btnConferma(ActionEvent event){
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
		}
	}
	
	public Contratto prendiDatiDaView(){
		LocalDate dParam= null;
		
		Contratto contratto = new Contratto();
		
		contratto.setIDContratto(tw.getItems().size()+1);
		
		contratto.setStato(StatoContratto.Aperto);
		
		dParam= dCreazione.getValue();
		contratto.setDataCreazione(Date.valueOf(dParam));
		
		dParam = dChiusura.getValue();
		if(dParam!=null){
			contratto.setDataChiusura(Date.valueOf(dParam));
		}
		
		
		return contratto;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		dCreazione.setValue(LocalDate.now());//setto il valore di default della data di creazione.
	}
}
