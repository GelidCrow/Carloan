package presentation.mvp.view.controller.generale.autoveicolo;

import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.manutenzione.*;
import business.model.Exception.CommonException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;

public class Nuova_Manutenzione extends Schermata{
	@FXML
	RadioButton radio_ord;
	@FXML
	RadioButton radio_stra;
	@FXML
	TextArea motivo;
	@FXML
	DatePicker data_inizio;
	@FXML
	ImageView immagine;
	@FXML
	Label targa;
	@FXML
	Label modello;
	private Autoveicolo a=null;
	private Manutenzione man;
public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		radio_ord.setSelected(true);
		radio_stra.setSelected(false);
}
public void initData(Entity x){
	this.a=(Autoveicolo)x;
	immagine.setImage(new Image(a.getImmagine_stream()));
	targa.setText(a.getTarga());
	modello.setText(a.getModello());
}

@FXML
public void btnannulla(ActionEvent e){
	Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
	if(result.isPresent() && result.get() == ButtonType.OK)
		this.chiudiFinestra();
}

 
@FXML
public void btnconferma(ActionEvent e){
	try {
		this.man=prendiDatiDaView();
		
	} catch (CommonException e1) {
		// TODO Auto-generated catch block
		e1.showMessage();
	}
	
	
}
private Manutenzione prendiDatiDaView() throws CommonException{
	LocalDate d=data_inizio.getValue();
	if(d==null)
		throw new CommonException("La data d'inizio � vuota");
	else if(d.isBefore(LocalDate.now()))
		throw new CommonException("La data d'inizio � passata");
	
	if(radio_ord.isSelected())
		return new ManutenzioneOrdinaria(null,d , null, motivo.getText());
	else
		return new ManutenzioneStraordinaria(null, d, null, motivo.getText());
}
}
