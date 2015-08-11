package presentation.mvp.boundary.view.controller;


import java.net.URL;
import java.util.ResourceBundle;

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
	   
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
