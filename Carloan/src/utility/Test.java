package utility;

import java.awt.FlowLayout;
import java.awt.Panel;
import java.sql.Date;
import java.time.LocalDate;









import javax.swing.JPanel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test extends Application{
	DatePicker datepicker= new DatePicker();

	final ToggleGroup group = new ToggleGroup();
	public Test(){
		
		 
	}


	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		RadioButton rb1 = new RadioButton("Home");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		
		rb1.setUserData("Ciao");

		RadioButton rb2 = new RadioButton("Calendar");
		rb2.setToggleGroup(group);
		rb2.setUserData("Ciao2");
		
		 datepicker.setOnAction(e -> {
			 LocalDate date = datepicker.getValue();
			 Date dat= Date.valueOf(date);
	         System.out.println("Selected date: " );
	   });

		
		Scene scene= new Scene(root,300,200);
		
		arg0.setScene(scene);
		
		arg0.show();
		
	}
}
