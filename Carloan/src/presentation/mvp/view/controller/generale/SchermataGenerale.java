package presentation.mvp.view.controller.generale;



import integration.DAO.connection.Connection;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Auto.Autoveicolo;
import business.entity.Gestori.Amministratore;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;

public class SchermataGenerale<T extends Entity> extends Schermata{
	@FXML
	private TabPane tabPane;
	private ObservableList<Tab> panes;
	@FXML
	private ImageView auto_image;
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
	private boolean Aggiornando= false;
	
	@FXML
	private MenuButton btnManutenzione;
	
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
			AlertView.getAlertView(e1.getMessage(), AlertType.ERROR);
		}
	}	
	
	@FXML
	public void btnChiudiContratto(ActionEvent e) {
		try {
			tbContrattoController.ChiudiContratto();
		} catch (CommonException e1) {
			AlertView.getAlertView(e1.getMessage(), AlertType.ERROR);
		}
	}		
			/************ CLIENTE *********/
	@FXML
	public void btnNuovoCliente(ActionEvent e){
		tbClientController.NuovoCliente();
	}

	
	@FXML
	public void btnModificaCliente(ActionEvent e) {
		try {
			tbClientController.ModificaCliente();
		} catch (CommonException e1) {
			AlertView.getAlertView(e1.getMessage(), AlertType.ERROR);
		}
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
	public void btnModificaAuto(ActionEvent e){
		try {
			tbAutoController.ModificaAuto();
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	
	
	@FXML
	public void btnVPagamento(ActionEvent e){
		
	}
	
	@FXML
	public void btnVOptional(ActionEvent e){
		
	}
	
	
	/*Carta di credito*/
	public void btnAggiungiCartaCredito(ActionEvent e){
		tbClientController.NuovaCartaCredito();
	}
	public void btnVisualizzaCartaCredito(ActionEvent e){
		tbClientController.VisualizzaCartaCredito();
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
		table.getItems().add(elem);
	}
	/**
	 * <p>Modifica un elemento da una tabella, lo rimuove e poi lo aggiunge in ultima posizione, oppure nel caso era l'ultimo quello modificato lo aggiunge alla 2 posizione</p>
	 */
	public void aggiornaElementotabella(int id,T elem,TableView<T> table){
		Aggiornando=true;
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
		Aggiornando=false;
	}	
	/**
	 * <p>Carica la tabella dei clienti graficamente</p>
	 * @param listaClienti
	 * @return
	 */
	public  void caricaTabella(List<T> list,TableView<T> table){
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
		else if(table.equals("Autoveicolo"))
			return tbAuto;
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
		else if(table.equals("Autoveicolo"))
			return tbAuto.getSelectionModel().getSelectedIndex();
		else
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
		else if(table.equals("Autoveicolo"))
			return tbAuto.getSelectionModel().getSelectedItem();
		else
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
			//cliente
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
			//noleggio
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
			else if(panes.get(0)==newValue){}
			
			//Auto
			else if(panes.get(3)==newValue){
				if(tbAutoController==null){
					tbAutoController=new TabAuto((TableView<Autoveicolo>)tbAuto,SchermataGenerale.this);
					ObservableList<MenuItem> items=btnManutenzione.getItems();
					for(MenuItem m:items){
						switch(m.getText()){
						case "Aggiungi":
							m.setOnAction(new EventHandler<ActionEvent>() {
					            public void handle(ActionEvent t) {
					            	try {
					        			tbAutoController.NuovaManutenzione();
					        		} catch (CommonException e1) {
					        			e1.showMessage();
					        		}
					            }
					        });   
							break;
						case "Chiudi":
							m.setOnAction(new EventHandler<ActionEvent>() {
					            public void handle(ActionEvent t) {
					            	try {
					        			tbAutoController.ModificaManutenzione();
					        		} catch (CommonException e1) {
					        			e1.showMessage();
					        		}
					            }
					        });
							break;
						case "Visualizza":
							m.setOnAction(new EventHandler<ActionEvent>() {
					            public void handle(ActionEvent t) {
					            	try {
					        			tbAutoController.VisualizzaManutenzione();
					        		} catch (CommonException e1) {
					        			e1.showMessage();
					        		}
					            }
					        });
							break;
							
						}
					}
					try {
						caricaTabella((List<T>)presenter.processRequest("getAllAuto",null), tbAuto);
					} catch (InstantiationException | IllegalAccessException
							| ClassNotFoundException | NoSuchMethodException
							| SecurityException | IllegalArgumentException
							| InvocationTargetException | CommonException e) {
						e.printStackTrace();
					}
				}
			}
			
	    }
	} 
	@FXML
	private Label lblCodFiscaleCliente;
	@FXML
	private Label lblNomeCliente;
	@FXML
	private Label lblCognomeCliente;
	@FXML
	private Label lblCodiceFiscaleGestore;
	@FXML
	private Label lblNomeGestore;
	@FXML
	private Label lblCognomeGestore;
	@FXML
	private Label lblContratto;

	/**
	 * <p> Ascoltatore per il cambio di elemento dal COntratto per settare i label per le info aggiuntive </p>
	 */
	@SuppressWarnings("rawtypes")
	private class ItemSelectedContratto implements ChangeListener{
	
		@Override
		public void changed(ObservableValue observable, Object oldValue,Object newValue) {
			//interroga db
			try {
				if(Aggiornando!=true){
					Cliente cliente = (Cliente)presenter.processRequest("letturaCliente",((Contratto)getEntitaElementoSelezionato("Contratto")).getIdCliente());
					popolaLabelCliente(cliente);
					Contratto contratto= (Contratto)getEntitaElementoSelezionato("Contratto");
					Utente gestore= null;
					if(contratto.getIDAmministratore()>0){
						gestore = (Amministratore)presenter.processRequest("letturaAmministratore",contratto.getIDAmministratore());
						lblContratto.setText("Contratto aggiunto dall'amministratore");
					}
					else if(contratto.getIDSupervisoreAgenzia()>0){
						gestore= (SupervisoreAgenzia)presenter.processRequest("letturaSupervisoreAgenzia",contratto.getIDSupervisoreAgenzia());
						lblContratto.setText("Contratto aggiunto dal Supervisore Agenzia");
					}
					else if(contratto.getIDSupervisoreSede()>0){
						gestore = (SupervisoreSede)presenter.processRequest("letturaSupervisoreSede",contratto.getIDSupervisoreSede());
						lblContratto.setText("Contratto aggiunto dal Supervisore Sede");
					}
					else if(contratto.getIDOperatore()>0){
						gestore = (Operatore)presenter.processRequest("letturaOperatore",contratto.getIDOperatore());
						lblContratto.setText("Contratto aggiunto dall'operatore");
					}
					popolaLabelGestore(gestore);
				}
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				e.printStackTrace();
			}
		}
		private void popolaLabelCliente(Cliente cliente){
			 lblCodFiscaleCliente.setText(cliente.getCodFiscale());
			 lblNomeCliente.setText(cliente.getNome());
			 lblCognomeCliente.setText(cliente.getCognome());
		}
		private void popolaLabelGestore(Utente utente){
			lblCodiceFiscaleGestore.setText(utente.getCodiceFiscale());
			lblNomeGestore.setText(utente.getNome());
			lblCognomeGestore.setText(utente.getCognome());
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
	
	
	@SuppressWarnings({ "rawtypes" })
	private class ItemSelectedAutoveicolo implements ChangeListener{

		@Override
		public void changed(ObservableValue observable, Object oldValue,Object newValue) {
			if(!Aggiornando){
			try {
				InputStream i=(InputStream) presenter.processRequest("leggiImmagineAutoveicolo", ((Autoveicolo)getEntitaElementoSelezionato("Autoveicolo")).getIDauto());
				if(i!=null)
				auto_image.setImage(new Image(i));
				else
					auto_image.setImage(null);
				}
		
			catch(CommonException e){
				e.showMessage();
			}
			catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		}
	}
	
	
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		
		panes= tabPane.getTabs();
		//serve solo per fargli fare il binding con le colonne
		tbContrattoController = new TabContratto((TableView<Contratto>) tbContratto);
		//carica la prima volta la tabella 
		try {
			List<Contratto> contratti = (List<Contratto>)presenter.processRequest("getAllContratti",null);
			caricaTabella((List<T>) contratti,tbContratto);
		
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabPane.getSelectionModel().selectedItemProperty().addListener( new TabChangeListener<Tab>());
		tbContratto.getSelectionModel().selectedItemProperty().addListener( new ItemSelectedContratto());
		tbAuto.getSelectionModel().selectedItemProperty().addListener(new ItemSelectedAutoveicolo());
		
		//setta la schermata per l'utente corrente
		settaSchermataPerUtente();
	}	
	
	public void setAggiornando(boolean a){
		this.Aggiornando=a;
	}
}
