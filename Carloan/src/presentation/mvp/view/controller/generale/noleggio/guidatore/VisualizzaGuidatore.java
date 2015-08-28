package presentation.mvp.view.controller.generale.noleggio.guidatore;

import java.time.LocalDate;
import java.util.List;

import business.entity.Noleggio.Optional.Guidatore;
import business.entity.pagamento.Guidatore;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.mvp.view.controller.Schermata;

public class VisualizzaGuidatore extends Schermata{
	  @FXML
	  private TableView<Guidatore> tbGuidatori;
	  @FXML
	  private TableColumn<Guidatore,String> codFiscale;
	  @FXML
	  private TableColumn<Guidatore,String> nomeECognome;
	  @FXML
	  private TableColumn<Guidatore,LocalDate> indirizzo;
	  @FXML
	  private TableColumn<Guidatore,String> patente;
	
	  
	  public void btnOk(ActionEvent e){
		  chiudiFinestra();
	  }
	  
  
  /**
	 * <p>Carica la tabella dei guidatori </p>
	 * @return
	 */
	private void caricaTabella(List<Guidatore> list){
		ObservableList<Guidatore> obsList= FXCollections.observableList(list);
		tbGuidatori.setItems(obsList);
	}
	private void bindingValuesCarta(){
		codFiscale.setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getCodFiscale()));
		nomeECognome.setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getNome()+ " "+ ((Guidatore) cellData.getValue()).getCognome()));
		indirizzo.setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getIndirizzo()));
		patente.setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getPatenteGuida()));
	}
	
	
	
	
}
