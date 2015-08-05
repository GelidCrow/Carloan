package business.entity.Noleggio.Optional;

public class Seggiolino extends OptionalAuto{
/**
 * Il numero di seggiolini 
 */
private int numero;
	public Seggiolino(float prezzo, String descrizione,int numero) {
		super(prezzo, descrizione);
	this.numero=numero;
	}
public int getnumero(){
	return numero;
	
}
}
