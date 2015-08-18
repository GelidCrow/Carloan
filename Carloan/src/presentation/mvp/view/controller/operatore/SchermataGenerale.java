package presentation.mvp.view.controller.operatore;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import Errori.AlertView;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;


public class SchermataGenerale<T extends Entity> extends Schermata{

	@FXML
	private Button btnNuovo;
	@FXML
	private TableView<T> tbCliente;
	@FXML
	private TableColumn<T,String> nome;
	@FXML
	private TableColumn<T,String> cognome;
	@FXML
	private TableColumn<T,String> sessoT;
	@FXML
	private TableColumn<T,Date> dataNascita;
	@FXML
	private TableColumn<T,Date> DataEmissPatente;
	@FXML
	private TableColumn<T,String> Email;
	@FXML
	private TableColumn<T,Date> DataScadPatente;
	@FXML
	private TableColumn<T,String> Indirizzo;
	@FXML
	private TableColumn<T,String> codFiscale;
	@FXML
	private TableColumn<T,String> numCell;
	@FXML
	private TableColumn<T,String> numTel;
	@FXML
	private TableColumn<T,String> PatenteGuida;
	@FXML
	private TableColumn<T,String> PartitaIva;
	
	
	private boolean tbClienteCaricata=false;

	@FXML
	public void btnNuovoContratto(ActionEvent e){
		FXMLParameter.setTitolo("Nuovo Contratto");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraSchermataNuovoContratto",Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void btnNuovoCliente(ActionEvent e){
		FXMLParameter.setTitolo("Nuovo Cliente");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraSchermataNuovoCliente",Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void btnLogout(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?",AlertType.CONFIRMATION);
		 
		if(result.isPresent() && result.get() == ButtonType.OK){
			this.chiudiFinestra();
			FXMLParameter.setTitolo("Login");
		    FXMLParameter.setRidimensionabile(false);
			Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraLogin",Modality.WINDOW_MODAL);
		}
	}
	/**
	 * <p>Aggiunge il cliente alla tabella già creata</p>
	 * @param cliente
	 */
	@SuppressWarnings("unchecked")
	public void aggiungiClienteTabella(Cliente cliente){
		tbCliente.getItems().add((T) cliente);
	}
	/**
	 * <p>Carica la tabella dei clienti graficamente</p>
	 * @param listaClienti
	 * @return
	 */
	public boolean caricaTabella(List<T> list,TableView<T> table){
		ObservableList<T> obsList= FXCollections.observableList(list);
		table.setItems(obsList);
		return true;
	}
	/**
	 * <p>Effettua il binding con i singoli campi della tabella</p>
	 */
	public void bindingValues(){
		nome.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getNomeT());
		
		cognome.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getCognomeT());
		
		sessoT.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getSessoT());
		
		dataNascita.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getDatanascitaT());
		
		Indirizzo.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getIndirizzoT());
		
		codFiscale.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getCodFiscaleT());
		
		numCell.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getNumCellT());

		numTel.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getNumTelT());

	    PatenteGuida.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getPatenteGuidaT());

		DataEmissPatente.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getDataEmissPatenteT());

		DataScadPatente.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getDataScadPatenteT());

		PartitaIva.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getPartitaIvaT());

		Email.setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getEmailT());

	}
	
	/**
	 * <p> Ascoltatore per il cambio di tab </p>
	 */
	private class TabChangeListener implements ChangeListener<Number>{
	
		/**
		 * <p>Inizializza l'ascoltatore a null </p>
		 */
		private TabChangeListener() {
    		changed(null, null, 0);		
		}
		/**
		 * <p>Quando selezioni il tab "Cliente" vengono caricati SOLO 1 VOLTA tutti i clienti</p>
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
	    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
			try {
				if(tbClienteCaricata==false)
					tbClienteCaricata= caricaTabella((List<T>)presenter.processRequest("getAllClienti",null),tbCliente);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	} 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		bindingValues();
		tbCliente.getSelectionModel().selectedIndexProperty().addListener(new TabChangeListener());
	}
}
