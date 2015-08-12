package presentation.mvp.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;

public class ReturnableStage extends Stage {
   
	private Scene scene;
    private Parent root;
    private ParametriFXML param;
    private  Schermata schermata;

	public ReturnableStage(String schemeResource,Object parameter) {
    		param= (ParametriFXML) parameter;
       
    		
        	FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource(schemeResource));
			
			try {
				root= loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.schermata = loader.getController();
           	
            scene = new Scene(root);
            
			this.setTitle(param.getTitolo());
			
			this.setResizable(param.isRidimensionabile());
			
			setScene(scene);	
			
			//this.schermata.setStage(this); <=cosi non funziona, da null pointer. 
    }
	/**
	 * <p>Serve a settare lo stage in modo tale da non perderlo per fare delle cose sulla finestra, per sempio nel login posso chiudere la finestra dopo l'autenticazione</p>
	 * @param stage
	 */
	public void setStageToWindow(Stage stage){
		schermata.setStage(stage);
	}
    public void showWindow() {
    	schermata.getStage().showAndWait();
    }
}