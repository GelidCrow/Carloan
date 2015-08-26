package presentation.mvp.view.controller.generale;


import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import business.entity.Luoghi.Agenzia;
public class TabAgenzia {
	private ObservableList<TableColumn<Agenzia,?>> agenzia;
	private TableView<Agenzia> table_agenzia;
	private Schermata schermata;
	TabAgenzia(TableView<Agenzia> tbAgenzia,Schermata schermata){
		agenzia= tbAgenzia.getColumns();
		this.schermata= schermata;
		this.table_agenzia=tbAgenzia;
		bindingValuesAgenzia();
		Presenter presenter = new Presenter();
		ParametriFXML FXMLParameter = new ParametriFXML(null,false);
	}
	private void bindingValuesAgenzia() {
		agenzia.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Agenzia) cellData.getValue()).getIDAgenzia()));
		agenzia.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Agenzia) cellData.getValue()).getNome()));
		agenzia.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Agenzia) cellData.getValue()).getNumTelefono()));
		
		
	}
}
