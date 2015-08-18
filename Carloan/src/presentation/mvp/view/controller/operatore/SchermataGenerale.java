package presentation.mvp.view.controller.operatore;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
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
	private TabPane tabPane;
	
	private ObservableList<Tab> panes;

	@FXML
	protected TableView<T> tbCliente;
	private List<T> listaClienti;
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
	 * <p>Modifica un elemento da una tabella</p>
	 */
	public void aggiornaElementotabella(int id,T elem,TableView<T> table){
		table.getItems().add(id, (T) elem);
	}
	
	/**
	 * <p>Carica la tabella dei clienti graficamente</p>
	 * @param listaClienti
	 * @return
	 */
	private boolean caricaTabella(List<T> list,TableView<T> table){
		listaClienti= list;
		ObservableList<T> obsList= FXCollections.observableList(list);
		table.setItems(obsList);
		return true;
	}

	public TableView<T> getTableClienti(){
		return tbCliente;
	}
	
	public int getElemSelezionato(){
		return tbCliente.getSelectionModel().getSelectedIndex()+1;
	}
	
	public T getEntitaElementoSelezionato(){
		return tbCliente.getSelectionModel().getSelectedItem();
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
			if( panes.get(0)==newValue){
				
			}
			else if(panes.get(2)==newValue){
					if(tbClienteCaricata==false){
						try {
							TabClientiController<T> tbClientController = new TabClientiController<T>(tbCliente.getColumns());
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		
		panes= tabPane.getTabs();
		tabPane.getSelectionModel().selectedItemProperty().addListener( new TabChangeListener<Tab>());
	}
}
