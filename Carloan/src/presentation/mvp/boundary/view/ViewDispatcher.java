package presentation.mvp.boundary.view;

import java.lang.reflect.InvocationTargetException;
import utility.ReaderXML;

public class ViewDispatcher {
	private ReaderXML reader;
	private String srcFXML;
	private ReturnableStage stage;
	
	public ViewDispatcher(String path){
		reader= new ReaderXML(path);
	}
	
	public Object dispatch(String serviceName,Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
	    	srcFXML= reader.read_UI(serviceName);
	    	stage= new ReturnableStage(srcFXML,parameter);
	    	return stage;
	}	
}
