package presentation.mvp.view.controller.generale.noleggio;

import business.entity.Luoghi.Sede;
import business.entity.Noleggio.Optional.Guidatore;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.Seggiolino;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import presentation.mvp.view.controller.Schermata;

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
	
	
	
	
	
	
	
	
	
}
