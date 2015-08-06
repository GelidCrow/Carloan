package presentation.controller;

public interface ApplicationController {
	public Object handleRequest(String request,Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}
