package presentation.mvp.view.controller.operatore;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
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
import javafx.stage.Modality;


public class SchermataOperatore extends Schermata{

	@FXML
	private Button btnNuovo;
	
	@FXML
	private TableView<ClienteTab> tbCliente;
	
	@FXML
	private TableColumn<ClienteTab,String> nome;
	@FXML
	private TableColumn<ClienteTab,String> cognome;

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
	
	public void aggiornaTabellaCliente(List<ClienteTab> listaClienti){
		ObservableList<ClienteTab> clienti= FXCollections.observableArrayList(listaClienti);
		tbCliente.setItems(clienti);
	}
	/**
	 * <p>Effettua il binding con i singoli campi della tabella</p>
	 */
	public void bindingValues(){
		nome.setCellValueFactory(cellData -> cellData.getValue().getNome());
		cognome.setCellValueFactory(cellData -> cellData.getValue().getCognome());

	}
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		bindingValues();
		try {
			List<ClienteTab> clienteTab = new ArrayList<ClienteTab>();
			clienteTab = ((ClienteTab) clienteTab).converter((List<Cliente>)presenter.processRequest("getAllClienti",null));
			this.aggiornaTabellaCliente(clienteTab);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
