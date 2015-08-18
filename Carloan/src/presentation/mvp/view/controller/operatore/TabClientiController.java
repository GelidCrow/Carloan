package presentation.mvp.view.controller.operatore;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import business.entity.Cliente;

public class TabClientiController extends SchermataGenerale<Cliente>{
	
	protected ObservableList<TableColumn<Cliente,?>> client;

	/**
	 * <p>Effettua il binding con i singoli campi della tabella</p>
	 */
	public void bindingValuesCliente(){

		client.get(0).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getNomeT());
		
		client.get(1).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getCognomeT());
		
		client.get(2).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getSessoT());
		
		client.get(3).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getDatanascitaT());
		
		client.get(4).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getIndirizzoT());
		
		client.get(5).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getCodFiscaleT());
		
		client.get(6).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getNumCellT());

		client.get(7).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getNumTelT());

		client.get(8).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getPatenteGuidaT());

		client.get(9).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getDataEmissPatenteT());

		client.get(10).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getDataScadPatenteT());

		client.get(11).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getPartitaIvaT());

		client.get(12).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getEmailT());

	}
	public TabClientiController(){
		client = tbCliente.getColumns();
	}

}
