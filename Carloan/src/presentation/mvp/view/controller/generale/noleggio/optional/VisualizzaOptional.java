package presentation.mvp.view.controller.generale.noleggio.optional;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.entity.Entity;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.Optional.Guidatore;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.Optional;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.Finestra;
import utility.ParametriFXML;

public class VisualizzaOptional extends Schermata{
	  @FXML
	  private TableView<Optional> tbGuidatori;
	  @FXML
	  private TableColumn<Optional,String> nomeOptScelti;
	  @FXML
	  private TableColumn<Optional,String> descOptScelti;
	
	@SuppressWarnings("rawtypes")
	@FXML
	public void btnVisualizzaGuidatori(ActionEvent e){
		if(((SchermataGenerale)this.getChiamante()).getEntitaElementoSelezionato("Noleggio")==null){
    		try {
				throw new CommonException("Nessun elemento selezionato");
			} catch (CommonException e1) {
				e1.showMessage();
			}
		}
		else{
			FXMLParameter.setTitolo("Optional");
		    FXMLParameter.setRidimensionabile(false);
		    FXMLParameter.setEntity(((SchermataGenerale)this.getChiamante()).getEntitaElementoSelezionato("Noleggio"));
			Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraSchermataVisualizzaGuidatori",Modality.APPLICATION_MODAL);	}
	}
	
	private void bindingValuesOptional(){
		nomeOptScelti.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getNome()));
		descOptScelti.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getDescrizione()));
	}
	  
	  public void btnOk(ActionEvent e){
		  chiudiFinestra();
	  }
/**
	 * <p>Carica la tabella dei guidatori </p>
	 * @return
	 */
	private void caricaTabella(List<Optional> list){
		ObservableList<Optional> obsList= FXCollections.observableList(list);
		tbGuidatori.setItems(obsList);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void initData(Entity entity){
		try {
			List<Optional> guidatori= (List<Optional>)presenter.processRequest("getAllOptionalByNoleggio",((Noleggio)entity).getIDNoleggio());
			caricaTabella(guidatori);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.showMessage();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false,false);	
		bindingValuesOptional();	
	}
}
