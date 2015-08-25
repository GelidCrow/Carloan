package presentation.mvp.view.controller.generale.noleggio;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Luoghi.Sede;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Optional.Assicurazione_KASKO;
import business.entity.Noleggio.Optional.Guidatore;
import business.entity.Noleggio.Optional.GuidatoreAggiuntivo;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.OptionalAuto;
import business.entity.Noleggio.Optional.OptionalNoleggio;
import business.entity.Noleggio.Optional.Seggiolino;
import business.entity.pagamento.CartaDiCredito;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;

public class ImpostaNoleggio<T extends Entity> extends Schermata{
	@FXML
	private DatePicker dInizio;
	@FXML
	private DatePicker dFine;
	@FXML
	private DatePicker dRitiro;
	@FXML
	private TextField txtKmBase;
	@FXML
	private TextField txtNumSettimane;
	@FXML
	private TextField txtNumGiorni;
	@FXML
	private TextField txtNumChilometri;
	@FXML
	private TableView<T> tbRestituzione;
	@FXML
	protected TableView<T> tbOptionalNoleggio;	
	@FXML
	protected  TableView<T> tbOptionalScelti;
	@FXML
	private ChoiceBox<Integer> choiceSeggiolini;
	@FXML
	protected ChoiceBox<Integer> choiceLimite;
	/***
	 * Cliente
	 */
	@FXML
	private Label lblCodFiscale;
	@FXML
	private Label lblNome;
	@FXML
	private Label lblCognome;
	


	@FXML
	private TableView<T> tbGuidatori;
	@FXML
	private TableView<T> tbCartaCredito;
	@FXML
	private TableView<T> tbAutoveicolo;
	@FXML
	private TableView<T> tbContratto;
	@FXML
	protected TableView<T> tbOptionalAuto;
	@FXML
	private ChoiceBox<String> choiceFascia;
	@FXML
	private TextField txtAcconto;
	@FXML
	private TextField txtCauzione;
	@FXML
	private TextField txtTotale;
	@FXML
	private RadioButton rdDenaro;
	@FXML
	private RadioButton rdCartaCredito;
	
	final ToggleGroup group = new ToggleGroup();

	
	
	private ObservableList<TableColumn<Sede,?>> restituzione;

	private ObservableList<TableColumn<Optional,?>> optionalNoleggio;

	private ObservableList<TableColumn<Optional,?>> optionalAuto;

	private ObservableList<TableColumn<Optional,?>> optionalScelti;

	private ObservableList<TableColumn<Guidatore,?>> guidatori;
	
	private ObservableList<TableColumn<Autoveicolo,?>> autoveicoli;

	private ObservableList<TableColumn<CartaDiCredito,?>> carteDiCredito;

	private ObservableList<TableColumn<Contratto,?>> contratti;

	
	
	/******* TABELLA CARTA DI CREDITO ***/
	@FXML
	private TableColumn<CartaDiCredito,String> iban;
	@FXML
	private TableColumn<CartaDiCredito,LocalDate> dScadenza;
	@FXML
	private TableColumn<CartaDiCredito,String> circuito;
	
	private void bindingValuesCartaCredito(){
		iban.setCellValueFactory(cellData ->  new SimpleStringProperty(((CartaDiCredito) cellData.getValue()).getIBAN()));
		dScadenza.setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((CartaDiCredito) cellData.getValue()).getDataScadenza()));
		circuito.setCellValueFactory(cellData ->  new SimpleStringProperty(((CartaDiCredito) cellData.getValue()).getCircuito()));
	}
	
	/******* TABELLA AUTOVEICOLO ***/
	@FXML
	private TableColumn<Autoveicolo,String> targa;
	@FXML
	private TableColumn<Autoveicolo,String> marca;
	@FXML
	private TableColumn<Autoveicolo,String> modello;
	@FXML
	private TableColumn<Autoveicolo,?> prezzoAuto;
	
	private void bindingValuesAutoveicolo(){
		targa.setCellValueFactory(cellData ->  new SimpleStringProperty(((Autoveicolo) cellData.getValue()).getTarga()));
		marca.setCellValueFactory(cellData ->  new SimpleStringProperty(((Autoveicolo) cellData.getValue()).getMarca()));
		modello.setCellValueFactory(cellData ->  new SimpleStringProperty(((Autoveicolo) cellData.getValue()).getModello()));
		prezzoAuto.setCellValueFactory(cellData ->  new SimpleFloatProperty(((Autoveicolo) cellData.getValue()).getPrezzo()));
	}
	
	/******* TABELLA CONTRATTO ***/
	@FXML
	private TableColumn<Contratto,?> IDContratto;
	@FXML
	private TableColumn<Contratto,LocalDate> dScadenzaContratto;
	private void bindingValuesContratto(){
		IDContratto.setCellValueFactory(cellData ->  new SimpleIntegerProperty(((Contratto) cellData.getValue()).getIDContratto()));
		dScadenzaContratto.setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((Contratto) cellData.getValue()).getDataCreazione()));
	}
	
	private void bindingValuesGuidatore(){
		guidatori.get(0).setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getCodFiscale()));
		guidatori.get(1).setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getNome()+((Guidatore) cellData.getValue()).getCognome()));
	}
	
	/** OPTIONAL **/
	@FXML
	private TableColumn<Optional,String> nomeOptNoleggio;
	@FXML
	private TableColumn<Optional,String> DescOptNoleggio;
	
	@FXML
	private TableColumn<Optional,String> nomeOptAuto;
	@FXML
	private TableColumn<Optional,String> descOptAuto;
	
	@FXML
	private TableColumn<Optional,String> nomeOptScelti;
	@FXML
	private TableColumn<Optional,String> descOptScelti;
	
	
	private void bindingValuesOptional(){
		nomeOptNoleggio.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getNome()));
		DescOptNoleggio.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getDescrizione()));

		nomeOptAuto.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getNome()));
		descOptAuto.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getDescrizione()));
		
		nomeOptScelti.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getNome()));
		descOptScelti.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getDescrizione()));
	}
	
	
	/******* TABELLA SEDE RESTITUZIONE ***/
	@FXML
	private TableColumn<Sede,?> IDSede;
	@FXML
	private TableColumn<Sede,String> NomeSede;
	@FXML
	private TableColumn<Sede,String> IndirizzoSede;
	
	private void bindingValuesRestituzione(){
		IDSede.setCellValueFactory(cellData ->  new SimpleIntegerProperty(((Sede) cellData.getValue()).getIDSede()));
		NomeSede.setCellValueFactory(cellData ->  new SimpleStringProperty(((Sede) cellData.getValue()).getNome()));
		IndirizzoSede.setCellValueFactory(cellData ->  new SimpleStringProperty(((Sede) cellData.getValue()).getIndirizzo()));
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
	

	private List<Optional> optional=null;
	private List<Seggiolino> seggiolini=null;
	@SuppressWarnings("unchecked")
	private void inizializzaTabelleOptional(){
		try {
			optional = (List<Optional>)presenter.processRequest("getAllOptional",null);
			seggiolini= new ArrayList<Seggiolino>();
			//imposto gli optional senza eliminarli
			for(Optional e : optional){
				if(e instanceof Seggiolino){
					seggiolini.add((Seggiolino) e);
				}
			}
			//metto nel set cosi toglie i doppioni
			Set<Optional> viewOptional = new HashSet<Optional>();
			viewOptional.addAll(optional);
			List<OptionalAuto> optAuto = new ArrayList<OptionalAuto>();
		
			List<OptionalNoleggio> optNoleggio = new ArrayList<OptionalNoleggio>();
			for(Optional op: viewOptional){
				if(op instanceof OptionalAuto){
					optAuto.add((OptionalAuto) op);
				}
				else 
					optNoleggio.add((OptionalNoleggio) op);
			}
			
			caricaTabella((List<T>) optAuto, tbOptionalAuto);
			caricaTabella((List<T>) optNoleggio, tbOptionalNoleggio);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * <p>Fa visualizzare solo le auto opportune in base all'utente corrente e in base alla fascia</p>
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws CommonException
	 */
	@SuppressWarnings("unchecked")
	private void inizializzaTabellaAutoveicolo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, CommonException{
		Utente utente = UtenteCorrente.getUtente();
		if(utente instanceof Amministratore )
			caricaTabella((List<T>)presenter.processRequest("getAllAuto",null), tbAutoveicolo);
		else if( utente instanceof SupervisoreSede){
			SupervisoreSede supervisoreS= (SupervisoreSede) utente;
			caricaTabella((List<T>)presenter.processRequest("getAllAutoDisponibiliBySede",supervisoreS.getIDSede()), tbAutoveicolo);
		}
		else if( utente instanceof Operatore){
			Operatore operatore= (Operatore) utente;
			caricaTabella((List<T>)presenter.processRequest("getAllAutoDisponibiliBySede",operatore.getIDSede()), tbAutoveicolo);
		}
		else {//prendo solo le auto delle sedi sottostanti l'agenzia a cui appartiene l'utente corrente. 
			SupervisoreAgenzia supervisoreA = (SupervisoreAgenzia) utente;
			List<Sede> sedi = (List<Sede>)presenter.processRequest("getAllSediByAgenzia",supervisoreA.getIDAgenzia());
			List<Autoveicolo> autoveicoli  = new ArrayList<Autoveicolo>();
			for(Sede s: sedi){
				List<Autoveicolo> auto= (List<Autoveicolo>) presenter.processRequest("getAllAutoDisponibiliBySede",s.getIDSede());
				for(Autoveicolo a: auto){
					autoveicoli.add(a);
				}
			}
			caricaTabella((List<T>)autoveicoli, tbAutoveicolo);
		}
	}
	@FXML
	private Label lblprezzoOptNoleggio;
	@FXML
	private Label lblLimiteCopertura;
	@FXML
	private Label lblprezzoOptAuto;
	/**
	 * <p> Ascoltatore per il cambio di elemento dal COntratto per settare i label per le info aggiuntive </p>
	 */
	@SuppressWarnings("rawtypes")
	private class ItemSelected implements ChangeListener{
	
		@SuppressWarnings("unchecked")
		@Override
		public void changed(ObservableValue observable, Object oldValue,Object newValue) {
			//interroga db
			try {
				if(newValue!=null){
					if(newValue instanceof Contratto){
						Contratto contratto= (Contratto)newValue;
						Cliente cliente = (Cliente)presenter.processRequest("letturaCliente",contratto.getIdCliente());
						popolaLabelCliente(cliente);
						caricaTabella((List<T>)presenter.processRequest("getCarteByCliente",contratto.getIdCliente()), tbCartaCredito);
					}
					else if(newValue instanceof OptionalNoleggio){
						popolaLabelOptionalNoleggio(newValue);
						
					}
					else {
						popolaLabelOptionalAuto(newValue);
					}
				}
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				e.printStackTrace();
			}
		}
		private void popolaLabelCliente(Cliente cliente){
			 lblCodFiscale.setText(cliente.getCodFiscale());
			 lblNome.setText(cliente.getNome());
			 lblCognome.setText(cliente.getCognome());
		}
		
		
		private void popolaLabelOptionalNoleggio(Object optional){
			Optional option = (Optional) optional;
			lblLimiteCopertura.setText("");
			if(option instanceof Assicurazione_KASKO){
				lblLimiteCopertura.setText(String.valueOf(((Assicurazione_KASKO)option).getCopertura())+ " €");	
			}
			lblprezzoOptNoleggio.setText(String.valueOf(option.getPrezzo())+  " €");
		}
		
		private void popolaLabelOptionalAuto(Object optional){
			Optional option = (Optional) optional;
			lblprezzoOptAuto.setText(String.valueOf(option.getPrezzo())+  " €");
			choiceSeggiolini.setDisable(true);
		   if(option instanceof Seggiolino){
			   choiceSeggiolini.setDisable(false);
		   }
		}
	
	}
	
	
	
	private void inizializzaToggleButton(){
		rdDenaro.setToggleGroup(group);
		rdCartaCredito.setToggleGroup(group);
	}
	private void inizializzaChoiceBox() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, CommonException{
		 @SuppressWarnings("unchecked")
		 //FASCE
		 List<Fascia> fasce=(LinkedList<Fascia>) presenter.processRequest("getAllFasce", null);
		 LinkedList<String> temp=new LinkedList<String>();
		 for(Fascia f:fasce)
			 temp.add(f.getNome());
		 choiceFascia.setItems(FXCollections.observableArrayList(temp));
		 choiceFascia.getSelectionModel().select(0);
		 
		 //NUMERO SEGGIOLINI
		 LinkedList<Integer> temp2=new LinkedList<Integer>();
		 for(int i=1;i<=seggiolini.size();i++){
			 temp2.add(i);
		 }
		 choiceSeggiolini.setItems(FXCollections.observableArrayList(temp2));
		 choiceSeggiolini.getSelectionModel().select(0);
		 choiceSeggiolini.setDisable(true);
		 
		 //LIMITE CHILOMETRAGGIO
		 LinkedList<Integer> temp3=new LinkedList<Integer>();
		 temp3.add(10000);
		 temp3.add(20000);
		 temp3.add(30000);
		 choiceLimite.setItems(FXCollections.observableArrayList(temp3));
		 choiceLimite.getSelectionModel().select(0);
	}
	
	/**
	 * <p>Prende il seggiolino in posizione 2 e mette i lsuo prezzo.</p>
	 * @author francesco
	 *
	 */
	private class ItemChoiceSelected implements ChangeListener<Integer>{

		@Override
		public void changed(ObservableValue<? extends Integer> observable,
				Integer oldValue, Integer newValue) {
			lblprezzoOptAuto.setText(seggiolini.get(newValue-1).getPrezzo() + " €");
		}
	}
	/*****  GUIDATORE **/
	@FXML
	protected  TextField txtNome;
	@FXML
	protected TextField txtCognome;
	@FXML
	protected TextField txtIndirizzo;
	@FXML
	protected TextField txtCodFiscale;
	@FXML
	protected TextField txtPatente;
	
	protected void impostaFalsoTxtGuidatore(){
			txtNome.setDisable(true);
			txtCognome.setDisable(true);
			txtIndirizzo.setDisable(true);
			txtCodFiscale.setDisable(true);
			txtPatente.setDisable(true);
		}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);

		bindingValuesRestituzione();
		bindingValuesOptional();
		bindingValuesAutoveicolo();
		bindingValuesContratto();
		bindingValuesCartaCredito();
		//bindingValuesGuidatore();

		inizializzaToggleButton();
		inizializzaTabelleOptional();
		try {
			inizializzaChoiceBox();
			caricaTabella((List<T>)presenter.processRequest("getAllSedi",null), tbRestituzione);
			inizializzaTabellaAutoveicolo();
			caricaTabella((List<T>)presenter.processRequest("getAllContratti",null), tbContratto);
			tbContratto.getSelectionModel().selectedItemProperty().addListener(new ItemSelected());
			tbOptionalNoleggio.getSelectionModel().selectedItemProperty().addListener(new ItemSelected());
			tbOptionalAuto.getSelectionModel().selectedItemProperty().addListener(new ItemSelected());
			tbOptionalScelti.getSelectionModel().selectedItemProperty().addListener(new ItemSelected());
			choiceSeggiolini.getSelectionModel().selectedItemProperty().addListener(new ItemChoiceSelected());
			impostaFalsoTxtGuidatore();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			e.printStackTrace();
		}
	}
	
	
}
