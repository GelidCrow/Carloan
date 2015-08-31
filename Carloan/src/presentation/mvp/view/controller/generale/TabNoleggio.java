package presentation.mvp.view.controller.generale;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.noleggio.RicercaNoleggio;
import utility.Finestra;
import utility.ParametriFXML;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoNoleggio;
import business.model.Exception.CommonException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
public class TabNoleggio {

	private ObservableList<TableColumn<Noleggio,?>> noleggio;
		
	private TableView<Noleggio> tbNoleggio;

	private Schermata schermata;
	
	private ParametriFXML FXMLParameter;
	
	private Presenter presenter;
	
	
	public void NuovoNoleggio(){
		FXMLParameter.setTitolo("Nuovo Noleggio");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataNuovoNoleggio",Modality.APPLICATION_MODAL);
	}
	
	public void ModificaNoleggio(){
		
	}
	
	public void ChiudiNoleggio(){
		
	}
	
	
	@SuppressWarnings("rawtypes")
	void visualizzaOptional(){
		FXMLParameter.setTitolo("Optional");
	    FXMLParameter.setRidimensionabile(false);
	    FXMLParameter.setEntity(((SchermataGenerale)schermata).getEntitaElementoSelezionato("Noleggio"));
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataVisualizzaOptional",Modality.APPLICATION_MODAL);
	}
	
	void NuovaMulta(){
		try {
			if(tbNoleggio.getSelectionModel().getSelectedIndex()<0){
					throw new CommonException("Nessun elemento selezionato");
			}
			Noleggio noleggio= (Noleggio) ((SchermataGenerale<?>)schermata).getEntitaElementoSelezionato("Noleggio");
			if(noleggio.getStato().toString().equals(noleggio.getStato().annullato.toString())){
				throw new CommonException("Non � possibile aprire una mula per questo noleggio in quanto � stato annullato");
			}
			else {
				FXMLParameter.setTitolo("Nuova Multa");
			    FXMLParameter.setRidimensionabile(false);
			    FXMLParameter.setEntity(noleggio);
				Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataInserimentoMulta",Modality.APPLICATION_MODAL);
			}
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.showMessage();
		}
	
	}
	
	public void ricerca(int idContratto, StatoNoleggio stato,
			LocalDate dStart) {
		FXMLParameter.setTitolo("Visualizza multe");
	    FXMLParameter.setRidimensionabile(false);
	    RicercaNoleggio ricercaNoleggio = new RicercaNoleggio(stato,dStart,idContratto);
		try {
			presenter.processRequest("RicercaNoleggio", ricercaNoleggio);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void VisualizzaMulta(){
		if(tbNoleggio.getSelectionModel().getSelectedIndex()<0){
			try {
				throw new CommonException("Nessun elemento selezionato");
			} catch (CommonException e) {
				// TODO Auto-generated catch block
				e.showMessage();
			}
		}
		else {
			FXMLParameter.setTitolo("Visualizza multe");
		    FXMLParameter.setRidimensionabile(false);
		    FXMLParameter.setEntity(((SchermataGenerale<?>)schermata).getEntitaElementoSelezionato("Noleggio"));
			Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataVisualizzaMulte",Modality.APPLICATION_MODAL);
		}
	}
	
	public void bindingValuesContratto(){
		noleggio.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getIDNoleggio()));
		noleggio.get(1).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Noleggio) cellData.getValue()).getInizioNoleggio()));
		noleggio.get(2).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Noleggio) cellData.getValue()).getFineNoleggio()));
		noleggio.get(3).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Noleggio) cellData.getValue()).getRientro()));
		noleggio.get(4).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Noleggio) cellData.getValue()).getRitiro()));
		noleggio.get(5).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getKmBase()));
		noleggio.get(6).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getKmRientro()));
		noleggio.get(7).setCellValueFactory(cellData -> new SimpleStringProperty(((Noleggio) cellData.getValue()).getStato().toString()));
		noleggio.get(8).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getNumeroSettimane()));
		noleggio.get(9).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getNumeroGiorni()));
		noleggio.get(10).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getNumeroChilometri()));
	}


	TabNoleggio(TableView<Noleggio> tbNoleggio,Schermata schermata){
		noleggio= tbNoleggio.getColumns();
		this.schermata= schermata;
		this.tbNoleggio=tbNoleggio;
		bindingValuesContratto();
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}


}
