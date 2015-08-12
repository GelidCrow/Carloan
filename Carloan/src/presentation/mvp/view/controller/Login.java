package presentation.mvp.view.controller;



import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
	
	private Presenter presenter ;
	
	private ParametriFXML FXMLParameter;
	
	private Stage stage;
	
	@FXML
	public void btnLogin(ActionEvent e){
		FXMLParameter = new ParametriFXML(null,false);
		
		List<String> parameter = new ArrayList<String>();
		
		parameter.add(txtUsername.getText());
		 
		try {
			
			parameter.add(Crittografia.CriptaPassword(txtPsw.getText()));
			
			Entity x=(Entity) presenter.processRequest("login",parameter);
			
			if(x!=null){
				/*Esito del log in positivo*/
				if(x instanceof Amministratore){
					
				
				}
				else if(x instanceof SupervisoreAgenzia){
					
				}
				else if(x instanceof SupervisoreSede){
					
				}
				else if(x instanceof Operatore){
				    FXMLParameter.setTitolo("Operatore");
				    FXMLParameter.setRidimensionabile(false);
					this.chiudiFinestra();
					ReturnableStage stager= (ReturnableStage) presenter.processRequest("MostraSchermataOperatore",FXMLParameter);
					stager.show();
				}
			
			}
			else{
			
			}
		} catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NoSuchMethodException| SecurityException | IllegalArgumentException
				| InvocationTargetException | NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void chiudiFinestra(){
		 stage.close();
	 }
	@Override
	public void setStage(Stage stage){
		this.stage=stage;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
	}
}
