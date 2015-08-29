package presentation.mvp.view.controller.generale.noleggio.multa;

import java.time.LocalDate;

import business.entity.Noleggio.Multa;
import business.model.Exception.CommonException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import presentation.mvp.view.controller.Schermata;

public class NuovaMulta extends Schermata{
	@FXML
	private DatePicker dMulta;
	@FXML
	private TextField txtImporto;
	@FXML
	private ChoiceBox<Multa> choiceStato;
	@FXML
	private TextArea txtAreaNote;
	
	@FXML
	public void btnConferma(ActionEvent e){
		
	}
	
	@FXML
	public void btnCancella(ActionEvent e){
		
	}
	
	public void checkDMulta(ActionEvent e){
		final int MASSIMO_INIZIO= 3;
		try {
			LocalDate lDate= dMultazz.getValue();
			if(lDate!=null){
				if(lDate.isBefore(LocalDate.now())){
					impostaDate(LocalDate.now());
				}
				if (lDate.isAfter(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()+MASSIMO_INIZIO))){
					impostaDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()+MASSIMO_INIZIO));		
				}
				else 
					impostaDate(lDate);
			}
			else 
				throw new CommonException("Data di Ritiro non impostata");
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	
}
