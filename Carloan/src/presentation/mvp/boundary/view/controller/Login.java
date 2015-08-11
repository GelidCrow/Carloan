package presentation.mvp.boundary.view.controller;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import presentation.mvp.boundary.view.Presenter;
import utility.Crittografia;
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
		
		parameter.add(txtUsername.getText());
		
		try {
			
			parameter.add(Crittografia.CriptaPassword(txtPsw.getText()));
			presenter.processRequest("ModelLogin",parameter);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
