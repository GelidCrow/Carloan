package presentation.mvp.boundary.view.controller;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import business.entity.Entity;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import presentation.mvp.boundary.view.Presenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Login implements Initializable{
	@FXML
	private Text txtUsername;
	@FXML
	private Text txtPsw;
	@FXML
	private Button btnLogin;
	
	Presenter presenter ;
	
	
	@FXML
	public void btnLogin(ActionEvent e){
		List<String> parameter = new ArrayList<String>();
		
		parameter.add(/*txtUsername.getText()*/"Amm1");
		
		try {
			
			parameter.add(/*Crittografia.CriptaPassword(txtPsw.getText())*/"EE774405AFA3E5CBB5279D8E77D3C840DEEE46372CEF95B511CFC8B259858031");
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
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e1) {
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
