package presentation.controller;

import java.lang.reflect.InvocationTargetException;

public class CarLoanFC implements FrontController {
	private CarLoanAC carloanAC;
	@Override
	public Object processRequest(String request, Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {   
		carloanAC = (CarLoanAC) ApplicationControllerFactory.getApplicationController();
		return carloanAC.handleRequest(request, parameter);
	}

}
