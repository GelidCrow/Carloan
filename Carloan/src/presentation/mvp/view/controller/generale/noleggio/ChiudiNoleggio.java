package presentation.mvp.view.controller.generale.noleggio;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import presentation.mvp.view.controller.Schermata;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Danni;
import business.entity.Noleggio.Noleggio;
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
	private Noleggio noleggio;
	private Autoveicolo auto;
	private Pagamento pagamento;
	private float importoDanni;
	private int numGiorni;
	private static final double PERCENTUALE_GIORNIPIU= 0.02;
	private double quantitaSingoloGiorno;
	@FXML
	public void btnIndietro(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Se inseriti,perderai tutti i dati ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	
	@Override
	public void initData(Entity entity){
			noleggio=(Noleggio)entity;
			try {
				auto= (Autoveicolo) presenter.processRequest("letturaAutoveicolo",noleggio.getIdAuto());
				pagamento= (Pagamento)presenter.processRequest("letturaPagamento",noleggio.getIdPagamento());
				textAreaDFutili.setText(auto.getDanni().getDanniFutili());
				lblCauzione.setText(String.valueOf(pagamento.getDepositoCauzinale()));				
				dRientro.setValue(noleggio.getFineNoleggio());
				numGiorni= (int) noleggio.getFineNoleggio().until(LocalDate.now(), ChronoUnit.DAYS);
				lblNumGiorni.setText(String.valueOf(numGiorni));
				
				setCostoGiorni();
		
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void setCostoGiorni(){
		quantitaSingoloGiorno= (pagamento.getAcconto()*PERCENTUALE_GIORNIPIU)/100;
		double totaleImportoGiorni=  numGiorni*quantitaSingoloGiorno;
		lblCostoGiorni.setText(String.valueOf(totaleImportoGiorni));
	}
	@FXML
	public void dRientroAction(ActionEvent e){
		if (dRientro.getValue().isBefore(noleggio.getFineNoleggio())){
			dRientro.setValue(noleggio.getFineNoleggio());}
		else{
			lblNumGiorni.setText(String.valueOf(noleggio.getFineNoleggio().until(dRientro.getValue(), ChronoUnit.DAYS)));
			setCostoGiorni();
		}

	}
	@FXML
	public void btnConferma(ActionEvent e){
		
		try {
			prendiDatiDaView();
		} catch (CommonException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	private void prendiDatiDaView() throws CommonException{
		try{
			int kmR=(Integer.parseInt(txtKmRientro.getText()));
			noleggio.setKmRientro(kmR);
			noleggio.setRientro(dRientro.getValue());
			noleggio.setNote(textAreaNote.getText());
			auto.setDanni(new Danni(textAreaDFutili.getText(),textAreaDGravi.getText()));
			importoDanni = Float.parseFloat(txtTotDanni.getText());
			
	}
	catch(NumberFormatException e){
		throw new CommonException("La caseella ");
	}
	}
	
}
