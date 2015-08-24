package presentation.mvp.view.controller.generale.cliente;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;
import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;

public class ModificaCliente extends NuovoCliente{
	@FXML
	private Button btnConferma;
	private Cliente cliente;
	private boolean Aggiornare=true;
	
	@FXML
	private  TextField txtIndirizzo;
	@FXML
	private  TextField txtNumCel;
	@FXML
	private  TextField txtNumTel;
	@FXML
	private  TextField txtPartIva;
	@FXML
	private  TextField txtEmail;
	
	  @FXML private VBox vCampi;
	  @FXML private AnchorPane anchCampi;
	
	@SuppressWarnings("rawtypes")
	public void impostaView(Schermata chiamante){
		SchermataGenerale scChiamante= (SchermataGenerale)chiamante;
		Cliente cliente =  (Cliente)scChiamante.getEntitaElementoSelezionato("Cliente");
		txtIndirizzo.setText(cliente.getIndirizzo());
		txtNumCel.setText(cliente.getNumCell());
		txtNumTel.setText(cliente.getNumTel());
		txtPartIva.setText(cliente.getPartitaIva());
		txtEmail.setText(cliente.getEmail());
		
	}
	
	@Override
	@SuppressWarnings({ "unchecked","rawtypes" })
	@FXML 
	public void btnConferma(ActionEvent event){

		SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
		cliente= (Cliente)scChiamante.getEntitaElementoSelezionato("Cliente");//ottengo le info sul cliente selezionato, ma ne cambio alcune

		Aggiornare=true;
		cliente = prendiDatiDaView();
		if(Aggiornare==true){
			try {
				presenter.processRequest("VerificaClienteModificato", cliente);
				presenter.processRequest("ModificaCliente", cliente);
				//Prendo la schermata che ha chiamato questo metodo , li passo l'elemento selezionato , il cliente da modificare e la tabella su cui lavorare
				((SchermataGenerale)this.getChiamante()).caricaTabella((List<Cliente>)presenter.processRequest("getAllClienti",null), scChiamante.getTable("Cliente"));
			} 
			catch(CommonException e){
				AlertView.getAlertView(e.getMessage(), AlertType.ERROR);
			}
			catch(InvocationTargetException e){
				new CommonException(((InvocationTargetException) e).getTargetException().getMessage()).showMessage();
			}
			catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException  e) {
				e.printStackTrace();
			}	
		}
		else {
			AlertView.getAlertView("Nessuna modifica da apportare", AlertType.INFORMATION);
		}
	}
	
	@Override
	public Cliente prendiDatiDaView(){
		String indirizzo = txtIndirizzo.getText();
		String numCellu= txtNumCel.getText();
		String numTel = txtNumTel.getText();
		String PartitaIva= txtPartIva.getText();
		String Email = txtEmail.getText();
		
		if(indirizzo.length()!=0)
			cliente.setIndirizzo(indirizzo);
		if(numCellu.length()!=0)
			cliente.setNumCell(numCellu);
		if(numTel.length()!=0)
			cliente.setNumTel(numTel);
		if(PartitaIva.length()!=0)
			cliente.setPartitaIva(PartitaIva);
		if(Email.length()!=0)
			cliente.setEmail(Email);
		
		if(indirizzo.length()==0 && numCellu.length()==0 && numTel.length()==0 && PartitaIva.length()==0 && Email.length()==0){
			Aggiornare=false;
		}
		return cliente;
	}
	@Override
	public void initData(Entity entity){
		Cliente cliente =  (Cliente)entity;
		txtIndirizzo.setText(cliente.getIndirizzo());
		txtNumCel.setText(cliente.getNumCell());
		txtNumTel.setText(cliente.getNumTel());
		txtPartIva.setText(cliente.getPartitaIva());
		txtEmail.setText(cliente.getEmail());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}
}
