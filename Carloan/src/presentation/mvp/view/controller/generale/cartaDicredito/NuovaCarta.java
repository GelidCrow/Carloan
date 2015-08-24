package presentation.mvp.view.controller.generale.cartaDicredito;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.pagamento.tipiCircuiti;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import MessaggiFinestra.AlertView;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;

public class NuovaCarta extends Schermata {
	@FXML private TextField txtIban;
	@FXML private TextField txtNumCarta;
	@FXML private DatePicker dScadenza;
	@FXML private ChoiceBox<String> choiceCircuito;
	
	@FXML
	public void btnConferma(ActionEvent e){
		if(txtIban.getText().isEmpty() || txtNumCarta.getText().isEmpty() || dScadenza.getValue()==null){
			
		}
	}
	

	@FXML
	public void btnCancella(ActionEvent event){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false,false);	
		settaChoiceBox();
	}
	
	public void settaChoiceBox(){
		ObservableList<String> choice = FXCollections.observableArrayList(tipiCircuiti.getAllCircuiti());
		choiceCircuito.setItems(choice);
		choiceCircuito.getSelectionModel().select(0);
	}
}
