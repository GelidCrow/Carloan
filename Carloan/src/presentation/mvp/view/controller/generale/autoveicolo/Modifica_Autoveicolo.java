package presentation.mvp.view.controller.generale.autoveicolo;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;

public class Modifica_Autoveicolo extends Nuovo_Autoveicolo{
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
	
	protected String immagine_path;//dal bottone [...]
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
	protected TextArea Note;
	@FXML
	protected Button btnchoose;
	@FXML
	protected Button btnconferma;
	@FXML
	protected Button btnannulla;
	@FXML
	protected Label descrizione_fascia;
	
	private LinkedList<Fascia> fasce;
	private ArrayList<Sede> sedi;
	public void initialize(URL arg0, ResourceBundle arg1) {
	presenter=new Presenter();
	FXMLParameter=new ParametriFXML(null, false);
	}
	@SuppressWarnings("unchecked")
	public void initData(Entity entity){
		Autoveicolo a=(Autoveicolo)entity;
		try {
		this.btnchoose.setTooltip(new Tooltip("Clicca e scegli l'immagine"));
		/*Numero posti*/
		ObservableList<Integer> list=FXCollections.observableArrayList(3,5,6,7,9);
		this.nposti.setItems(list);
		this.nposti.getSelectionModel().select(list.indexOf(a.getNroPosti()));
		//Disponibilita
		ObservableList<String> lista=FXCollections.observableArrayList("Disponibile","NonDisponibile","ManutenzioneOrdinaria","ManutenzioneStraordinaria");
		this.Disponibilita.setItems(lista);
		this.Disponibilita.getSelectionModel().select(lista.indexOf(a.getDisponibilita().toString()));
		//Fasce
		this.fasce=(LinkedList<Fascia>) presenter.processRequest("getAllFasce", null);
		 LinkedList<String> temp=new LinkedList<String>();
		 int i=0;
		 for(int j=0;j<this.fasce.size();j++){
			 temp.add(this.fasce.get(j).getNome());
			 int elimina=a.getFascia();
			 Fascia f=this.fasce.get(j);
			 if(f.getIDFascia()==elimina)
				 i=j;
				 
		 }
		 lista=FXCollections.observableArrayList(temp);
		 this.fascia.setItems(lista);
		 this.fascia.getSelectionModel().selectedIndexProperty().addListener(new Choiceboxlistener());
		 this.fascia.getSelectionModel().select(i);
		 //Cambio
		 this.cambio.setItems(FXCollections.observableArrayList("Manuale","Automatico"));
		 String s=a.getCambio();
		 if(s.equals("Manuale"))
			 this.cambio.getSelectionModel().select(0);
		 else
			 this.cambio.getSelectionModel().select(1);
		 //Sedi
		 sedi=(ArrayList<Sede>) presenter.processRequest("getAllSedi", null);
		 nome.setCellValueFactory(cellData ->  new  SimpleStringProperty(((Sede) cellData.getValue()).getNome()));
		 indirizzo.setCellValueFactory(cellData ->  new  SimpleStringProperty(((Sede) cellData.getValue()).getIndirizzo()));
		 identifier.setCellValueFactory(cellData ->new ReadOnlyObjectWrapper<Integer>(((Sede)cellData.getValue()).getIDSede()));
		 ObservableList<Sede> obsList= FXCollections.observableList(sedi);
		 tablesedi.setItems(obsList);
		 for(int j=0;j<obsList.size();j++){
			 if(obsList.get(j).getIDSede()==a.getCodiceSedDisp()){
				 tablesedi.getSelectionModel().select(j);
				 break;
			 }
		 }
		 targa.setText(a.getTarga());
		 marca.setText(a.getMarca());
		 modello.setText(a.getModello());;
		 alimprinc.setText(a.getAlimPrincipale());
		 alimsec.setText(a.getAlimSec());
		 colore.setText(a.getColore());
		 immatricolazione.setValue(a.getImmatricolazione());
		 cilindrata.setText(String.valueOf(a.getCilindrata()));
		 potenza.setText(String.valueOf(a.getPotenza()));
		 numtelaio.setText(a.getNroTelaio());
		 kmpercorsi.setText(String.valueOf(a.getUltimoKm()));
		 capienza.setText(String.valueOf(a.getCapPortaBagnagli()));
		 scadenzaass.setValue(a.getDataScadAssic());
		 prezzo.setText(String.valueOf(a.getPrezzo()));
		 optional_auto.setText(a.getOptionalAuto());
		 danni_futili.setText(a.getDanni().getDanniFutili());
		 danni_gravi.setText(a.getDanni().getDanniGravi());
		 Note.setText(a.getNote());
		 vistaimmagine.setImage(new Image(a.getImmagine_stream()));
		 
		} 
		 catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 }
	
	class Choiceboxlistener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable,Number oldValue, Number newValue) {
		descrizione_fascia.setText(fasce.get((int)newValue).getDescrizione());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	public void btnconferma_click(ActionEvent e){
		SchermataGenerale<Autoveicolo> schermataGenerale = (SchermataGenerale<Autoveicolo>)this.getChiamante();
		tw= ((SchermataGenerale<Autoveicolo>)schermataGenerale).getTable("Autoveicolo");
		try {
			Autoveicolo auto_da_aggiornare=prendiDatiDaView();
			presenter.processRequest("VerificaAutoveicolo", auto_da_aggiornare);
			presenter.processRequest("AggiornamentoAutoveicolo", auto_da_aggiornare);
			schermataGenerale.setAggiornando(true);
			caricaTabella((List<Autoveicolo>)presenter.processRequest("getAllAuto",null));
			schermataGenerale.setAggiornando(false);
			chiudiFinestra();
		} 
		catch(CommonException e1){
			e1.showMessage();
		}
		catch(InvocationTargetException e1){
			new CommonException(e1.getTargetException().getMessage()).showMessage();
		}
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException e1) {
			e1.printStackTrace();
		}
	}
	
}
