package utility;

import java.lang.reflect.InvocationTargetException;

import javafx.stage.Modality;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.ReturnableStage;

public class Finestra {
	
	
	public static void visualizzaFinestra(Presenter presenter,ParametriFXML FXMLParameter,String operation,Modality modality){
		ReturnableStage stager;
		try {
			stager = (ReturnableStage) presenter.processRequest(operation,FXMLParameter);
			stager.setStageToWindow(stager);
			stager.showWindow(modality);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
