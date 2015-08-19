package presentation.mvp.view.controller.generale;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Noleggio.Contratto;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import Errori.AlertView;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;


public class SchermataGenerale<T extends Entity> extends Schermata{
	
	@FXML
	private Button btnNuovoCliente;
	@FXML
	private Button btnModificaCliente;
	@FXML
	private Button btnModificaContratto;
	
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	protected TableView<T> tbCliente;
	
	@FXML
	protected TableView<T> tbContratto;
	
	private ObservableList<Tab> panes;
	
	private boolean tbClienteCaricata=false;
	
	@FXML
	public void btnNuovoContratto(ActionEvent e){
		FXMLParameter.setTitolo("Nuovo Contratto");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraSchermataNuovoContratto",Modality.APPLICATION_MODAL);
	
	}
	
	@FXML
	public void btnModificaContratto(ActionEvent e) throws CommonException{
		FXMLParameter.setTitolo("Modifica Contratto");
	    FXMLParameter.setRidimensionabile(false);
	    if(tbContratto.getSelectionModel().getSelectedIndex()< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	if(((Contratto)tbContratto.getSelectionModel().getSelectedItem()).getStato().equals("Aperto")){
	    		Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraSchermataModificaContratto",Modality.APPLICATION_MODAL);
	    	}
	    	else{
	    		AlertView.getAlertView("Attenzione, non è più possibile  modificare questo contratto", AlertType.INFORMATION);
	    	}
	    }
	}
	
	@FXML
	public void btnNuovoCliente(ActionEvent e){
		FXMLParameter.setTitolo("Nuovo Cliente");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraSchermataNuovoCliente",Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void btnModificaCliente(ActionEvent e) throws CommonException{
		FXMLParameter.setTitolo("Modifica Cliente");
	    FXMLParameter.setRidimensionabile(false);
	    if(tbCliente.getSelectionModel().getSelectedIndex()< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraSchermataModificaCliente",Modality.APPLICATION_MODAL);	
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
	private boolean caricaTabella(List<T> list,TableView<T> table){
		ObservableList<T> obsList= FXCollections.observableList(list);
		table.setItems(obsList);
		return true;
	}
	
	public TableView<T> getTable(String table){
		if(table.equals("Cliente")){
			return tbCliente;
		}
		else if(table.equals("Contratto"))
			return tbContratto;
		else 
			return null;
	}
	public int getElemSelezionato(String table){
		if(table.equals("Cliente")){
			return tbCliente.getSelectionModel().getSelectedIndex();
		}
		else if(table.equals("Contratto"))
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
		@SuppressWarnings({ "unchecked", "unused" })
		@Override
	    public void changed(ObservableValue<? extends Tab> ov, Tab oldValue, Tab newValue) {
			if(panes.get(2)==newValue){
					if(tbClienteCaricata==false){
						try {
							//serve solo per fargli fare il binding con le colonne
							TabClientiController<T> tbClientController = new TabClientiController<T>(tbCliente.getColumns());
							//carica la prima volta la tabella 
							tbClienteCaricata = caricaTabella((List<T>)presenter.processRequest("getAllClienti",null),tbCliente);
						} catch (InstantiationException
								| IllegalAccessException
								| ClassNotFoundException
								| NoSuchMethodException | SecurityException
								| IllegalArgumentException
								| InvocationTargetException
								| CommonException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
	    }
	} 
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		
		panes= tabPane.getTabs();
		//serve solo per fargli fare il binding con le colonne
		TabContrattoController<T> tbContrattoController = new TabContrattoController<T>(tbContratto.getColumns());
		//carica la prima volta la tabella 
		try {
			caricaTabella((List<T>)presenter.processRequest("getAllContratti",null),tbContratto);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabPane.getSelectionModel().selectedItemProperty().addListener( new TabChangeListener<Tab>());
	}
}
