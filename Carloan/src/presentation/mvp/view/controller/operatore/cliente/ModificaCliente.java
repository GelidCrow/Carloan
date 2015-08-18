package presentation.mvp.view.controller.operatore.cliente;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.operatore.SchermataGenerale;
import utility.ParametriFXML;
import business.entity.Cliente;
import business.model.Exception.CommonException;

public class ModificaCliente extends NuovoCliente{
	@FXML
	private Button btnConfermaModifica;
	
	@SuppressWarnings({ "unchecked","rawtypes" })
	@FXML
	public void btnConfermaModifica(ActionEvent event){

		SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
		Cliente cliente= prendiDatiDaView();
	System.out.println(	cliente.getIndirizzo());
		try {
				presenter.processRequest("VerificaClienteModificato", cliente);	
				presenter.processRequest("ModificaCliente", cliente);
				//Prendo la schermata che ha chiamato questo metodo , li passo l'elemento selezionato , il cliente da modificare e la tabella su cui lavorare
				((SchermataGenerale)this.getChiamante()).aggiornaElementotabella(scChiamante.getElemSelezionato(),cliente,((SchermataGenerale)this.getChiamante()).getTableClienti());
				/*if(this.getStage().getTitle()=="Modifica Cliente"){
				@SuppressWarnings("rawtypes")
				SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
				Cliente cliente= (Cliente) scChiamante.getEntitaElementoSelezionato();
				settaView(cliente);
			}*/
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			//AlertView.getAlertView(e.toString(), AlertType.ERROR);
		}
	}
	@Override
	public Cliente prendiDatiDaView(){
		Cliente cliente= new Cliente();
		cliente.setIndirizzo(txtIndirizzo.getText());
		cliente.setNumCell(txtNumCel.getText());
		cliente.setNumTel(txtNumTel.getText());
		cliente.setPartitaIva(txtPartIva.getText());
		cliente.setEmail(txtEmail.getText());
		return cliente;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}

}
