package business.entity.pagamento;

import java.util.Date;

import business.entity.Cliente;

public class CartaDiCredito extends Pagamento{
	private String IDCliente ; 
	private Date dataScadenza;
	private String IBAN;
	private String NueroCarta;
	private String Circuito;
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CartaDiCredito(float depositoCauzinale, float importo,
			float detrazioneAggiuntiva) {
			super(depositoCauzinale, importo, detrazioneAggiuntiva);
	}
	
	public String getIDCliente() {
		return IDCliente;
	}
	public void setIDCliente(String iDCliente) {
		IDCliente = iDCliente;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public String getNueroCarta() {
		return NueroCarta;
	}

	public void setNueroCarta(String nueroCarta) {
		NueroCarta = nueroCarta;
	}

	public String getCircuito() {
		return Circuito;
	}

	public void setCircuito(String circuito) {
		Circuito = circuito;
	}

	
	
		
}
