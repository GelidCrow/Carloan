package presentation.mvp.view.controller.generale;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Entity;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Operatore;
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
	private TabPane tabPane;	
	@FXML
	private TableView<T> tbCliente;	
	@FXML
	private TableView<T> tbContratto;
	
	private ObservableList<Tab> panes;
	
	private boolean tbClienteCaricata=false;
	
	private TabClientiController tbClientController;

	private TabContrattoController tbContrattoController;
	
	
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
	
	@FXML
	public void btnNuovoCliente(ActionEvent e){
		tbClientController.NuovoCliente();
	}
	
	@FXML
	public void btnModificaCliente(ActionEvent e) throws CommonException{
		tbClientController.ModificaCliente();
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
		@SuppressWarnings("unchecked")
		@Override
	    public void changed(ObservableValue<? extends Tab> ov, Tab oldValue, Tab newValue) {
			if(panes.get(2)==newValue){
				if(tbClienteCaricata==false){
					try {
						//serve solo per fargli fare il binding con le colonne
						tbClientController = new TabClientiController((TableView<Cliente>)tbCliente,SchermataGenerale.this);
						//carica la prima volta la tabella 
						tbClienteCaricata = caricaTabella((List<T>)presenter.processRequest("getAllClienti",null),tbCliente);
					} catch (InstantiationException | IllegalAccessException| ClassNotFoundException| NoSuchMethodException | SecurityException
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
	/**
	 * <p>Elmina i tab che non possono essere usati dall'utente corrente</p>
	 */
	public void settaSchermataPerUtente(){
		if(UtenteCorrente.getUtente().getClass().getName().equals("Operatore")){
			for(int i=6; i<11;i++){
				panes.get(i).setDisable(true);
			}
		}
		else 
			System.out.println("Ciao");
	}
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		
		panes= tabPane.getTabs();
		//serve solo per fargli fare il binding con le colonne
		tbContrattoController = new TabContrattoController((TableView<Contratto>) tbContratto);
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
		//settaSchermataPerUtente();
	}
	
	
}
