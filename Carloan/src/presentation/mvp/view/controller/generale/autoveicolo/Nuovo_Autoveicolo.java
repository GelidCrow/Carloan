package presentation.mvp.view.controller.generale.autoveicolo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Danni;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
	protected TextArea optional_auto;
	
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
		if(targa.getText().isEmpty() || modello.getText().isEmpty() || marca.getText().isEmpty() || immatricolazione.getValue()==null || prezzo.getText().isEmpty())
			throw new CommonException("I campi obbligatori non devono essere vuoti!");
		String s=targa.getText();
		if(s.isEmpty())
			throw new CommonException("La targa è vuota");
		else
			temp.setTarga(s);
		
		s=marca.getText();
		if(s.isEmpty())
			throw new CommonException("La marca è vuota");
		else
				temp.setMarca(s);
		s=modello.getText();
		if(s.isEmpty())
		throw new CommonException("Il modello è vuoto");
		 else
			temp.setModello(s);
		s=alimprinc.getText();
		if(s.isEmpty())
		throw new CommonException("L'alimentazione principale è vuota");
		 else
			temp.setAlimPrincipale(s);
		LocalDate d=immatricolazione.getValue();
		if(d==null)
		throw new CommonException("Data immatricolazione vuota");
		 else
			 temp.setImmatricolazione(d);
		
		try{
			temp.setCilindrata(Integer.parseInt(cilindrata.getText()));
			}
			catch(NumberFormatException e){
				throw new CommonException("Cilindrata non valida");
			}
		try{
			 s=kmpercorsi.getText();
			if(s.isEmpty())
				temp.setUltimoKm(0);
			else
				temp.setUltimoKm(Integer.parseInt(s));
		}
		catch(NumberFormatException e){
			throw new CommonException("Kilometri percorsi non validi");
		}
		try{
			s=potenza.getText();
			if(s.isEmpty())
				temp.setPotenza(0);
			else
			temp.setPotenza(Integer.parseInt(potenza.getText()));
			}
			catch(NumberFormatException e){
				throw new CommonException("Potenza non valida");
		}
		try{
			 s=capienza.getText();
			if(s.isEmpty())
				temp.setCapPortaBagnagli(0);
			else
		temp.setCapPortaBagnagli(Integer.parseInt(s));
		}
		catch(NumberFormatException e){
			throw new CommonException("Capienza non valida");
		}
		
			s=numtelaio.getText();
			if(s.isEmpty())
				throw new CommonException("Il numero del telaio non può essere vuoto");
			else
			temp.setNroTelaio(s);
		
	temp.setAlimSec(alimsec.getText());
	temp.setColore(colore.getText());
	temp.setCambio(cambio.getSelectionModel().getSelectedItem());
	
	temp.setNroPosti(nposti.getSelectionModel().getSelectedItem());
	
	String disp=Disponibilita.getSelectionModel().getSelectedItem();
	switch(disp){
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
	temp.setImmagine(immagine);
	d=scadenzaass.getValue();
	if(d==null)
		throw new CommonException("Data scadenza assicurazione vuota");
	else
	temp.setDataScadAssic(d);
		
	s=fascia.getSelectionModel().getSelectedItem();
	for(Fascia f : fasce){
		if(f.getNome().equals(s)){
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
	Sede se=(Sede)tablesedi.getSelectionModel().getSelectedItem();
	temp.setCodiceSedDisp(se.getIDSede());
	s=optional_auto.getText();
	if(s.isEmpty())
		temp.setOptionalAuto("");
	else
		temp.setOptionalAuto(s);
	
	return temp;
	}
	
	
	@FXML
	public void btnannulla_click(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
}