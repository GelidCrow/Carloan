package presentation.mvp.boundary.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stage;
	private Presenter presenter;
	private List<Object> parameter;
	
	@Override
	public void start(Stage stage) {
		parameter= new ArrayList<Object>();
		parameter.add("Login");
		parameter.add(false);
		
		presenter= new Presenter();
		initRootLayout();
	}
	
	public void initRootLayout(){
		try {
			stage= (Stage) presenter.processRequest("MostraLogin", parameter);
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
