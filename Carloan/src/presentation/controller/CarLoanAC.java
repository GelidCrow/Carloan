package presentation.controller;
import java.lang.reflect.InvocationTargetException;
import business.delegate.Delegate;





public class CarLoanAC implements ApplicationController {
	private Delegate delegate ; 
	@Override
	public Object handleRequest(String request, Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		delegate= new Delegate("../AC.xml");
		return delegate.doTask(request, parameter);
	}
}