package presentation.mvp.view.controller.generale.noleggio;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import presentation.mvp.view.controller.Schermata;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Danni;
import business.entity.Auto.Disponibilita;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.Optional.ChilometraggioIllimitato;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.OptionalNoleggio;
import business.entity.pagamento.Pagamento;
import business.model.Exception.CommonException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ChiudiNoleggio extends Schermata{
	@FXML
	private TextField txtKmRientro;
	@FXML
	private DatePicker dRientro; 
	@FXML
	private TextArea textAreaNote;
	@FXML
	private TextArea textAreaDGravi;
	@FXML
	private TextArea textAreaDFutili;
	@FXML
	private TextField txtTotDanni;
	@FXML
	private Label lblNumGiorni;
	@FXML
	private Label lblCostoGiorni;
	@FXML
	private Label lblNumKilometri;
	@FXML
	private Label lblCostoKilometri;
	@FXML
	private Label lblCauzione;
	@FXML
	private Label lblTotChiusura;
	@FXML
	private Label lblTotKm;
	private Noleggio noleggio;
	private Autoveicolo auto;
	private Pagamento pagamento;
	private float importoDanni;
	private int numGiorni;
	private static final float PERCENTUALE_GIORNIPIU= 02;
	private float  quantitaSingoloGiorno;
	private Fascia fascia;
	private boolean KIllimitato=false;
	@FXML
	public void btnIndietro(ActionEvent e){
	 java.util.Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Se inseriti,perderai tutti i dati ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	
	@Override
	public void initData(Entity entity){
			noleggio=(Noleggio)entity;
			try {
				auto= (Autoveicolo) presenter.processRequest("letturaAutoveicolo",noleggio.getIdAuto());
				pagamento= (Pagamento)presenter.processRequest("letturaPagamento",noleggio.getIdPagamento());
				fascia=  (Fascia)presenter.processRequest("letturaFascia",auto.getFascia());
				lblCostoKilometri.setText(String.valueOf(fascia.getCosto_kilometrico()));
				textAreaDFutili.setText(auto.getDanni().getDanniFutili());
				lblCauzione.setText(String.valueOf(pagamento.getDepositoCauzinale()));				
				dRientro.setValue(noleggio.getFineNoleggio());
				numGiorni= (int) noleggio.getFineNoleggio().until(LocalDate.now(), ChronoUnit.DAYS);
				lblNumGiorni.setText(String.valueOf(numGiorni));
				
				// CONTOLO CHE NON ABBIA IL KILOMETRAGGIO ILLIMITATO
				@SuppressWarnings("unchecked")
				List<Optional> optional= (List<Optional>)presenter.processRequest("getAllOptionalByNoleggio",noleggio.getIDNoleggio());
				for(Optional op: optional){
					if(op instanceof ChilometraggioIllimitato){
						KIllimitato=true;
						break;
					}
				}
				
				setCostoGiorni();
		
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private float totaleImportoGiorni=0;
	private void setCostoGiorni(){
		quantitaSingoloGiorno= (pagamento.getAcconto()*PERCENTUALE_GIORNIPIU)/100;
		totaleImportoGiorni=  numGiorni*quantitaSingoloGiorno;
		lblCostoGiorni.setText(String.valueOf(totaleImportoGiorni));
	}
	@FXML
	public void dRientroAction(ActionEvent e){
		if (dRientro.getValue().isBefore(noleggio.getFineNoleggio())){
			dRientro.setValue(noleggio.getFineNoleggio());
			numGiorni= (int) noleggio.getFineNoleggio().until(dRientro.getValue(), ChronoUnit.DAYS);
			lblNumGiorni.setText(String.valueOf(noleggio.getFineNoleggio().until(dRientro.getValue(), ChronoUnit.DAYS)));
			setCostoGiorni();
			}
		else{
			numGiorni= (int) noleggio.getFineNoleggio().until(dRientro.getValue(), ChronoUnit.DAYS);
			lblNumGiorni.setText(String.valueOf(noleggio.getFineNoleggio().until(dRientro.getValue(), ChronoUnit.DAYS)));
			setCostoGiorni();
		}

	}
	private float totaleCostoKmAggiuntivo=0;
	private void setSezioneKm(){
		int sommaKmMax= auto.getUltimoKm()+noleggio.getNumeroChilometri();
		int numKilometriRientro= Integer.parseInt(txtKmRientro.getText());
		if(numKilometriRientro>sommaKmMax){
			int kmInPiu= numKilometriRientro-sommaKmMax;
			lblNumKilometri.setText(String.valueOf(kmInPiu));
			totaleCostoKmAggiuntivo= fascia.getCosto_kilometrico()*kmInPiu;
			lblTotKm.setText(String.valueOf(totaleCostoKmAggiuntivo));
		}
	}
	@FXML
	public void btnConferma(ActionEvent e){
		
		try {
//			kmbase+quelli da fare scelti ;
//		se kmfatti < somma di quelli allora ok
//		altrimenti fai la differenza e metti quelli in piu fatti e calcola il prezzo per ogni km fatto in piu.
			if(!KIllimitato)
				setSezioneKm();
			prendiDatiDaView();
			calcolaImportoFinale();
			
			//se danni gravi sono pieni � da manutenere.
			//fai aggiorna auto.+ aggiorna i km che adesso ha l'auto
		} catch (CommonException e1) {
			// TODO Auto-generated catch block
			e1.showMessage();
		}
	}
	
	private void prendiDatiDaView() throws CommonException{
		try{
			if(txtKmRientro.getText().isEmpty()){
				throw new CommonException("Devi inserire i km dell'auto prima di procedere");
			}
			int kmR=(Integer.parseInt(txtKmRientro.getText()));
			noleggio.setKmRientro(kmR);
			noleggio.setRientro(dRientro.getValue());
			noleggio.setNote(textAreaNote.getText());
			auto.setUltimoKm(kmR);
			if(!textAreaDGravi.getText().isEmpty())
				auto.setDisponibilita(Disponibilita.DaManutenere);
			auto.setDanni(new Danni(textAreaDFutili.getText(),textAreaDGravi.getText()));
			if(!txtTotDanni.getText().isEmpty())
				importoDanni = Float.parseFloat(txtTotDanni.getText());
	}
	catch(NumberFormatException e){
		throw new CommonException("La casella dei kilometri oppure quella del totale delle multe contengono caratterei alfabetici ");
	}
	}
	
	private float calcolaImportoFinale(){
		float totaleAggiunto= importoDanni+totaleCostoKmAggiuntivo+totaleImportoGiorni;
		float cauzione= pagamento.getDepositoCauzinale();
		if(cauzione>totaleAggiunto)
			return cauzione-totaleAggiunto;
		else 
			return totaleAggiunto-cauzione;
	}
}
