package utility;

import java.lang.reflect.InvocationTargetException;

import business.model.Exception.CommonException;
import javafx.stage.Modality;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.ReturnableStage;

public class Finestra {
	/**
	 * <p>permette di visualizzare finestre basandosi sul presenter passato, sui parametri di quella finestra </p>
	 * @param presenter
	 * @param FXMLParameter
	 * @param operation
	 * @param modality
	 * @throws NomeClienteNonValido 
	 */
	
	public static void visualizzaFinestra(Presenter presenter,ParametriFXML FXMLParameter,String schermata,Modality modality){
		ReturnableStage stager;
		try {
			stager = (ReturnableStage) presenter.processRequest(schermata,FXMLParameter);
			stager.setStageToWindow(stager);
			stager.showWindow(modality);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
