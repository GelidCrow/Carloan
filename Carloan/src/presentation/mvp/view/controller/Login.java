package presentation.mvp.view.controller;



import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import Errori.AlertView;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.model.Exception.CommonException;
import utility.Crittografia;
import utility.Finestra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

public class Login extends Schermata{
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPsw;
	@FXML
	private Button btnLogin;
	
	@FXML
	public void btnLogin(ActionEvent e){
		
		business.entity.Login credenziali;
		try {

			credenziali = new business.entity.Login(txtUsername.getText(),txtPsw.getText());
			
			//non crittografo la password per verificare se rispetta i campi
			presenter.processRequest("VerificaLogin", credenziali);//controlal solo se sono corretti , non se nel db esistono!
			
			//la password viene crittografata e settata
			credenziali.setPassword(Crittografia.CriptaPassword(credenziali.getPassword()));
			
			Entity x=(Entity) presenter.processRequest("login",credenziali);
			
			
			if(x!=null){
				/*Esito del log in positivo*/
				if(x instanceof Amministratore){
					FXMLParameter.setTitolo("Amministratore");
				    FXMLParameter.setRidimensionabile(false);
					this.chiudiFinestra();
					Finestra.visualizzaFinestra(this.presenter,FXMLParameter,this,"MostraSchermataGenerale",Modality.APPLICATION_MODAL);
					Utente.setUtente((Amministratore)x);
				}
				else if(x instanceof SupervisoreAgenzia){
					FXMLParameter.setTitolo("Supervisore Agenzia");
					FXMLParameter.setRidimensionabile(false);
					this.chiudiFinestra();
					Finestra.visualizzaFinestra(this.presenter,FXMLParameter,this,"MostraSchermataGenerale",Modality.APPLICATION_MODAL);
				}
				else if(x instanceof SupervisoreSede){
					FXMLParameter.setTitolo("Supervisore Sede");
				    FXMLParameter.setRidimensionabile(false);
					this.chiudiFinestra();
					Finestra.visualizzaFinestra(this.presenter, FXMLParameter,this,"MostraSchermataGenerale",Modality.APPLICATION_MODAL);
				}
				else if(x instanceof Operatore){
				    FXMLParameter.setTitolo("Operatore");
				    FXMLParameter.setRidimensionabile(false);
					this.chiudiFinestra();
					Finestra.visualizzaFinestra(this.presenter,FXMLParameter,this,"MostraSchermataGenerale",Modality.APPLICATION_MODAL);
					Utente.setUtente((Operatore)x);
			}
			}
			else{
				AlertView.getAlertView("Autenticazione fallita : Ricontrollare l'Username e la password inserite",AlertType.ERROR);
			}
		} catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NoSuchMethodException| SecurityException | IllegalArgumentException
				| InvocationTargetException | NoSuchAlgorithmException | CommonException e1) {
				//AlertView.getAlertView("C'è stato un problema" + e1.getMessage(), AlertType.ERROR);
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void btnEsci(ActionEvent e){
		this.chiudiFinestra();
	}
}
