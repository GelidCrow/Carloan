package presentation.mvp.view.controller.generale;

import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import business.entity.Cliente;
import business.model.Exception.CommonException;

public class TabClientiController{
	
	private ObservableList<TableColumn<Cliente, ?>> cliente;
	
	private TableView<Cliente> tbCliente;

	private Schermata schermata;
	
	private ParametriFXML FXMLParameter;
	
	private Presenter presenter;
	
	public void NuovoCliente(){
		FXMLParameter.setTitolo("Nuovo Cliente");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataNuovoCliente",Modality.APPLICATION_MODAL);
	}
	
	public void ModificaCliente() throws CommonException{
		FXMLParameter.setTitolo("Modifica Cliente");
	    FXMLParameter.setRidimensionabile(false);
	    if(tbCliente.getSelectionModel().getSelectedIndex()< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaCliente",Modality.APPLICATION_MODAL);	
	}
	
	/**
	 * <p>Effettua il binding con i singoli campi della tabella</p>
	 */
	public void bindingValuesCliente(){

		
		cliente.get(0).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getCodFiscaleT());
		
		cliente.get(1).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getNomeT());
		
		cliente.get(2).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getCognomeT());
		
		cliente.get(3).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getSessoT());
		
		cliente.get(4).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getDatanascitaT());
		
		cliente.get(5).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getIndirizzoT());
	
		cliente.get(6).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getDataEmissPatenteT());

		cliente.get(7).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getDataScadPatenteT());

		cliente.get(8).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getPatenteGuidaT());
		
		cliente.get(9).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getNumCellT());

		cliente.get(10).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getNumTelT());

		cliente.get(11).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getPartitaIvaT());

		cliente.get(12).setCellValueFactory(cellData -> ((Cliente) cellData.getValue()).getEmailT());

	}

	
	TabClientiController(TableView<Cliente> tbCliente,Schermata schermata){
		cliente= tbCliente.getColumns();
		
		this.schermata= schermata;
		
		this.tbCliente=tbCliente;
		
		bindingValuesCliente();
		
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}
	
	
}
