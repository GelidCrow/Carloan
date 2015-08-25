package presentation.mvp.view.controller.generale.noleggio;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Optional.ChilometraggioIllimitato;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.Seggiolino;

public class NuovoNoleggio extends ImpostaNoleggio<Entity>{
		
	
	@FXML
	public void btnAggiungiOptionalNoleggio(ActionEvent e){
		ObservableList<Entity> listItem= tbOptionalScelti.getItems();

		Optional itemSelected = (Optional) tbOptionalNoleggio.getSelectionModel().getSelectedItem();
		if(!listItem.contains(itemSelected)){
			tbOptionalScelti.getItems().add(itemSelected);
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
