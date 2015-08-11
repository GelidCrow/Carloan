package presentation.mvp.boundary.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("CarLoan");
		initRootLayout();
	}
public void initRootLayout(){
	try{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("Login.fxml"));
		Scene scene=new Scene(loader.load());
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	catch(IOException e){
		e.printStackTrace();
	}
}
	public static void main(String[] args) {
		launch(args);
	}
}
