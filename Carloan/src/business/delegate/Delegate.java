package business.delegate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import utility.ReaderXML;

public class Delegate {
    private ReaderXML reader;
    private Object  model;
    private ArrayList<String> service_method;
    
    public Delegate(String path){
    	reader= new ReaderXML(path);
    }
    public Object doTask(String serviceName,Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
    	service_method = reader.read(serviceName);
    	model =  Class.forName(service_method.get(0)).newInstance();
    	Method method = model.getClass().getMethod(service_method.get(1), parameter.getClass());
    	return method.invoke(model, parameter);
    }
}
