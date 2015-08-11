package presentation.mvp.boundary.view;

import java.io.IOException;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private VBox vbox;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("CarLoan");
		initRootLayout();
	}
public void initRootLayout(){
	try{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("markup/Login.fxml"));
		vbox=(VBox)loader.load();
		Scene scene=new Scene(vbox);
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
