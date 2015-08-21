package business.entity.Auto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import business.entity.Auto.Fascia.*;
import business.entity.Auto.manutenzione.Manutenzione;
public class Autoveicolo {
	
	private String IDauto;
	private String Targa;
	private String Marca;
	private String Modello;
	private String AlimPrincipale;// può essere un enum, ma comunque non potrebbero avere attributi propri un domani
	private String AlimSec;
	private String Colore;
	private String Cambio;
	private LocalDate Immatricolazione;
	private int Cilindrata;
	private int potenza;
	private int NroPosti;
	private String NroTelaio;
	private Disponibilita disponibilita;
	private int UltimoKm;
	private int CapPortaBagnagli;
	private String Note;
	private Image Immagine;
	private LocalDate DataScadAssic;
	private Fascia fascia;
	private Danni danni;
	private String CodiceSedDisp;
	private String OptionalAuto;
	private List<Manutenzione> manutenzioni;
	private float prezzo;
	
	private StringProperty IDautoT;
	private StringProperty TargaT;
	private StringProperty MarcaT;
	private StringProperty ModelloT;
	private StringProperty AlimPrincipaleT;
	private StringProperty AlimSecT;
	private StringProperty ColoreT;
	private StringProperty CambioT;
	private ObjectProperty<LocalDate> ImmatricolazioneT;
	private IntegerProperty CilindrataT;
	private IntegerProperty potenzaT;
	private IntegerProperty NroPostiT;
	private StringProperty NroTelaioT;
	private ObjectProperty<Disponibilita> disponibilitaT;
	private IntegerProperty UltimoKmT;
	private IntegerProperty CapPortaBagnagliT;
	private StringProperty NoteT;
	private ObjectProperty<Image> ImmagineT;
	private ObjectProperty<LocalDate> DataScadAssicT;
	private ObjectProperty<Fascia> fasciaT;
	private ObjectProperty<Danni> danniT;
	private StringProperty CodiceSedDispT;
	private StringProperty OptionalAutoT;
	private StringProperty manutenzioniT;
	private FloatProperty prezzoT;
	

	
	public Autoveicolo(String iDauto, String targa, String marca,
			String modello, String alimPrincipale, String alimSec,
			String colore, String cambio, LocalDate immatricolazione,
			int cilindrata, int potenza, int nroPosti, String nroTelaio,
			Disponibilita disponibilita, int ultimoKm, int capPortaBagnagli,
			String note, Image immagine, LocalDate dataScadAssic, Fascia fascia,
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
		this.IDautoT=new SimpleStringProperty(this.IDauto);
		this.TargaT=new SimpleStringProperty(this.Targa);
		this.MarcaT=new SimpleStringProperty(this.Marca);
		this.ModelloT=new SimpleStringProperty(this.Modello);
		this.AlimPrincipaleT=new SimpleStringProperty(this.AlimPrincipale);
		this.AlimSecT=new SimpleStringProperty(this.AlimSec);
		this.ColoreT=new SimpleStringProperty(this.Colore);
		this.CambioT=new SimpleStringProperty(this.Cambio);
		this.ImmatricolazioneT=new SimpleObjectProperty<LocalDate>(this.Immatricolazione);
		this.CilindrataT=new SimpleIntegerProperty(this.Cilindrata);
		this.potenzaT=new SimpleIntegerProperty(this.potenza);
		this.NroPostiT=new SimpleIntegerProperty(this.NroPosti);
		this.NroTelaioT=new SimpleStringProperty(this.NroTelaio);
		this.disponibilitaT=new SimpleObjectProperty<Disponibilita>(this.disponibilita);
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
