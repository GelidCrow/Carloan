package presentation.mvp.view.controller.generale.noleggio;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Optional.ChilometraggioIllimitato;
import business.entity.Noleggio.Optional.GuidatoreAggiuntivo;
import business.entity.Noleggio.Optional.Optional;

public class NuovoNoleggio extends ImpostaNoleggio<Entity>{
		
	
	@FXML
	public void btnAggiungiOptionalNoleggio(ActionEvent e){
		ObservableList<Entity> listItem= tbOptionalScelti.getItems();

		Optional itemSelected = (Optional) tbOptionalNoleggio.getSelectionModel().getSelectedItem();
		if(!listItem.contains(itemSelected)){
			tbOptionalScelti.getItems().add(itemSelected);
			controllaGuidatoreAggiuntivo();
			controllaChilLimitato();
			}
		else 
			AlertView.getAlertView("Hai già aggiunto quest'optional", AlertType.WARNING);
	}
	@FXML
	public void btnAggiungiOptionalAuto(ActionEvent e){
		ObservableList<Entity> listItem= tbOptionalScelti.getItems();
		Optional itemSelected = (Optional) tbOptionalAuto.getSelectionModel().getSelectedItem();
		if(!listItem.contains(itemSelected)){
			tbOptionalScelti.getItems().add(itemSelected);
			
			}
		else 
			AlertView.getAlertView("Hai già aggiunto quest'optional", AlertType.WARNING);
	}
	/**
	 * <p>Setta ad abilitato i valori di guidatore aggiuntivo</p>
	 */
	private void impostaTrueGuidatoreAggiuntivo(){
		txtNome.setDisable(false);
		txtCognome.setDisable(false);
		txtIndirizzo.setDisable(false);
		txtCodFiscale.setDisable(false);
		txtPatente.setDisable(false);
	}
	
	/**
	 * <p>Setta ad abilitato i valori di guidatore aggiuntivo</p>
	 */
	private void controllaGuidatoreAggiuntivo(){
		ObservableList<Entity> listItem=  tbOptionalScelti.getItems();
		for(Entity e: listItem){
			if((Optional)e instanceof GuidatoreAggiuntivo)
			{
				impostaTrueGuidatoreAggiuntivo();
				return;
			}
		}
		impostaFalsoTxtGuidatore();
	}
	/**
	 * <p>Disabilita la scelta del limite di copertura</p>
	 */
	private void controllaChilLimitato(){
		ObservableList<Entity> listItem=  tbOptionalScelti.getItems();
		for(Entity e: listItem){
			if((Optional)e instanceof ChilometraggioIllimitato)
			{
				choiceLimite.setDisable(true);
			}
		}
	}
	@FXML
	public void btnAggiungiGuidatore(ActionEvent e){
		
	}
	@FXML
	public void	btnRimuoviGuidatore(ActionEvent e){
		
	}
	@FXML
	public void btnInfoAuto(ActionEvent e){
		
	}
	@FXML
	public void btnRimuoviOptional(ActionEvent e){
		if(tbOptionalScelti.getSelectionModel().getSelectedIndex()<0){
			AlertView.getAlertView("Nessun elemento selezionato", AlertType.WARNING);
		}
		else{
			tbOptionalScelti.getItems().remove( tbOptionalScelti.getSelectionModel().getSelectedItem());
			controllaGuidatoreAggiuntivo();
			}
	}
	@FXML
	public void btnCalcolaPrezzo(ActionEvent e){
		
	}
	@FXML
	public void btnAggiungiCartaCredito(ActionEvent e){
		
	}
	@FXML
	public void btnCancella(ActionEvent e){
		
	}
	@FXML
	public void btnConferma(ActionEvent e){
		
	}

}
