package utility;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReaderXML {
	private String percorsoFile;
	
	public  ReaderXML(String percorso){
		this.percorsoFile=percorso;
	}
	public  ArrayList<String> read(String nomeServizio) {
		XMLHandler myhandler=new XMLHandler(nomeServizio);
		SAXParser myparser;
		try {
			myparser = SAXParserFactory.newInstance().newSAXParser();
			myparser.parse(percorsoFile, myhandler);
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String> coppia=new ArrayList<String>();
		coppia.add(myhandler.getclassName());
		coppia.add(myhandler.getmethodName());
		return  coppia;
	}
	
	/**
	 * Inner Class who defines a defaulthandler for the sax xml parser
	 * @author Gelidcrow
	 *
	 */
	class XMLHandler extends DefaultHandler{
		
		boolean should_read=false;
		boolean should_read_class=false;
		boolean should_read_method=false;
		String className,methodName,nomeservizio;
		
		 XMLHandler(String nomeServizio) {
			this.nomeservizio=nomeServizio;
		}
		
		String getclassName(){
			return className;
		}
		String getmethodName(){
			return methodName;
		}
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if(should_read_class){
				className=new String(ch,start,length);
				should_read_class=false;
			}
			else if(should_read_method){
				methodName=new String(ch,start,length);
				should_read_method=false;
				should_read=false;
			}
			
			
		}

		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void startDocument() throws SAXException {
		System.out.println("Starting document parsing");
			
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes atts) throws SAXException {
		if(qName.equals("call")){
		for(int i=0;i<atts.getLength();i++){
			if(atts.getQName(i).equals("id"))
				if(atts.getValue(i).equals(this.nomeservizio))
					should_read=true;
		}
		}
		else if(qName.equals("class") &&should_read)
			should_read_class=true;
		else if(qName.equals("method") && should_read)
			should_read_method=true;
		}


	}
}

