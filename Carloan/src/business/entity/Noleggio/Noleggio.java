package business.entity.Noleggio;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property. IntegerProperty;
import javafx.beans.property. ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Luoghi.Sede;
import business.entity.Noleggio.Optional.Optional;
import business.entity.pagamento.Pagamento;

public class Noleggio extends Entity{
	
	private Integer IDNoleggio; 
	private LocalDate InizioNoleggio;
	private LocalDate FineNoleggio;
	private LocalDate rientro;
	private LocalDate ritiro;
	private int kmRientro;
	private int KmPercorsi;
	private int kmBase;
	private List<Optional> optional;
	private List<Multa> multa;
	private Sede LuogoRestituzione;
	private int numeroSettimane;
	private int numeroGiorni;
	private int numeroChilometri;
	private StatoNoleggio stato;
	private String TargaAuto;
	private int Idcontratto;
	private String note;
	private Pagamento pagamento;


	
	
	


}
