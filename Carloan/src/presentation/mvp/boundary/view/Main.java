package presentation.mvp.boundary.view;

import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stage;
	private Presenter presenter;
	
	@Override
	public void start(Stage stage) {
		presenter= new Presenter();
		initRootLayout();
	}
	
	public void initRootLayout(){
		try {
			stage= (Stage) presenter.processRequest("MostraLogin", null);
			this.stage.setTitle("CarLoan");
			this.stage.setResizable(false);
			this.stage.show();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
