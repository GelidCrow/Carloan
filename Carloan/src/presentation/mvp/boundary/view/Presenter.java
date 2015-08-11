package presentation.mvp.boundary.view;

import java.lang.reflect.InvocationTargetException;
import presentation.controller.CarLoanFC;
import presentation.controller.FrontControllerFactory;
/**
 * <p>Chiama il frontController con la richiesta.</p>
 *
 */
public class Presenter {
	
	private CarLoanFC carloanFC;
	
	public Object processRequest(String request, Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		carloanFC = (CarLoanFC)FrontControllerFactory.getFrontController();
		return carloanFC.processRequest(request, parameter);
	}	
}
