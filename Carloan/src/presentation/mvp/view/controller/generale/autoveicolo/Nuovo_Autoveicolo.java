package presentation.mvp.view.controller.generale.autoveicolo;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import presentation.mvp.view.controller.Schermata;


public class Nuovo_Autoveicolo extends Schermata{
	@FXML
	protected TextField targa;
	@FXML
	protected TextField marca;
	@FXML
	protected TextField modello;
	@FXML
	protected TextField alimprinc;
	@FXML
	protected TextField alimsec;
	@FXML
	protected TextField colore;
	@FXML
	protected TextField cambio;
	@FXML
	protected TextField cilindrata;
	@FXML
	protected TextField potenza;
	@FXML
	protected TextField numtelaio;
	@FXML
	protected TextField kmpercorsi;
	@FXML
	protected TextField capienza;
	@FXML
	protected TextField prezzo;
	@FXML
	protected DatePicker immatricolazione;
	@FXML
	protected ChoiceBox<Integer> nposti;
	@FXML
	protected ChoiceBox<String> Disponibilità;
	@FXML
	protected File immagine;//dal bottone [...]
	@FXML
	protected DatePicker scadenzaass;
	@FXML
	protected ChoiceBox<String> fascia;
	@FXML
	protected TextArea danni_futili;
	@FXML
	protected TextArea danni_gravi;
	@FXML
	protected Button btnchoose;
	@FXML
	protected Button btnconferma;
	@FXML
	protected Button btnannulla;
	
	
	
	
	
	
	
	
}