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
	private Cliente cliente;
	@SuppressWarnings({ "unchecked","rawtypes" })
	@FXML
	public void btnConfermaModifica(ActionEvent event){

		SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
		cliente= (Cliente)scChiamante.getEntitaElementoSelezionato();//ottengo le info sul cliente selezionato, ma ne cambio alcune
		cliente= prendiDatiDaView();
			try {
				presenter.processRequest("VerificaCliente", cliente);
				presenter.processRequest("ModificaCliente", cliente);
				//Prendo la schermata che ha chiamato questo metodo , li passo l'elemento selezionato , il cliente da modificare e la tabella su cui lavorare
				((SchermataGenerale)this.getChiamante()).aggiornaElementotabella(scChiamante.getElemSelezionato(),cliente,((SchermataGenerale)this.getChiamante()).getTableClienti());			
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	@Override
	public Cliente prendiDatiDaView(){
		String indirizzo = txtIndirizzo.getText();
		String numCellu= txtNumCel.getText();
		String numTel = txtNumTel.getText();
		String PartitaIva= txtPartIva.getText();
		String Email = txtEmail.getText();
		
		if(indirizzo!= null)
			cliente.setIndirizzo(indirizzo);
		if(numCellu!= null)
			cliente.setNumCell(numCellu);
		if(numTel!= null)
			cliente.setNumTel(numTel);
		if(PartitaIva!= null)
			cliente.setPartitaIva(PartitaIva);
		if(Email!= null)
			cliente.setEmail(Email);
		
		return cliente;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}

}
