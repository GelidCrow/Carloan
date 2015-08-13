package business.delegate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import business.model.Model;
import business.model.checker.Checker;
import utility.ReaderXML;

public class Delegate {
    private ReaderXML reader;
    private Model  model;
    private Checker checker;
    private ArrayList<String> service_method;
    private Method method;
	private Object result;
    
    public Delegate(String path){
    	reader= new ReaderXML(path);
    }
    public Object doTask(String serviceName,Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
    	service_method = reader.read(serviceName);
    	if(Class.forName(service_method.get(0)).newInstance() instanceof Checker){
    		checker = (Checker) Class.forName(service_method.get(0)).newInstance();
    		method = checker.getClass().getMethod(service_method.get(1),Object.class);
    		result=  method.invoke(checker, parameter);
    		
    	}
    	else {
    		model = (Model) Class.forName(service_method.get(0)).newInstance();
    		method = model.getClass().getMethod(service_method.get(1), Object.class);
    		result=  method.invoke(model, parameter);
    	}
    	return result;
    }
}
