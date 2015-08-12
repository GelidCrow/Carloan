package presentation.mvp.boundary.view.controller;



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
import presentation.mvp.boundary.view.Presenter;
import presentation.mvp.boundary.view.ReturnableStage;
import utility.Crittografia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Login extends Schermata implements Initializable{
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPsw;
	@FXML
	private Button btnLogin;
	
	Presenter presenter ;
	
	
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
					//E' un amministratore
					System.out.println("amministratore");
				}
				else if(x instanceof SupervisoreAgenzia){
					
				}
				else if(x instanceof SupervisoreSede){
					
				}
				else if(x instanceof Operatore){
					
				}
			
			}
			else{
			
			}
		} catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NoSuchMethodException| SecurityException | IllegalArgumentException
				| InvocationTargetException | NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void btnAltroLogin(ActionEvent e){
		try {
			ReturnableStage stage= (ReturnableStage) presenter.processRequest("MostraLogin", null);
			stage.show();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
	}
	
	public static void main(String[] args){
	Login l=new Login();
	l.presenter=new Presenter();
	l.btnLogin(null);
	
	}
}
