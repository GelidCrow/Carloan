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
            Schermata schermata = loader.getController();
     
			scene = new Scene(root);
			
			this.setTitle(param.getTitolo());
			
			this.setResizable(param.isRidimensionabile());
			
			setScene(scene);	
    }
}