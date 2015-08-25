package presentation.mvp.view.controller.generale.noleggio;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
import business.entity.Noleggio.Optional.Guidatore;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
	private TableView<T> tbOptionalNoleggio;	
	@FXML
	private TableView<T> tbOptionalScelti;
	@FXML
	private ChoiceBox<Integer> choiceSeggiolini;
	@FXML
	private ChoiceBox<Integer> choiceLimite;
	/***
	 * Cliente
	 */
	@FXML
	private Label lblCodFiscale;
	@FXML
	private Label lblNome;
	@FXML
	private Label lblCognome;
	
	/*****  GUIDATORE **/
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCognome;
	@FXML
	private TextField txtIndirizzo;
	@FXML
	private TextField txtCodFiscale;
	@FXML
	private TextField txtPatente;

	@FXML
	private TableView<T> tbGuidatori;
	@FXML
	private TableView<T> tbCartaCredito;
	@FXML
	private TableView<T> tbAutoveicolo;
	@FXML
	private TableView<T> tbContratto;
	@FXML
	private TableView<T> tbOptionalAuto;
	@FXML
	private ChoiceBox<Fascia> choiceFascia;
	@FXML
	private TextField txtImporto;
	@FXML
	private TextField txtCauzione;
	@FXML
	private TextField txtTotale;
	@FXML
	private RadioButton rdDenaro;
	@FXML
	private RadioButton rdCartaCredito;
	@FXML
	private RadioButton rdIllimitato;
	@FXML
	private RadioButton rdLimitato;
	
	final ToggleGroup group = new ToggleGroup();

	final ToggleGroup group2 = new ToggleGroup();
	
	
	private ObservableList<TableColumn<Sede,?>> restituzione;

	private ObservableList<TableColumn<Optional,?>> optionalNoleggio;

	private ObservableList<TableColumn<Optional,?>> optionalAuto;

	private ObservableList<TableColumn<Optional,?>> optionalScelti;

	private ObservableList<TableColumn<Guidatore,?>> guidatori;
	
	private ObservableList<TableColumn<Autoveicolo,?>> autoveicoli;

	private ObservableList<TableColumn<CartaDiCredito,?>> carteDiCredito;

	private ObservableList<TableColumn<Contratto,?>> contratti;

	
	private void bindingValuesCartaCredito(){
		carteDiCredito.get(0).setCellValueFactory(cellData ->  new SimpleStringProperty(((CartaDiCredito) cellData.getValue()).getIBAN()));
		carteDiCredito.get(1).setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((CartaDiCredito) cellData.getValue()).getDataScadenza()));
		carteDiCredito.get(2).setCellValueFactory(cellData ->  new SimpleStringProperty(((CartaDiCredito) cellData.getValue()).getCircuito()));
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
	private void bindingValuesContratto(){
		contratti.get(0).setCellValueFactory(cellData ->  new SimpleIntegerProperty(((Contratto) cellData.getValue()).getIDContratto()));
		contratti.get(1).setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((Contratto) cellData.getValue()).getDataCreazione()));
	}
	
	private void bindingValuesGuidatore(){
		guidatori.get(0).setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getCodFiscale()));
		guidatori.get(1).setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getNome()+((Guidatore) cellData.getValue()).getCognome()));
	}
	
	private void bindingValuesOptional(){
		optionalNoleggio.get(0).setCellValueFactory(cellData ->  new SimpleIntegerProperty(((Optional) cellData.getValue()).getId()));
		optionalNoleggio.get(1).setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getDescrizione()));
		optionalNoleggio.get(2).setCellValueFactory(cellData ->  new SimpleFloatProperty(((Optional) cellData.getValue()).getPrezzo()));
		

		optionalAuto.get(0).setCellValueFactory(cellData ->  new SimpleIntegerProperty(((Optional) cellData.getValue()).getId()));
		optionalAuto.get(1).setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getDescrizione()));
		optionalAuto.get(2).setCellValueFactory(cellData ->  new SimpleFloatProperty(((Optional) cellData.getValue()).getPrezzo()));
		
		optionalScelti.get(0).setCellValueFactory(cellData ->  new SimpleIntegerProperty(((Optional) cellData.getValue()).getId()));
		optionalScelti.get(1).setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getDescrizione()));
		optionalScelti.get(2).setCellValueFactory(cellData ->  new SimpleFloatProperty(((Optional) cellData.getValue()).getPrezzo()));
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
	
	
	
	
	private void inizializzaToggleButton(){
		rdDenaro.setToggleGroup(group);
		rdCartaCredito.setToggleGroup(group);
		rdIllimitato.setToggleGroup(group2);
		rdLimitato.setToggleGroup(group2);
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
	
	
	@SuppressWarnings("unchecked")
	private void inizializzaTabelleOptional(){
		List<Optional> optional;
		try {
			optional = (List<Optional>)presenter.processRequest("getAllOptional",null);
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
			caricaTabella((List<T>) optAuto, tbOptionalNoleggio);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * <p>Fa visualizzare solo le auto opportune in base all'utente corrente</p>
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);

		bindingValuesRestituzione();
		bindingValuesAutoveicolo();
		//bindingValuesOptional();
		//bindingValuesGuidatore();
		//bindingValuesContratto();
		//bindingValuesCartaCredito();

		inizializzaToggleButton();
	//	inizializzaTabelleOptional();
		try {

			caricaTabella((List<T>)presenter.processRequest("getAllSedi",null), tbRestituzione);
			inizializzaTabellaAutoveicolo();
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			e.printStackTrace();
		}
	}
}
