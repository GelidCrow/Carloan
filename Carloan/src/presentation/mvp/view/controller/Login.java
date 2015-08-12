package presentation.mvp.view.controller;



import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Errori.AlertView;
import business.entity.Entity;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.ReturnableStage;
import utility.Crittografia;
import utility.ParametriFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login extends Schermata{
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPsw;
	@FXML
	private Button btnLogin;
	
	@FXML
	public void btnLogin(ActionEvent e){
		
		List<String> parameter = new ArrayList<String>();
		
		parameter.add(txtUsername.getText());
		 
		try {
			
			parameter.add(Crittografia.CriptaPassword(txtPsw.getText()));
			
			Entity x=(Entity) presenter.processRequest("login",parameter);
			
			if(x!=null){
				/*Esito del log in positivo*/
				if(x instanceof Amministratore){
					FXMLParameter.setTitolo("Amministratore");
				    FXMLParameter.setRidimensionabile(false);
					this.chiudiFinestra();
					ReturnableStage stager= (ReturnableStage) presenter.processRequest("MostraSchermataAmministratore",FXMLParameter);
					stager.setStageToWindow(stager);
					stager.show();
				
				}
				else if(x instanceof SupervisoreAgenzia){
					FXMLParameter.setTitolo("Supervisore Agenzia");
					FXMLParameter.setRidimensionabile(false);
					this.chiudiFinestra();
					ReturnableStage stager= (ReturnableStage) presenter.processRequest("MostraSchermataSupervisoreAgenzia",FXMLParameter);
					stager.setStageToWindow(stager);
					stager.show();
				}
				else if(x instanceof SupervisoreSede){
					FXMLParameter.setTitolo("Supervisore Sede");
				    FXMLParameter.setRidimensionabile(false);
					this.chiudiFinestra();
					ReturnableStage stager= (ReturnableStage) presenter.processRequest("MostraSchermataSupervisoreSede",FXMLParameter);
					stager.setStageToWindow(stager);
					stager.show();
				}
				else if(x instanceof Operatore){
				    FXMLParameter.setTitolo("Operatore");
				    FXMLParameter.setRidimensionabile(false);
					this.chiudiFinestra();
					ReturnableStage stager= (ReturnableStage) presenter.processRequest("MostraSchermataOperatore",FXMLParameter);
					stager.setStageToWindow(stager);
					stager.show();
				}	
			}
			else{
				new AlertView("Autenticazione fallita : Ricontrollare l'Username e la password inserite",AlertType.ERROR);
			}
		} catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NoSuchMethodException| SecurityException | IllegalArgumentException
				| InvocationTargetException | NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
	}
}
