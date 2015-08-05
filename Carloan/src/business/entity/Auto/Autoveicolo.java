package business.entity.Auto;

import java.util.Date;
import java.util.List;

import javafx.scene.image.Image;
import business.entity.Manutenzione.*;
import business.entity.Auto.Fascia.*;
public class Autoveicolo {
	private String IDauto;
	private String Targa;
	private String Marca;
	private String Modello;
	private String AlimPrincipale;// pu� essere un enum, ma comunque non potrebbero avere attributi propri un domani
	private String AlimSec;
	private String Colore;
	private String Cambio;
	private Date Immatricolazione;
	private int Cilindrata;
	private int potenza;
	private int NroPosti;
	private String NroTelaio;
	private Disponibilita disponibilita;
	private int UltimoKm;
	private int CapPortaBagnagli;
	private String Note;
	private Image Immagine;
	private Date DataScadAssic;
	private Fascia fascia;
	private Danni danni;
	private String CodiceSedDisp;
	private String OptionalAuto;
	private List<Manutenzione> manutenzioni;
	private float prezzo;

	
	public Autoveicolo(String iDauto, String targa, String marca,
			String modello, String alimPrincipale, String alimSec,
			String colore, String cambio, Date immatricolazione,
			int cilindrata, int potenza, int nroPosti, String nroTelaio,
			Disponibilita disponibilita, int ultimoKm, int capPortaBagnagli,
			String note, Image immagine, Date dataScadAssic, Fascia fascia,
			Danni danni, String codiceSedDisp, String optionalAuto,
			List<Manutenzione> manutenzioni, float prezzo) 
	{
		IDauto = iDauto;
		Targa = targa;
		Marca = marca;
		Modello = modello;
		AlimPrincipale = alimPrincipale;
		AlimSec = alimSec;
		Colore = colore;
		Cambio = cambio;
		Immatricolazione = immatricolazione;
		Cilindrata = cilindrata;
		this.potenza = potenza;
		NroPosti = nroPosti;
		NroTelaio = nroTelaio;
		this.disponibilita = disponibilita;
		UltimoKm = ultimoKm;
		CapPortaBagnagli = capPortaBagnagli;
		Note = note;
		Immagine = immagine;
		DataScadAssic = dataScadAssic;
		this.fascia = fascia;
		this.danni = danni;
		CodiceSedDisp = codiceSedDisp;
		OptionalAuto = optionalAuto;
		this.manutenzioni= manutenzioni;
		this.prezzo = prezzo;
	}

	public String getIDauto() {
		return IDauto;
	}


	public void setIDauto(String iDauto) {
		IDauto = iDauto;
	}


	public String getTarga() {
		return Targa;
	}


	public void setTarga(String targa) {
		Targa = targa;
	}


	public String getMarca() {
		return Marca;
	}


	public void setMarca(String marca) {
		Marca = marca;
	}


	public String getModello() {
		return Modello;
	}


	public void setModello(String modello) {
		Modello = modello;
	}


	public String getAlimPrincipale() {
		return AlimPrincipale;
	}


	public void setAlimPrincipale(String alimPrincipale) {
		AlimPrincipale = alimPrincipale;
	}


	public String getAlimSec() {
		return AlimSec;
	}


	public void setAlimSec(String alimSec) {
		AlimSec = alimSec;
	}


	public String getColore() {
		return Colore;
	}


	public void setColore(String colore) {
		Colore = colore;
	}


	public String getCambio() {
		return Cambio;
	}


	public void setCambio(String cambio) {
		Cambio = cambio;
	}


	public Date getImmatricolazione() {
		return Immatricolazione;
	}


	public void setImmatricolazione(Date immatricolazione) {
		Immatricolazione = immatricolazione;
	}


	public int getCilindrata() {
		return Cilindrata;
	}


	public void setCilindrata(int cilindrata) {
		Cilindrata = cilindrata;
	}


	public int getPotenza() {
		return potenza;
	}


	public void setPotenza(int potenza) {
		this.potenza = potenza;
	}


	public int getNroPosti() {
		return NroPosti;
	}


	public void setNroPosti(int nroPosti) {
		NroPosti = nroPosti;
	}


	public String getNroTelaio() {
		return NroTelaio;
	}


	public void setNroTelaio(String nroTelaio) {
		NroTelaio = nroTelaio;
	}


	public Disponibilita getDisponibilita() {
		return disponibilita;
	}


	public void setDisponibilita(Disponibilita disponibilita) {
		this.disponibilita = disponibilita;
	}


	public int getUltimoKm() {
		return UltimoKm;
	}


	public void setUltimoKm(int ultimoKm) {
		UltimoKm = ultimoKm;
	}


	public int getCapPortaBagnagli() {
		return CapPortaBagnagli;
	}


	public void setCapPortaBagnagli(int capPortaBagnagli) {
		CapPortaBagnagli = capPortaBagnagli;
	}


	public String getNote() {
		return Note;
	}


	public void setNote(String note) {
		Note = note;
	}


	public Image getImmagine() {
		return Immagine;
	}


	public void setImmagine(Image immagine) {
		Immagine = immagine;
	}


	public Date getDataScadAssic() {
		return DataScadAssic;
	}


	public void setDataScadAssic(Date dataScadAssic) {
		DataScadAssic = dataScadAssic;
	}


	public Fascia getFascia() {
		return fascia;
	}


	public void setFascia(Fascia fascia) {
		this.fascia = fascia;
	}


	public Danni getDanni() {
		return danni;
	}


	public void setDanni(Danni danni) {
		this.danni = danni;
	}


	public String getCodiceSedDisp() {
		return CodiceSedDisp;
	}


	public void setCodiceSedDisp(String codiceSedDisp) {
		CodiceSedDisp = codiceSedDisp;
	}


	public String getOptionalAuto() {
		return OptionalAuto;
	}


	public void setOptionalAuto(String optionalAuto) {
		OptionalAuto = optionalAuto;
	}


	public List<Manutenzione> getManutenzioni() {
		return manutenzioni;
	}


	public void setManutenzioni(List<Manutenzione> manutenzioni) {
		this.manutenzioni = manutenzioni;
	}


	public float getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}


	
	
}