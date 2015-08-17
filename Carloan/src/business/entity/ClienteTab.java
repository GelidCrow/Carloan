package business.entity;

import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClienteTab {
	private StringProperty nome;
	private StringProperty cognome;
	private StringProperty sesso;
	private ObjectProperty<Date> DataEmissPatente;
	private ObjectProperty<Date>  datanascita;
	private StringProperty Indirizzo;
	private StringProperty codFiscale;
	private StringProperty numCell;
	private StringProperty numTel;
	private StringProperty PatenteGuida;
	private ObjectProperty<Date>  DataScadPatente;
	private StringProperty PartitaIva;
	private StringProperty Email;
	
	public ClienteTab(Cliente cliente){
		this.nome = new SimpleStringProperty(cliente.getNome());
        this.cognome = new SimpleStringProperty(cliente.getCognome());
        this.sesso= new SimpleStringProperty(cliente.getSesso());
        this.DataEmissPatente =new SimpleObjectProperty<Date>(cliente.getDataEmissPatente());
        this.datanascita =new SimpleObjectProperty<Date>(cliente.getDatanascita());
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

	}
	
}
