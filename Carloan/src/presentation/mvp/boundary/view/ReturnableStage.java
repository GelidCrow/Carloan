package presentation.mvp.boundary.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

import presentation.mvp.boundary.view.controller.Schermata;

public class ReturnableStage extends Stage {
    protected Scene scene;
    private Parent root;


    public ReturnableStage(String schemeResource,Object parameter) {
       
        	FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource(schemeResource));
            Schermata schermata = loader.getController();
     
			
			scene = new Scene(root);
			
			setScene(scene);
    }
}