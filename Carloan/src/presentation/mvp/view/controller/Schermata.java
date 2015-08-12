package presentation.mvp.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import presentation.mvp.view.Presenter;
import utility.ParametriFXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
/**
 * <p>Tutti i controller estendono schermata</p>
 * @author francesco
 *
 */
public abstract class Schermata implements Initializable{
	

	protected Presenter presenter ;
	
	protected  ParametriFXML FXMLParameter;

	/**
	 * <p>Utile per gestire la finestra grafica</p>
	 */
	protected Stage stage;
	
	
	protected void chiudiFinestra(){
		 stage.close();
	 }
	
	public void setStage(Stage stage){
		this.stage=stage;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();

		FXMLParameter = new ParametriFXML(null,false);
	}
	

}
