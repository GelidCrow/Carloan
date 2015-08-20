package presentation.mvp.view.controller;

import integration.DAO.connection.Connection;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import presentation.mvp.view.Presenter;
import utility.ParametriFXML;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
	
	protected Schermata chiamante;
	
	protected void nascondiFinestra(){
		stage.hide();
	}
	protected void chiudiFinestra(){
		 stage.close();
	 }
	
	public void setStage(Stage stage){
		stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>()
		        {
		            @Override
		            public void handle(WindowEvent window)
		            {
		                try {
							Connection.chiudiConnessione();
						} catch (SQLException e) {
							e.printStackTrace();
						}
		            }
		        });
		this.stage=stage;
	}
	public Stage getStage(){
		return stage;
	}
	
	public void setChiamante(Schermata chiamante){
		this.chiamante=chiamante;
	}
	public Schermata getChiamante(){
		return chiamante;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();

		FXMLParameter = new ParametriFXML(null,false);	
	}
}
