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
		noleggio.get(0).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getIDNoleggioT());
		noleggio.get(1).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getInizioNoleggioT());
		noleggio.get(2).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getFineNoleggioT());
		noleggio.get(3).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getRientroT());
		noleggio.get(4).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getRientroT());
		noleggio.get(5).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getKmBaseT());
		noleggio.get(6).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getKmRientroT());
		noleggio.get(7).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getStatoT());
		noleggio.get(8).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getNumeroSettimaneT());
		noleggio.get(9).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getNumeroGiorniT());
		noleggio.get(10).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getNumeroChilometriT());
		noleggio.get(11).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getLuogoRestituzioneT());
		noleggio.get(12).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getAutoT());
		noleggio.get(13).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getContrattoT());
		noleggio.get(14).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getClienteT());
		noleggio.get(15).setCellValueFactory(cellData -> ((Noleggio) cellData.getValue()).getNoteT());
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
