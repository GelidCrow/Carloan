package presentation.mvp.view.controller.generale;


import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabContratto {
	
	private ObservableList<TableColumn<Contratto,?>> contratto;
		
	private TableView<Contratto> tbContratto;

	private Schermata schermata;
	
	private ParametriFXML FXMLParameter;
	
	private Presenter presenter;
	
	/**
	 * 
	 * @param schermata importante per settare la view
	 */
	public void NuovoContratto(){
		FXMLParameter.setTitolo("Nuovo Contratto");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataNuovoContratto",Modality.APPLICATION_MODAL);
	
	}		
	@SuppressWarnings("rawtypes")
	public void ModificaContratto() throws CommonException{
		
	    if(tbContratto.getSelectionModel().getSelectedIndex()< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	FXMLParameter.setTitolo("Modifica Contratto");
		    FXMLParameter.setRidimensionabile(false);
		    FXMLParameter.setEntity(((SchermataGenerale) schermata).getEntitaElementoSelezionato("Contratto"));
	    	if(((Contratto)tbContratto.getSelectionModel().getSelectedItem()).getStato().equals("Aperto")){
	    		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaContratto",Modality.APPLICATION_MODAL);
	    	}
	    	else{
	    		throw new CommonException("Operazione non disponibile per questo contratto");
	    	}
	    }
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void ChiudiContratto() throws CommonException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		Contratto contratto= (Contratto) ((SchermataGenerale) schermata).getEntitaElementoSelezionato("Contratto");
		List<Noleggio> noleggiAperti= (List<Noleggio>)presenter.processRequest("getNoleggiAperti", contratto.getIDContratto());
		if(noleggiAperti.size()>0){
			throw new CommonException("Ci sono dei noleggi aperti , non � possibile fare questa scelta");
		}
		else 
			contratto.setDataChiusura(LocalDate.now());//imposto la data di chiusura se il valore scelto � annullato
		
	    if(tbContratto.getSelectionModel().getSelectedIndex()< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	if(((Contratto)tbContratto.getSelectionModel().getSelectedItem()).getStato().equals("Aperto")){
	    		FXMLParameter.setTitolo("Chiudi Contratto");
	    	    FXMLParameter.setRidimensionabile(false);
	    	    FXMLParameter.setEntity(((SchermataGenerale) schermata).getEntitaElementoSelezionato("Contratto"));
	    		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataChiusuraContratto",Modality.APPLICATION_MODAL);
	    	}
	    	else{
	    		throw new CommonException("Operazione non disponibile per questo contratto");
	    	}
	    }
	}	
	/**
	 * <p>Effettua il binding con i singoli campi della tabella</p>
	 */
	public void bindingValuesContratto(){

		
		contratto.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Contratto) cellData.getValue()).getIDContratto()));
		
		contratto.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Contratto) cellData.getValue()).getStato()));
		
		contratto.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Contratto) cellData.getValue()).getNote()));
		
		contratto.get(3).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Contratto) cellData.getValue()).getDataCreazione()));
		
		contratto.get(4).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Contratto) cellData.getValue()).getDataChiusura()));
	
	}

	
	
	public 
	TabContratto(TableView<Contratto> tbContratto,Schermata schermata){
		contratto= tbContratto.getColumns();
		
		this.schermata=schermata;
		this.tbContratto=tbContratto;
		
		//
		bindingValuesContratto();
		
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}

	
	
}
