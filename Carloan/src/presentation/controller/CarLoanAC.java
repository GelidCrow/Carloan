package presentation.controller;
import utility.ReaderXML;
import business.command.*;
public class CarLoanAC implements ApplicationController {

	@Override
	public Object handleRequest(String request, Object parameter) {
		ReaderXML reader_xml=new ReaderXML("presentation/AC.xml");
		Command command=CommandFactory.getInstance(reader_xml.read(request));
		command.execute();
		return null;
	}
}
