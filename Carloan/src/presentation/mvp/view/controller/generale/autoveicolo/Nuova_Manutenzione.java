package presentation.mvp.view.controller.generale.autoveicolo;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
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
	final ToggleGroup group = new ToggleGroup();
	protected TableView<Autoveicolo> tw;
public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		radio_ord.setSelected(true);
		radio_stra.setSelected(false);
		radio_ord.setToggleGroup(group);
		radio_stra.setToggleGroup(group);
		
}
public void initData(Entity x){
	this.a=(Autoveicolo)x;
	InputStream i=a.getImmagine_stream();
	if(i!=null)
	immagine.setImage(new Image(i));
	
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

	SchermataGenerale<Autoveicolo> schermataGenerale = (SchermataGenerale<Autoveicolo>)this.getChiamante();
	tw= ((SchermataGenerale<Autoveicolo>)schermataGenerale).getTable("Autoveicolo");
		try {
			this.man=prendiDatiDaView();
			presenter.processRequest("VerificaManutenzione", this.man);
			presenter.processRequest("InserimentoManutenzione",this.man);
			schermataGenerale.caricaTabella((List<Autoveicolo>)presenter.processRequest("getAllAuto",null), tw);
			chiudiFinestra();
		}
		
		 catch (CommonException e1) {
			// TODO Auto-generated catch block
			e1.showMessage();
		}
		catch(InvocationTargetException e1){
			new CommonException(e1.getTargetException().getMessage()).showMessage();
		}
		catch( ClassNotFoundException e1){
			new CommonException("File AC.xml corrotto").showMessage();
		}
		catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException
				| SecurityException | IllegalArgumentException
				e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
	
}


private Manutenzione prendiDatiDaView() throws CommonException{
	LocalDate d=data_inizio.getValue();
	if(d==null)
		throw new CommonException("La data d'inizio è vuota");
	if(radio_ord.isSelected()){
		String mot=motivo.getText();
		if(mot==null)
			mot="";
			return new ManutenzioneOrdinaria(d ,null, mot,this.a.getIDauto());
	}
	else{
		String mot=motivo.getText();
		if(mot==null)
			mot="";
			return new ManutenzioneOrdinaria(d ,null, mot,this.a.getIDauto());
	}
}
}
