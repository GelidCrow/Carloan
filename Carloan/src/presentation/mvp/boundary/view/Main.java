package presentation.mvp.boundary.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (Parent)FXMLLoader.load(Main.class.getResource("/markup/Login.fxml"));
			Scene scene= new Scene(root,350,200);
			primaryStage.setScene(scene);
			primaryStage.show();	
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
