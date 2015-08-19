package presentation.mvp.view.controller.generale;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import business.entity.Noleggio.Contratto;

public class TabContrattoController<T> extends SchermataGenerale<Contratto>{
	
	private ObservableList<TableColumn<T,?>> contratto;
	/**
	 * <p>Effettua il binding con i singoli campi della tabella</p>
	 */
	public void bindingValuesContratto(){

		
		contratto.get(0).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getIDContrattoT());
		
		contratto.get(1).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getStatoT());
		
		contratto.get(2).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getNoteT());
		
		contratto.get(3).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getDataCreazioneT());
		
		contratto.get(4).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getDataChiusuraT());
	
		contratto.get(5).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getIDClienteT());

		/*contratto.get(7).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getIDAmministratore());

		contratto.get(8).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getPatenteGuidaT());
		
		contratto.get(9).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getNumCellT());

		contratto.get(10).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getNumTelT());

		contratto.get(11).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getPartitaIvaT());

		contratto.get(12).setCellValueFactory(cellData -> ((Contratto) cellData.getValue()).getEmailT());*/

	}

	TabContrattoController(ObservableList<TableColumn<T, ?>> observableList){
		contratto= observableList;
		bindingValuesContratto();
	}
	
}
