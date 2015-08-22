package presentation.mvp.view.controller.generale;



import integration.DAO.connection.Connection;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Auto.Autoveicolo;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import MessaggiFinestra.AlertView;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.beans.property.SimpleStringProperty;

public class SchermataGenerale<T extends Entity> extends Schermata{
	@FXML
	private TabPane tabPane;
	private ObservableList<Tab> panes;
	
	@FXML
	private TableView<T> tbCliente;	
	@FXML
	private TableView<T> tbContratto;
	@FXML
	private TableView<T> tbNoleggio;
	@FXML
	private TableView<T> tbAuto;
	@FXML
	private Label txtBenvenuto;
	@FXML
	private TableColumn<Cliente,String> cliente;
	
	
	private TabClienti tbClientController;

	private TabContratto tbContrattoController;
	
	private TabNoleggio tbNoleggioController;
	
	private TabAuto tbAutoController;
		/***********  CONTRATTO *************/
	@FXML
	public void btnNuovoContratto(ActionEvent e){
		tbContrattoController.setSchermata(this);
		tbContrattoController.NuovoContratto();
		
	}	
	@FXML
	public void btnModificaContratto(ActionEvent e){
		try {
			tbContrattoController.setSchermata(this);
			tbContrattoController.ModificaContratto();
		} catch (CommonException e1) {
			e1.printStackTrace();
		}
	}	
	
	@FXML
	public void btnChiudiContratto(ActionEvent e) {
		try {
			tbContrattoController.ChiudiContratto();
		} catch (CommonException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}		
			/************ CLIENTE *********/
	@FXML
	public void btnNuovoCliente(ActionEvent e){
		tbClientController.NuovoCliente();
	}

	
	@FXML
	public void btnModificaCliente(ActionEvent e) throws CommonException{
		tbClientController.ModificaCliente();
	}
	
	
		/*********** NOLEGGIO ************/ 
	@FXML
	public void btnNuovoNoleggio(ActionEvent e) {
		tbNoleggioController.NuovoNoleggio();
	}
	
	@FXML
	public void btnModificaNoleggio(ActionEvent e) {
		
	}
	
	@FXML
	public void btnChiudiNoleggio(ActionEvent e){
		
	}
	
	
	/*Auto*/
	@FXML
	public void btnNuovaAuto(ActionEvent e){
		tbAutoController.NuovaAuto();
	}
	
	@FXML
	public void btnVPagamento(ActionEvent e){
		
	}
	
	@FXML
	public void btnVOptional(ActionEvent e){
		
	}
	
	@FXML
	public void btnLogout(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?",AlertType.CONFIRMATION);
		 
		if(result.isPresent() && result.get() == ButtonType.OK){
			chiudiFinestra();
			try {
				Connection.chiudiConnessione();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			FXMLParameter.setTitolo("Login");
		    FXMLParameter.setRidimensionabile(false);
			Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraLogin",Modality.WINDOW_MODAL);
		}
	}
	/**
	 * <p>Aggiunge un elemento ad una tabella</p>
	 */
	public void aggiungiElementoAtabella(T elem,TableView<T> table){
		table.getItems().add((T) elem);
	}
	/**
	 * <p>Modifica un elemento da una tabella, lo rimuove e poi lo aggiunge in ultima posizione, oppure nel caso era l'ultimo quello modificato lo aggiunge alla 2 posizione</p>
	 */
	public void aggiornaElementotabella(int id,T elem,TableView<T> table){
		if(id>=0 && elem!=null && table!=null){
			table.getItems().remove(id);
			if(id==table.getItems().size()){
				table.getItems().add(0,(T) elem);
			}
			else 
				table.getItems().add((T) elem);
		}
		else 
			AlertView.getAlertView("Non è possibile aggiornare l'elemento in tabella", AlertType.ERROR);
	}	
	/**
	 * <p>Carica la tabella dei clienti graficamente</p>
	 * @param listaClienti
	 * @return
	 */
	private void caricaTabella(List<T> list,TableView<T> table){
		ObservableList<T> obsList= FXCollections.observableList(list);
		table.setItems(obsList);
	}
	
	public TableView<T> getTable(String table){
		if(table.equals("Cliente")){
			return tbCliente;
		}
		else if(table.equals("Contratto"))
			return tbContratto;
		else if(table.equals("Noleggio"))
			return tbNoleggio;
		else 
			return null;
	}
	public int getElemSelezionato(String table){
		if(table.equals("Cliente")){
			return tbCliente.getSelectionModel().getSelectedIndex();
		}
		else if(table.equals("Contratto"))
			return tbContratto.getSelectionModel().getSelectedIndex();
		else if(table.equals("Noleggio"))
			return tbContratto.getSelectionModel().getSelectedIndex();
		return 0;
	}
	
	public T getEntitaElementoSelezionato(String table){
		if(table.equals("Cliente")){
			return tbCliente.getSelectionModel().getSelectedItem();
		}
		else if(table.equals("Contratto")){
			return tbContratto.getSelectionModel().getSelectedItem();
		}
		else if(table.equals("Noleggio")){
			return tbNoleggio.getSelectionModel().getSelectedItem();
		}
		
		return null;
	}
	
	
	/**
	 * <p> Ascoltatore per il cambio di tab </p>
	 */
	private class TabChangeListener<X> implements ChangeListener<Tab>{
		/**
		 * <p>Quando selezioni il tab "Cliente" vengono caricati SOLO 1 VOLTA tutti i clienti</p>
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
	    public void changed(ObservableValue<? extends Tab> ov, Tab oldValue, Tab newValue) {
			if(panes.get(2)==newValue){
				if(tbClientController==null){
					try {
						//serve solo per fargli fare il binding con le colonne
						tbClientController = new TabClienti((TableView<Cliente>)tbCliente,SchermataGenerale.this);
						//carica la prima volta la tabella 
						caricaTabella((List<T>)presenter.processRequest("getAllClienti",null),tbCliente);
					} catch (InstantiationException | IllegalAccessException| ClassNotFoundException| NoSuchMethodException | SecurityException
							| IllegalArgumentException	
							| InvocationTargetException
							| CommonException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
			}
			else if(panes.get(1)==newValue){
				if(tbNoleggioController==null){
					try {
						//serve solo per fargli fare il binding con le colonne
						tbNoleggioController = new TabNoleggio((TableView<Noleggio>)tbNoleggio,SchermataGenerale.this);
						//carica la prima volta la tabella 
						caricaTabella((List<T>)presenter.processRequest("getAllNoleggi",null),tbNoleggio);
					} catch (InstantiationException | IllegalAccessException| ClassNotFoundException| NoSuchMethodException | SecurityException
							| IllegalArgumentException	
							| InvocationTargetException
							| CommonException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
			}
			else if(panes.get(3)==newValue){
				if(tbAutoController==null){
					tbAutoController=new TabAuto((TableView<Autoveicolo>)tbAuto,SchermataGenerale.this);
					try {
						caricaTabella((List<T>)presenter.processRequest("getAllAuto",null), tbAuto);
					} catch (InstantiationException | IllegalAccessException
							| ClassNotFoundException | NoSuchMethodException
							| SecurityException | IllegalArgumentException
							| InvocationTargetException | CommonException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
	    }
	} 
	
	/**
	 * <p>Elmina i tab che non possono essere usati dall'utente corrente</p>
	 */
	public void settaSchermataPerUtente(){
		Utente utente= UtenteCorrente.getUtente();
		String msgBenvenuto = "Benvenuto "+ utente.getNome() + " " + utente.getCognome() + "\n  Cod: "+  utente.getIdUtente();
		txtBenvenuto.setText(msgBenvenuto);
		if( utente instanceof Operatore){
			panes.remove(3,panes.size());
		}
		else if(utente instanceof SupervisoreSede){
			panes.remove(4, 9);
		}
		else if(utente instanceof SupervisoreAgenzia){
			panes.remove(4);
			panes.remove(7);
		}	
	}
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		
		panes= tabPane.getTabs();
		//serve solo per fargli fare il binding con le colonne
		tbContrattoController = new TabContratto<T>((TableView<T>) tbContratto);
		//carica la prima volta la tabella 
		try {
			List<Contratto> contratti = (List<Contratto>)presenter.processRequest("getAllContratti",null);
			caricaTabella((List<T>) contratti,tbContratto);
			List<Cliente> clienti = new ArrayList<Cliente>();
			for(Contratto c: contratti){
			  	clienti.add((Cliente)presenter.processRequest("leggiCliente",c.getIdCliente()));
			}
			//cliente.setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getNome()+ ((Cliente) cellData.getValue()).getCognome()));
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabPane.getSelectionModel().selectedItemProperty().addListener( new TabChangeListener<Tab>());
		//setta la schermata per l'utente corrente
		settaSchermataPerUtente();
	}	
}
