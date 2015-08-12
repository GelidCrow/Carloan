package presentation.mvp.boundary.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.mvp.boundary.view.controller.Schermata;

public class ReturnableStage extends Stage {
   
	protected Scene scene;
    private Parent root;


    public ReturnableStage(String schemeResource,Object parameter) {
       
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
			
			setScene(scene);
    }
}