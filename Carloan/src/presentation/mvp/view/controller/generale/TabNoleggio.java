package presentation.mvp.view.controller.generale;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.Finestra;
import utility.ParametriFXML;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
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
	
	
	void visualizzaOptional(){
		FXMLParameter.setTitolo("Optional");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataVisualizzaOptional",Modality.APPLICATION_MODAL);
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
