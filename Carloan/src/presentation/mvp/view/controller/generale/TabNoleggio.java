package presentation.mvp.view.controller.generale;

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
	
	public void bindingValuesContratto(){
		noleggio.get(0).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getIDContrattoT());
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
