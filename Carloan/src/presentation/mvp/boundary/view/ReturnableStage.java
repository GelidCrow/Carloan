package presentation.mvp.boundary.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.mvp.boundary.view.controller.Schermata;

public class ReturnableStage extends Stage {
   
	private Scene scene;
    private Parent root;
    private List<Object> parameter;

    @SuppressWarnings("unchecked")
	public ReturnableStage(String schemeResource,Object parameter) {
    		parameter= (ArrayList<Object>) parameter;
       
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
			
			this.setTitle((String)this.parameter.get(0));
			
			this.setResizable((boolean)this.parameter.get(1));
			
			setScene(scene);	
    }
}