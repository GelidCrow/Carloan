package presentation.mvp.view.controller.generale.autoveicolo;

import java.net.URL;
import java.util.ResourceBundle;

import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import presentation.mvp.view.Presenter;
import utility.ParametriFXML;

public class Modifica_Autoveicolo extends Nuovo_Autoveicolo{
	public void initialize(URL arg0, ResourceBundle arg1) {
	presenter=new Presenter();
	FXMLParameter=new ParametriFXML(null, false);
	}
	public void initData(Entity entity){
		Autoveicolo a=(Autoveicolo)entity;
		
	 
 }
}
