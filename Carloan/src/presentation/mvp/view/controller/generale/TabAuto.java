package presentation.mvp.view.controller.generale;

import presentation.mvp.view.controller.Schermata;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import business.entity.Auto.Autoveicolo;
import business.entity.Noleggio.Noleggio;
import utility.Finestra;

public class TabAuto {
	private ObservableList<TableColumn<Autoveicolo,?>> auto;
	private Schermata schermata;
	private TableView<Autoveicolo> tbAuto;
	public void NuovaAuto(){
		//Finestra.visualizzaFinestra(presenter, FXMLParameter, chiamante, schermata, modality);
	}
	TabAuto(TableView<Autoveicolo> tbAuto,Schermata schermata){
		auto=tbAuto.getColumns();
		this.schermata=schermata;
		this.tbAuto=tbAuto;
	}
}
