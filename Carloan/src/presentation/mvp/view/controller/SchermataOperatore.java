package presentation.mvp.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import presentation.mvp.view.Presenter;
import utility.ParametriFXML;

public class SchermataOperatore extends Schermata{

	private Presenter presenter ;
	
	private ParametriFXML FXMLParameter;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		presenter=new Presenter();
	}
	
	
	
}
