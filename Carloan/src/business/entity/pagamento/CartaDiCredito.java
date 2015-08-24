package business.entity.pagamento;

import java.time.LocalDate;
import java.util.Date;

import business.entity.Cliente;

public class CartaDiCredito extends Pagamento{
	private int IDCliente ; 
	private LocalDate dataScadenza;
	private String IBAN;
	private String NumeroCarta;
	private String Circuito;
	

	public int getIDCliente() {
		return IDCliente;
	}


	public void setIDCliente(int iDCliente) {
		IDCliente = iDCliente;
	}


	public String getNumeroCarta() {
		return NumeroCarta;
	}


	public void setNumeroCarta(String numeroCarta) {
		NumeroCarta = numeroCarta;
	}


	public CartaDiCredito(float depositoCauzinale, float importo,
			float detrazioneAggiuntiva) {
			super(depositoCauzinale, importo, detrazioneAggiuntiva);
	}

	
	public CartaDiCredito( int iDCliente,LocalDate dataScadenza,
			String iBAN, String numeroCarta, String circuito) {
		super();
		IDCliente = iDCliente;
		this.dataScadenza = dataScadenza;
		IBAN = iBAN;
		NumeroCarta = numeroCarta;
		Circuito = circuito;
	}


	public LocalDate getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public String getCircuito() {
		return Circuito;
	}

	public void setCircuito(String circuito) {
		Circuito = circuito;
	}

	public CartaDiCredito(){}
	
		
}
