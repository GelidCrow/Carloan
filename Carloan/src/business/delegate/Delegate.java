package business.delegate;

import java.util.ArrayList;

import utility.ReaderXML;

public class Delegate {
    private ReaderXML reader= new ReaderXML(null);
    
    private Model  model;
    
    private ArrayList<String> service_method;
    
    public void doTask(String serviceName) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
    	service_method = reader.read(serviceName);
    	model = Class.forName(service_method.get(0)).newInstance();
    	
    	
    }

    
}
