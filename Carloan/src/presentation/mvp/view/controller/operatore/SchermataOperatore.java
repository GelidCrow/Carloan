package presentation.mvp.view.controller.operatore;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.ClienteTab;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import Errori.AlertView;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;


public class SchermataOperatore extends Schermata{

	@FXML
	private Button btnNuovo;
	@FXML
	private TableView<Cliente> tbCliente;
	@FXML
	private TableColumn<Cliente,String> nome;
	@FXML
	private TableColumn<Cliente,String> cognome;
	@FXML
	private TableColumn<Cliente,String> sesso;
	@FXML
	private TableColumn<Cliente,Date> dataNascita;
	@FXML
	private TableColumn<Cliente,Date> DataEmissPatente;
	@FXML
	private TableColumn<Cliente,String> Email;
	@FXML
	private TableColumn<Cliente,Date> DataScadPatente;
	@FXML
	private TableColumn<Cliente,String> Indirizzo;
	@FXML
	private TableColumn<Cliente,String> codFiscale;
	@FXML
	private TableColumn<Cliente,String> numCell;
	@FXML
	private TableColumn<Cliente,String> numTel;
	@FXML
	private TableColumn<Cliente,String> PatenteGuida;
	@FXML
	private TableColumn<Cliente,String> PartitaIva;

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
	
	public void aggiornaTabellaCliente(List<Cliente> listaClienti){
		ObservableList<Cliente> clienti= FXCollections.observableArrayList(listaClienti);
		tbCliente.setItems(clienti);
	}
	/**
	 * <p>Effettua il binding con i singoli campi della tabella</p>
	 */
	public void bindingValues(){
		nome.setCellValueFactory(cellData -> cellData.getValue().getNomeT());
		
		cognome.setCellValueFactory(cellData -> cellData.getValue().getCognomeT());
		
		sesso.setCellValueFactory(cellData -> cellData.getValue().getSessoT());
		
		dataNascita.setCellValueFactory(cellData -> cellData.getValue().getDatanascitaT());
		
		Indirizzo.setCellValueFactory(cellData -> cellData.getValue().getIndirizzoT());
		
		codFiscale.setCellValueFactory(cellData -> cellData.getValue().getSessoT());
		
		numCell.setCellValueFactory(cellData -> cellData.getValue().getNumCellT());

		numTel.setCellValueFactory(cellData -> cellData.getValue().getNumTelT());

	    PatenteGuida.setCellValueFactory(cellData -> cellData.getValue().getPatenteGuidaT());

		DataEmissPatente.setCellValueFactory(cellData -> cellData.getValue().getDataEmissPatenteT());

		DataScadPatente.setCellValueFactory(cellData -> cellData.getValue().getDataScadPatenteT());

		PartitaIva.setCellValueFactory(cellData -> cellData.getValue().getPartitaIvaT());

		Email.setCellValueFactory(cellData -> cellData.getValue().getEmailT());

	}
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		bindingValues();
		try {
			this.aggiornaTabellaCliente((List<Cliente>)presenter.processRequest("getAllClienti",null));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
