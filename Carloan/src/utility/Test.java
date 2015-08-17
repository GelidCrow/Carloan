package utility;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test extends Application{
	DatePicker datepicker= new DatePicker();
	public Test(){
		
		 
	}


	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	
		 datepicker.setOnAction(e -> {
			 LocalDate date = datepicker.getValue();
			 String date1= date.toString();
	         System.out.println("Selected date: " + date1);
	   });
		StackPane root= new StackPane();
		root.getChildren().add(datepicker);
		
		Scene scene= new Scene(root,300,200);
		
		arg0.setScene(scene);
		
		arg0.show();
		
	}
}
