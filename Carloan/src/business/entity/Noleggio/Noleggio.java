package business.entity.Noleggio;

import java.util.Date;
import java.util.List;

public class Noleggio {
	private String IDNoleggio; 
	private Date InizioNoleggio;
	private Date FineNoleggio;
	private Date rientro;
	private int kmRientro;
	private int KmPercorsi;
	private int kmBase;
	private List<Optional> optional;
	private String LuogoRestituzione;
	private String IDAuto;
	private int numeroSettimane;
	private int numeroGiorni;
	private int numero_chilometri;
	private int numero_conducenti;
	private StatoNoleggio stato;
	
	public Noleggio(String iDNoleggio, Date inizioNoleggio, Date fineNoleggio,
			Date rientro, int kmRientro, int kmPercorsi, int kmBase,
			List<Optional> optional, String luogoRestituzione, String iDAuto,
			int numeroSettimane, int numeroGiorni, int numero_chilometri,
			int numero_conducenti, StatoNoleggio stato) 
	{
		IDNoleggio = iDNoleggio;
		InizioNoleggio = inizioNoleggio;
		FineNoleggio = fineNoleggio;
		this.rientro = rientro;
		this.kmRientro = kmRientro;
		KmPercorsi = kmPercorsi;
		this.kmBase = kmBase;
		this.optional = optional;
		LuogoRestituzione = luogoRestituzione;
		IDAuto = iDAuto;
		this.numeroSettimane = numeroSettimane;
		this.numeroGiorni = numeroGiorni;
		this.numero_chilometri = numero_chilometri;
		this.numero_conducenti = numero_conducenti;
		this.stato = stato;
	}
	
	
	
	
	
	
}
