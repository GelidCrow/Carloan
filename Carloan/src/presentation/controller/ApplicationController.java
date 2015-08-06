package presentation.controller;

import java.lang.reflect.InvocationTargetException;

public interface ApplicationController {
	public Object handleRequest(String request,Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException;
}
