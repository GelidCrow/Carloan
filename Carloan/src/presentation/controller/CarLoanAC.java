package presentation.controller;
import utility.ReaderXML;
import business.command.*;
public class CarLoanAC implements ApplicationController {

	@Override
	public Object handleRequest(String request, Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ReaderXML reader_xml=new ReaderXML("presentation/AC.xml");
		Command command=(Command) Class.forName(reader_xml.read(request)).newInstance();
		command.execute();
		return null;
	}
}
