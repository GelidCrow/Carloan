package presentation.mvp.view.controller.generale;


import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import business.entity.Noleggio.Contratto;
import business.model.Exception.CommonException;

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
	public void ModificaContratto() throws CommonException{
		FXMLParameter.setTitolo("Modifica Contratto");
	    FXMLParameter.setRidimensionabile(false);
	    if(tbContratto.getSelectionModel().getSelectedIndex()< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	if(((Contratto)tbContratto.getSelectionModel().getSelectedItem()).getStato().equals("Aperto")){
	    		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaContratto",Modality.APPLICATION_MODAL);
	    	}
	    	else{
	    		throw new CommonException("Operazione non disponibile per questo contratto");
	    	}
	    }
	}
	
	public void ChiudiContratto() throws CommonException{
		FXMLParameter.setTitolo("Chiudi Contratto");
	    FXMLParameter.setRidimensionabile(false);
	    if(tbContratto.getSelectionModel().getSelectedIndex()< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	if(((Contratto)tbContratto.getSelectionModel().getSelectedItem()).getStato().equals("Aperto")){
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

		
		contratto.get(0).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getIDContrattoT());
		
		contratto.get(1).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getStatoT());
		
		contratto.get(2).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getNoteT());
		
		contratto.get(3).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getDataCreazioneT());
		
		contratto.get(4).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getDataChiusuraT());
	
		contratto.get(5).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getCliente().getNomeCognome());


	}

	
	public void setSchermata(Schermata schermata){
		this.schermata=schermata;
	}
	
	TabContratto(TableView<Contratto> tbContratto){
		contratto= tbContratto.getColumns();
		
		this.tbContratto=tbContratto;
		
		bindingValuesContratto();
		
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}
	
	
}
