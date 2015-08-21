package presentation.mvp.view.controller.generale.autoveicolo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import Errori.AlertView;
import business.entity.Auto.Fascia.Fascia;
import business.model.Exception.CommonException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;


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
	protected ChoiceBox<String> Disponibilita;
	
	@FXML
	protected ImageView vistaimmagine;
	
	protected InputStream immagine;//dal bottone [...]
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
	@FXML
	protected Label descrizione_fascia;
	
	private LinkedList<Fascia> fasce;
	
	@SuppressWarnings("unchecked")
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		try {
		FXMLParameter = new ParametriFXML(null,false);
		btnchoose.setTooltip(new Tooltip("Clicca e scegli l'immagine"));
		nposti.setItems(FXCollections.observableArrayList(3,5,6,7,9));
		nposti.getSelectionModel().selectFirst();
		Disponibilita.setItems(FXCollections.observableArrayList("Disponibile","NonDisponibile","ManutenzioneOrdinaria","ManutenzioneStraordinaria"));
		Disponibilita.getSelectionModel().selectFirst();
		
		 fasce=(LinkedList<Fascia>) presenter.processRequest("getAllFasce", null);
		 LinkedList<String> temp=new LinkedList<String>();
		 for(Fascia f:fasce)
			 temp.add(f.getNome());
		 fascia.setItems(FXCollections.observableArrayList(temp));
		 fascia.getSelectionModel().selectedIndexProperty().addListener(new Choiceboxlistener());
		 fascia.getSelectionModel().selectFirst();
		
		 
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			AlertView.getAlertView("File :Interfacce.xml corrotto",AlertType.WARNING);
		}
	}
	class Choiceboxlistener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable,Number oldValue, Number newValue) {
		descrizione_fascia.setText(fasce.get((int)newValue).getDescrizione());
		}
		
	}
	
	@FXML
	public void btnchooseclick(ActionEvent e){

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Scegli l'immagine");
		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
		File f=fileChooser.showOpenDialog(stage);
		try {
			this.immagine=new FileInputStream(f);
		} catch (FileNotFoundException e1) {
		}
		vistaimmagine.setImage(new Image(this.immagine));
	}
	
	
	
	
}