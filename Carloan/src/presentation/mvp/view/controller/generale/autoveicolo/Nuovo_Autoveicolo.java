package presentation.mvp.view.controller.generale.autoveicolo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.entity.UtenteCorrente;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Danni;
import business.entity.Auto.Disponibilita;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import presentation.mvp.view.controller.generale.SchermataGenerale;
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
	protected ChoiceBox<String> cambio;
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
	@SuppressWarnings("rawtypes")
	
	@FXML
	protected TableView tablesedi;
	@FXML
	protected  TableColumn<Sede, Integer> identifier;
	@FXML
	protected  TableColumn<Sede, String> nome;
	@FXML
	protected  TableColumn<Sede, String> indirizzo;
	
	
	private LinkedList<Fascia> fasce;
	private ArrayList<Sede> sedi;
	private TableView<Autoveicolo> tw;
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
		 cambio.setItems(FXCollections.observableArrayList("Manuale","Automatico"));
		 cambio.getSelectionModel().selectFirst();
		 sedi=(ArrayList<Sede>) presenter.processRequest("getAllSedi", null);
		 nome.setCellValueFactory(cellData ->  new  SimpleStringProperty(((Sede) cellData.getValue()).getNome()));
		 indirizzo.setCellValueFactory(cellData ->  new  SimpleStringProperty(((Sede) cellData.getValue()).getIndirizzo()));
		 identifier.setCellValueFactory(cellData ->new ReadOnlyObjectWrapper<Integer>(((Sede)cellData.getValue()).getIDSede()));
		 ObservableList<Sede> obsList= FXCollections.observableList(sedi);
		 tablesedi.setItems(obsList);
			
		}
			catch (InstantiationException | IllegalAccessException
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
		if(f!=null){
		try {
			this.immagine=new FileInputStream(f);
		} catch (FileNotFoundException e1) {
		}
		vistaimmagine.setImage(new Image(this.immagine));
		}
	}
	
	@FXML
	public void btnconferma_click(ActionEvent e){
		tw=  ((SchermataGenerale<Autoveicolo>)this.getChiamante()).getTable("Autoveicolo");
		
		try {
			Autoveicolo auto_da_inserire=prendiDatiDaView();
			presenter.processRequest("VerificaAutoveicolo", auto_da_inserire);
			presenter.processRequest("InserimentoAutoveicolo", auto_da_inserire);
			//agggiustare tabella autossss
		} 
		catch(CommonException e1){
			e1.showMessage();
		}
		
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException  e1) {
			
			e1.printStackTrace();
		}
	}

	
	
	
	private Autoveicolo prendiDatiDaView() throws CommonException {
	Autoveicolo temp=new Autoveicolo();
	temp.setTarga(targa.getText());
	temp.setMarca(marca.getText());
	temp.setModello(modello.getText());
	temp.setAlimPrincipale(alimprinc.getText());
	temp.setAlimSec(alimsec.getText());
	temp.setColore(colore.getText());
	temp.setCambio(cambio.getSelectionModel().getSelectedItem());
	temp.setImmatricolazione(immatricolazione.getValue());
	try{
	temp.setCilindrata(Integer.parseInt(cilindrata.getText()));
	}
	catch(NumberFormatException e){
		throw new CommonException("Cilindrata non valida");
	}
	try{
	temp.setPotenza(Integer.parseInt(potenza.getText()));
	}
	catch(NumberFormatException e){
		throw new CommonException("Potenza non valida");
	}
	temp.setNroPosti(nposti.getSelectionModel().getSelectedItem());
	temp.setNroTelaio(numtelaio.getText());
	String d=Disponibilita.getSelectionModel().getSelectedItem();
	switch(d){
	case "Disponibile":
		temp.setDisponibilita(business.entity.Auto.Disponibilita.Disponibile);
		break;
	case "NonDisponibile":
		temp.setDisponibilita(business.entity.Auto.Disponibilita.NonDisponibile);
		break;
	case "ManutenzioneOrdinaria":
		temp.setDisponibilita(business.entity.Auto.Disponibilita.ManutenzioneOrdinaria);
		break;
	case "ManutenzioneStraordinaria":
		temp.setDisponibilita(business.entity.Auto.Disponibilita.ManutenzioneStraordinaria);
		break;
	}
	try{
		String s=kmpercorsi.getText();
		if(s.isEmpty())
			temp.setUltimoKm(0);
		else
			temp.setUltimoKm(Integer.parseInt(s));
	}
	catch(NumberFormatException e){
		throw new CommonException("Kilometri percorsi non validi");
	}
	try{
		String s=capienza.getText();
		if(s.isEmpty())
			temp.setCapPortaBagnagli(0);
		else
	temp.setCapPortaBagnagli(Integer.parseInt(s));
	}
	catch(NumberFormatException e){
		throw new CommonException("Capienza non valida");
	}
	temp.setImmagine(immagine);
	temp.setDataScadAssic(scadenzaass.getValue());
	d=fascia.getSelectionModel().getSelectedItem();
	for(Fascia f : fasce){
		if(f.getNome().equals(d)){
			temp.setFascia(f);
			break;
		}
	}
	temp.setDanni(new Danni(danni_futili.getText(), danni_gravi.getText()));
	try{
	temp.setPrezzo(Float.parseFloat(prezzo.getText()));
	}
	catch(NumberFormatException e){
		throw new CommonException("Prezzo non valido");
	}
	//temp.setCodiceSedDisp(UtenteCorrente.getUtente().);
	return temp;
	}
	
	
}