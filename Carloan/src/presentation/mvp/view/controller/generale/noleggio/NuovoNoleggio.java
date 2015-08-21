package presentation.mvp.view.controller.generale.noleggio;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Luoghi.Sede;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.Optional.Guidatore;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.Seggiolino;
import business.model.Exception.CommonException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;

public class NuovoNoleggio extends Schermata{
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
	private TableView<Sede> tbRestituzione;
	@FXML
	private TableView<Optional> tbOptional;	
	@FXML
	private TableView<Optional> tbOptionalScelti;
	@FXML
	private ChoiceBox<Seggiolino> choiceSeggiolini;
	
	/*****  GUIDATORE **/
	@FXML
	private TextField txtLimCopertura;
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
	private Button btnAggiungi;
	@FXML
	private Button btnRimuovi;
	@FXML
	private TableView<Guidatore> tbGuidatori;
	@FXML
	private TableView<Guidatore> tbCartaCredito;
	@FXML
	private TableView<Guidatore> tbAutoveicolo;
	@FXML
	private TableView<Guidatore> tbContratto;
	@FXML
	private ChoiceBox<Fascia> choiceFascia;
	@FXML
	private TextField txtImporto;
	@FXML
	private TableView<Guidatore> txtCauzione;
	@FXML
	private TableView<Guidatore> txtTotale;
	@FXML
	private RadioButton rdDenaro;
	@FXML
	private RadioButton rdCartaCredito;
	
	final ToggleGroup group = new ToggleGroup();
	
	private ObservableList<TableColumn<Sede,?>> restituzione;
	
	public void bindingValuesRestituzione(){
		restituzione.get(0).setCellFactory(cellData -> new SimpleIntegerProperty(((Sede)cellData.getValue()).getIDSede()));
	}
	
	private void inizializzaToggleButton(){
		rdDenaro.setToggleGroup(group);
		rdCartaCredito.setToggleGroup(group);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		bindingValuesRestituzione();
		inizializzaToggleButton();
		

	}

}
