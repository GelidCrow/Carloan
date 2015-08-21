package business.entity.Noleggio;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property. IntegerProperty;
import javafx.beans.property. ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Noleggio.Optional.Optional;
/*diocane*/
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
	private Integer LuogoRestituzione;
	private int numeroSettimane;
	private int numeroGiorni;
	private int numeroChilometri;
	private StatoNoleggio stato;
	private Autoveicolo auto;
	private Contratto contratto;

	private  IntegerProperty IDNoleggioT;
	private  IntegerProperty kmRientroT;
	private  IntegerProperty kmPercorsiT;
	private  IntegerProperty KmBaseT;
	private  IntegerProperty LuogoRestituzioneT;
	private  IntegerProperty numeroSettimaneT;
	private  IntegerProperty numeroGiorniT;
	private  IntegerProperty numeroChilometriT;
	private  ObjectProperty<LocalDate> InizioNoleggioT;
	private  ObjectProperty<LocalDate> FineNoleggioT;
	private  ObjectProperty<LocalDate> rientroT;
	private  ObjectProperty<LocalDate> ritiroT;
	private  ObjectProperty<List<Optional>> optionalT;
	private  ObjectProperty<StatoNoleggio> statoT;
	private  ObjectProperty<Autoveicolo> autoT;
	private  ObjectProperty<Contratto> contrattoT;
	
	public void setRitiro(LocalDate ritiro) {
		this.ritiro = ritiro;
		this.ritiroT=  new SimpleObjectProperty<LocalDate>(ritiro);
	}
	public LocalDate getRitiro() {
		return ritiro;
	}
	public ObjectProperty<LocalDate> getRitiroT() {
		return ritiroT;
	}
	
	public void setIDNoleggio(Integer iDNoleggio) {
		IDNoleggio = iDNoleggio;
		IDNoleggioT= new  SimpleIntegerProperty(iDNoleggio);
	}
	public void setInizioNoleggio(LocalDate inizioNoleggio) {
		InizioNoleggio = inizioNoleggio;
		this.InizioNoleggioT = new  SimpleObjectProperty<LocalDate>(inizioNoleggio);
	}
	public void setFineNoleggio(LocalDate fineNoleggio) {
		FineNoleggio = fineNoleggio;
		this.FineNoleggioT = new  SimpleObjectProperty<LocalDate>(fineNoleggio);
	}
	public void setRientro(LocalDate rientro) {
		this.rientro = rientro;
		this.rientroT = new  SimpleObjectProperty<LocalDate>(rientro);
	}
	public void setKmRientro(int kmRientro) {
		this.kmRientro = kmRientro;
		this.kmRientroT= new  SimpleIntegerProperty(kmRientro);
	}
	public void setKmPercorsi(int kmPercorsi) {
		KmPercorsi = kmPercorsi;
		this.kmPercorsiT= new  SimpleIntegerProperty(kmPercorsi);
	}
	
	public void setKmBase(int kmBase) {
		this.kmBase = kmBase;
		this.KmBaseT= new  SimpleIntegerProperty(kmBase);
	}

	public void setLuogoRestituzione(Integer luogoRestituzione) {
		LuogoRestituzione = luogoRestituzione;
		this.LuogoRestituzioneT= new  SimpleIntegerProperty(kmRientro);
	}
	public void setNumeroSettimane(int numeroSettimane) {
		this.numeroSettimane = numeroSettimane;
		this.numeroSettimaneT= new  SimpleIntegerProperty(numeroSettimane);
	}
	public void setNumeroGiorni(int numeroGiorni) {
		this.numeroGiorni = numeroGiorni;
		this.numeroGiorniT= new  SimpleIntegerProperty(numeroGiorni);
	}
	public void setNumeroChilometri(int numeroChilometri) {
		this.numeroChilometri = numeroChilometri;
		this.numeroGiorniT= new  SimpleIntegerProperty(numeroChilometri);
	}
	public void setStato(StatoNoleggio stato) {
		this.stato = stato;
		this.statoT= new  SimpleObjectProperty<StatoNoleggio>(stato);
	}
	public void setAuto(Autoveicolo auto) {
		this.auto = auto;
	}
	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}
	public void setOptional(List<Optional> optional) {
		this.optional = optional;
	}
	public Integer getIDNoleggio() {
		return IDNoleggio;
	}
	public LocalDate getInizioNoleggio() {
		return InizioNoleggio;
	}
	public LocalDate getFineNoleggio() {
		return FineNoleggio;
	}
	public LocalDate getRientro() {
		return rientro;
	}
	public int getKmRientro() {
		return kmRientro;
	}
	public int getKmPercorsi() {
		return KmPercorsi;
	}
	public int getKmBase() {
		return kmBase;
	}
	public List<Optional> getOptional() {
		return optional;
	}
	public Integer getLuogoRestituzione() {
		return LuogoRestituzione;
	}
	public int getNumeroSettimane() {
		return numeroSettimane;
	}
	public int getNumeroGiorni() {
		return numeroGiorni;
	}
	public int getNumeroChilometri() {
		return numeroChilometri;
	}
	public StatoNoleggio getStato() {
		return stato;
	}
	public Autoveicolo getAuto() {
		return auto;
	}
	public Contratto getContratto() {
		return contratto;
	}
	public IntegerProperty getIDNoleggioT() {
		return IDNoleggioT;
	}
	public IntegerProperty getKmRientroT() {
		return kmRientroT;
	}
	public IntegerProperty getKmPercorsiT() {
		return kmPercorsiT;
	}
	public IntegerProperty getKmBaseT() {
		return KmBaseT;
	}
	public IntegerProperty getLuogoRestituzioneT() {
		return LuogoRestituzioneT;
	}
	public IntegerProperty getNumeroSettimaneT() {
		return numeroSettimaneT;
	}
	public IntegerProperty getNumeroGiorniT() {
		return numeroGiorniT;
	}
	public IntegerProperty getNumeroChilometriT() {
		return numeroChilometriT;
	}
	public ObjectProperty<LocalDate> getInizioNoleggioT() {
		return InizioNoleggioT;
	}
	public ObjectProperty<LocalDate> getFineNoleggioT() {
		return FineNoleggioT;
	}
	public ObjectProperty<LocalDate> getRientroT() {
		return rientroT;
	}
	public ObjectProperty<List<Optional>> getOptionalT() {
		return optionalT;
	}
	public ObjectProperty<StatoNoleggio> getStatoT() {
		return statoT;
	}
	public ObjectProperty<Autoveicolo> getAutoT() {
		return autoT;
	}
	public ObjectProperty<Contratto> getContrattoT() {
		return contrattoT;
	}
	
	
}
